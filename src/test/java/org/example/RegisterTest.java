package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.base.BaseUiTest;
import org.example.page.AccountPage;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterTest extends BaseUiTest {



    @Test
    @DisplayName("Успешная регистрация") //дополнительно полноценно проверяется в классе LoginTest в @BeforeClass
    public void userRegisterWithCorrectPassword() {
        loginPage.clickTopBarAccountButton();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getRegisterLink()));
        loginPage.getRegisterLink().click();

        wait.until(ExpectedConditions.visibilityOf(registerPage.getRegisterForm()));
        registerPage.registerUser(name, email, "123456");

        wait.until(ExpectedConditions.urlContains("/login"));
        loginPage.loginToAccount(email, "123456");
        loginPage.clickTopBarAccountButton();

        AccountPage accountPage = new AccountPage(driver);
        softAssertions.assertThat(name).isEqualTo(accountPage.getAccountProfileName().getAttribute("value"));
        softAssertions.assertThat(email).isEqualTo(accountPage.getAccountProfileLogin().getAttribute("value"));
        softAssertions.assertAll();

    }

    @Test
    @DisplayName("Отображение ошибки при вводе некорректного пароля")
    public void userRegisterWithIncorrectPassword() {
        loginPage.clickTopBarAccountButton();
         wait.until(ExpectedConditions.visibilityOf(loginPage.getRegisterLink()));
        loginPage.getRegisterLink().click();

        wait.until(ExpectedConditions.visibilityOf(registerPage.getRegisterForm()));
        registerPage.registerUser(name, email, "1234");

        softAssertions.assertThat("Некорректный пароль").isEqualTo(registerPage.getIncorrectPasswordAlert());
        softAssertions.assertAll();
    }
}