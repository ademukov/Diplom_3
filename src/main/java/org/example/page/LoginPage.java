package org.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailInputField = By.xpath("//input[@class='text input__textfield text_type_main-default' and @name='name']");

    private By passwordInputField = By.xpath("//input[@class='text input__textfield text_type_main-default' and @name='Пароль'] ");

    private By loginButton = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");

    private By assembleBurgerHeader = By.xpath("//h1[contains(text(),'Соберите бургер')]");

    private By accountButton = By.linkText("Личный Кабинет");

    private By registerLink = By.linkText("Зарегистрироваться");

    private By restorePasswordLink = By.linkText("Восстановить пароль");


    public void setEmailInputField (String email){
        driver.findElement(emailInputField).sendKeys(email);
    }

    public void setPasswordInputField (String password){
        driver.findElement(passwordInputField).sendKeys(password);
    }

    public void clickLoginButton () {
        driver.findElement(loginButton).click();
    }

    public void clickTopBarAccountButton (){
        driver.findElement(accountButton).click();
    }

    public void loginToAccount(String email, String password){
        setEmailInputField(email);
        setPasswordInputField(password);
        clickLoginButton();
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(assembleBurgerHeader));
    }

    public WebElement getRegisterLink(){
        return driver.findElement(registerLink);
    }

    public WebElement getRestorePasswordLink(){
        return driver.findElement(restorePasswordLink);
    }
}