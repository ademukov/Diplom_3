package org.example;

import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.example.dto.CreateUserResponse;
import org.example.dto.User;
import org.example.dto.UserLogin;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.entity.ContentType;
import org.example.base.BaseUiTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static io.restassured.RestAssured.given;

public class PersonalAccountTest extends BaseUiTest {

    private static User user;
    private static String accessToken;

    @BeforeClass
    public static void userCreation() {
        Faker faker = new Faker();
        user = new User(faker.internet().emailAddress(), faker.internet().password(), faker.name().firstName());
        UserLogin createUserForLoginCheck = new UserLogin(user.getEmail(), user.getPassword(), user.getName());//создаем объект для создания пользователя
        given()
                .contentType(ContentType.APPLICATION_JSON.getMimeType()) // заполнили header
                .body(createUserForLoginCheck)
                .post("https://stellarburgers.nomoreparties.site/api/auth/register");
        UserLogin loginBody = new UserLogin(
                user.getEmail(),
                user.getPassword(),
                null
        );
        var userLoginResponse = given()
                .contentType(ContentType.APPLICATION_JSON.getMimeType()) // заполнили header
                .body(loginBody)
                .post("https://stellarburgers.nomoreparties.site/api/auth/login")
                .then()
                .statusCode(200)
                .extract().body().as(CreateUserResponse.class);
        accessToken = userLoginResponse.getAccessToken();
    }

    @AfterClass
    public static void deleteUser() {
        given()
                .contentType(ContentType.APPLICATION_JSON.getMimeType()) // заполнили header
                .header("Authorization", accessToken)
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user")
                .then()
                .statusCode(202);
    }

    @Test
    @DisplayName("Переход в личный кабинет залогиненным пользователем")
    public void transitionToPersonalAccountAuthorizedUser() {
        mainPage.getTransientButton().click();
        loginPage.loginToAccount(user.getEmail(), user.getPassword());
        mainPage.clickTopBarAccountButton();
        wait.until(ExpectedConditions.visibilityOf(profilePage.getProfileButton()));
        Assertions.assertThat("Профиль").isEqualTo(profilePage.getProfileButton().getText());
    }
}
