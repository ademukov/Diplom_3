package org.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage {
    private WebDriver driver;

    public ForgotPasswordPage (WebDriver driver) {
        this.driver = driver;
    }

    private By loginLinkOnForgotPasswordPage = By.linkText("Войти");

    public WebElement getLoginLink(){
        return driver.findElement(loginLinkOnForgotPasswordPage);
    }
}