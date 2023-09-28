package org.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private WebDriver driver;
    private By makeOrderButton = By.xpath("//button[contains(text(),'Оформить заказ')]");
    private By transientButton = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    private By topBarAccountButton = By.linkText("Личный Кабинет");
    private By bunsTab = By.xpath("//div/span[contains(text(), 'Булки')]");
    private By saucesTab = By.xpath("//div/span[contains(text(), 'Соусы')]");
    private By ingredientsTab = By.xpath("//div/span[contains(text(), 'Начинки')]");
    private By bunsTabElement = By.xpath("//span[contains(text(), 'Булки')]/parent::div");
    private By saucesSpan = By.xpath("//span[contains(text(), 'Соусы')]/parent::div");
    private By ingredientsSpan = By.xpath("//span[contains(text(), 'Начинки')]/parent::div");
    private By bunsSpan = By.xpath("//span[contains(text(), 'Булки')]/parent::div");


    private By saucesTabTitle = By.xpath("//h2[text()='Соусы']");
    private By ingredientsTabTitle = By.xpath("//h2[text()='Начинки']");
    private By bunsTabTitle = By.xpath("//h2[text()='Булки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSaucesSpan() {
        return driver.findElement(saucesSpan);
    }

    public WebElement getIngredientsSpan() {
        return driver.findElement(ingredientsSpan);
    }

    public WebElement getBunsSpan() {
        return driver.findElement(bunsSpan);
    }

    public WebElement getTransientButton() {
        return driver.findElement(transientButton);
    }

    public void clickTopBarAccountButton() {
        driver.findElement(topBarAccountButton).click();
    }


    public void selectSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    public void selectIngredientsTab() {
        driver.findElement(ingredientsTab).click();
    }
}