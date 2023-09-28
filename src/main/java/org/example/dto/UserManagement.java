package org.example.dto;

import com.github.javafaker.Faker;
import org.apache.http.entity.ContentType;

import static io.restassured.RestAssured.given;

public class UserManagement {
    private static User user;
    private static String accessToken;

    public static void userCreation() {
        Faker faker = new Faker();
        user = new User(faker.internet().emailAddress(), faker.internet().password(), faker.name().firstName());
        UserLogin createUserForLoginCheck = new UserLogin(user.getEmail(), user.getPassword(), user.getName());//создаем объект для создания пользователя
        var response = given()
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

    public static void deleteUser() {
        given()
                .contentType(ContentType.APPLICATION_JSON.getMimeType()) // заполнили header
                .header("Authorization", accessToken)
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user")
                .then()
                .statusCode(202);
    }
}
