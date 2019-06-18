package org.romanchi.instagram.api;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.romanchi.instagram.exceptions.ApiException;
import org.romanchi.instagram.model.Credentials;
import org.romanchi.instagram.model.dto.GirlDTO;
import org.romanchi.instagram.model.Return;
import org.romanchi.instagram.model.entities.Girl;
import org.romanchi.instagram.services.GirlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.logging.Logger;

@Component
public class ApiClient {

    private final static Logger logger = Logger.getLogger(ApiClient.class.getCanonicalName());

    private final Authenticator authenticator;

    private final GirlsService girlsService;

    @Value("${login}")
    private String login;

    @Value("${password}")
    private String password;

    private WebDriver driver;

    @Autowired
    public ApiClient(Authenticator authenticator, GirlsService girlsService) {
        this.authenticator= authenticator;
        this.girlsService = girlsService;
    }

    public void login(){
        Return ret = authenticator.authenticate(
                Credentials.builder().login(login).password(password).build());
        if(ret.getCode().equals(401)){
            logger.info("Bad credentials");
            System.exit(401);
        }
        if(ret.getCode().equals(200)){
            logger.info("Authenticated");
            this.driver = (WebDriver) ret.getObject();
        }
    }

    private GirlDTO parseGirlInfoAndGet(){
        if(!driver.getCurrentUrl().equals("https://badoo.com/encounters")){
            return null;
        }

        WebDriverWait modalWait = new WebDriverWait(driver, 1);

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement image = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".photo-gallery__photo>img")));
        String imageUrl = image.getAttribute("src");
        WebElement credentialsLabel = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(".profile-header__name")));
        String credentials = credentialsLabel.getText();
        WebElement ageLabel = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(".profile-header__age")));
        Integer age = Integer.valueOf(ageLabel.getText().substring(2));
        return GirlDTO.builder().credentials(credentials).age(age).photoUrl(imageUrl).build();
    }

    public Optional<GirlDTO> nextGirl(){
        if(!driver.getCurrentUrl().equals("https://badoo.com/encounters")){
            driver.get("https://badoo.com/encounters");
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement noBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".encounters-actions__item--no")));
        noBtn.click();
        GirlDTO girlDTO = parseGirlInfoAndGet();
        return Optional.ofNullable(girlDTO);
    }

    public Optional<GirlDTO> currentGirl() {
        return Optional.ofNullable(parseGirlInfoAndGet());
    }

    public Optional<GirlDTO> likeGirl() {
        if(!driver.getCurrentUrl().equals("https://badoo.com/encounters")){
            driver.get("https://badoo.com/encounters");
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement likeBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(".encounters-actions__item--yes")));
        try{
            likeBtn.click();
        }catch (ElementClickInterceptedException e){
            closeModal();
            likeGirl();
        }

        GirlDTO currentGirl = currentGirl().orElseThrow(() -> new ApiException("ApiException"));
        Girl toSave = currentGirl.toEntity();
        girlsService.saveGirl(toSave);

        GirlDTO girlDTO = parseGirlInfoAndGet();
        return Optional.ofNullable(girlDTO);
    }

    private void closeModal(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement denyBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".js-chrome-pushes-deny")));
        denyBtn.click();
    }
}
