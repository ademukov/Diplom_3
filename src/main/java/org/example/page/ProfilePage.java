package org.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage {
    private WebDriver driver;
    private By profileButton = By.linkText("Профиль");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getProfileButton() {
        return driver.findElement(profileButton);
    }
}
