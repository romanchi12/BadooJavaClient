package org.romanchi.instagram.api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.romanchi.instagram.model.Credentials;
import org.romanchi.instagram.model.Return;
import org.springframework.stereotype.Component;


@Component
public class BadooAuthenticator implements Authenticator {

    WebDriver driver;


    BadooAuthenticator(){
        driver = new ChromeDriver();
    }

    @Override
    public Return<?> authenticate(Credentials credentials) {
        driver.get("https://badoo.com/signin/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        WebElement password = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("password")));
        email.sendKeys(credentials.getLogin());
        password.sendKeys(credentials.getPassword());
        WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("post")));
        loginBtn.click();
        return Return.builder().code(200).message("ok").object(driver).build();
    }
}
