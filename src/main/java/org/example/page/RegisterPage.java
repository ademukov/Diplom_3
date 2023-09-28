package org.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {

    private WebDriver driver;

    public RegisterPage (WebDriver driver){
        this.driver = driver;
    }

    private By registerForm = By.xpath("//form[@class='Auth_form__3qKeq mb-20']");
    private By nameInputField = By.xpath("//label[contains(text(),'Имя')]/following-sibling::input");
    private By emailInputField = By.xpath("//label[contains(text(),'Email')]/following-sibling::input");
    private By passwordInputField = By.xpath("//label[contains(text(),'Пароль')]/following-sibling::input");
    private By registerButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]");
    private By incorrectPasswordAlert = By.xpath("//p[@class='input__error text_type_main-default' or contains(text(),'Некорректный пароль')]");
    private By loginLinkOnRegisterPage = By.linkText("Войти");

    public WebElement getRegisterForm (){
        return driver.findElement(registerForm);
    }

    public void setNameInputField(String name){
        driver.findElement(nameInputField).sendKeys(name);
    }
    public void setEmailInputField(String email){
        driver.findElement(emailInputField).sendKeys(email);
    }

    public void setPasswordInputField(String password){
        driver.findElement(passwordInputField).sendKeys(password);
    }

    public void clickRegisterButton(){
        driver.findElement(registerButton).click();
    }

    public String getIncorrectPasswordAlert(){
        return driver.findElement(incorrectPasswordAlert).getText();
    }

    public void clickOnLoginLink(){
        driver.findElement(loginLinkOnRegisterPage).click();
    }
    public void registerUser(String name, String email, String password){
        setNameInputField(name);
        setEmailInputField(email);
        setPasswordInputField(password);
        clickRegisterButton();

    }
}