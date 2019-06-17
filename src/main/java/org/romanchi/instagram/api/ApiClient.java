package org.romanchi.instagram.api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.romanchi.instagram.model.Credentials;
import org.romanchi.instagram.model.Girl;
import org.romanchi.instagram.model.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.logging.Logger;

@Component
public class ApiClient {

    private final static Logger logger = Logger.getLogger(ApiClient.class.getCanonicalName());

    private final Authenticator authenticator;

    @Value("${login}")
    private String login;

    @Value("${password}")
    private String password;

    private WebDriver driver;

    @Autowired
    public ApiClient(Authenticator authenticator) {
        this.authenticator= authenticator;
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

    private Girl parseGirlInfoAndGet(){
        if(!driver.getCurrentUrl().equals("https://badoo.com/encounters")){
            return null;
        }
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
        return Girl.builder().credentials(credentials).age(age).photourl(imageUrl).build();
    }

    public Optional<Girl> nextGirl(){
        if(!driver.getCurrentUrl().equals("https://badoo.com/encounters")){
            driver.get("https://badoo.com/encounters");
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement noBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".encounters-actions__item--no")));
        noBtn.click();
        Girl girl = parseGirlInfoAndGet();
        return Optional.ofNullable(girl);
    }

    public Optional<Girl> currentGirl() {
        return Optional.ofNullable(parseGirlInfoAndGet());
    }
}
