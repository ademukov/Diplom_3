package org.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {

    private WebDriver driver;

    public AccountPage (WebDriver driver) {
        this.driver = driver;
    }

    private By accountProfileName = By.xpath("//input[@type ='text' and @name='Name']");

    private By accountProfileLogin = By.xpath("//input[@type ='text' and @name='name']");

    private By logoutButton = By.xpath("//button[contains(text(), 'Выход')]");

    public WebElement getAccountProfileName (){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(accountProfileName)));
        return  driver.findElement(accountProfileName);
    }
    public WebElement getAccountProfileLogin(){
        return driver.findElement(accountProfileLogin);
    }
    public WebElement getLogoutButton (){
        return driver.findElement(logoutButton);
    }

    public void clickLogoutButton (){
        driver.findElement(logoutButton).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.urlContains("/login"));
    }
}