package org.example;

import com.github.javafaker.Faker;
import org.example.dto.CreateUserResponse;
import org.example.dto.User;
import org.example.dto.UserLogin;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.entity.ContentType;
import org.example.page.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class LoginTest {

    private static User user;
    private static String accessToken;
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private LoginPage loginPage;

    @BeforeClass
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

    @AfterClass
    public static void deleteUser() {
        given()
                .contentType(ContentType.APPLICATION_JSON.getMimeType()) // заполнили header
                .header("Authorization", accessToken)
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user")
                .then()
                .statusCode(202);
    }

    @Before
    public void setup() {
        setupChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, 10);

    }

    private void setupChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox",
                "--disable-dev-shm-usage",
                "start-maximized");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
    }

    @After
    public void quitBrowser() {
        driver.close();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void authWithAccountLoginButtonOnMainPage() {
        mainPage.getTransientButton().click();
        loginPage.loginToAccount(user.getEmail(), user.getPassword());
        Assert.assertEquals("Оформить заказ", mainPage.getTransientButton().getText());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void authWithTopBarAccountLinkOnMainPage() {
        mainPage.clickTopBarAccountButton();
        loginPage.loginToAccount(user.getEmail(), user.getPassword());
        Assert.assertEquals("Оформить заказ", mainPage.getTransientButton().getText());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void authWithLoginButtonOnRegisterPage() {
        mainPage.clickTopBarAccountButton();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getRegisterLink()));
        loginPage.getRegisterLink().click();
        RegisterPage registerPage = new RegisterPage(driver);
        wait.until(ExpectedConditions.visibilityOf(registerPage.getRegisterForm()));
        registerPage.clickOnLoginLink();
        loginPage.loginToAccount(user.getEmail(), user.getPassword());
        Assert.assertEquals("Оформить заказ", mainPage.getTransientButton().getText());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void authWithLoginButtonOnForgotPasswordPage() {
        mainPage.clickTopBarAccountButton();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getRestorePasswordLink()));
        loginPage.getRestorePasswordLink().click();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        wait.until(ExpectedConditions.visibilityOf(forgotPasswordPage.getLoginLink()));
        forgotPasswordPage.getLoginLink().click();
        loginPage.loginToAccount(user.getEmail(), user.getPassword());
        Assert.assertEquals("Оформить заказ", mainPage.getTransientButton().getText());
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void userlogoutFromAccount() throws InterruptedException {
        mainPage.getTransientButton().click();
        loginPage.loginToAccount(user.getEmail(), user.getPassword());
        Assert.assertEquals("Оформить заказ", mainPage.getTransientButton().getText());
        loginPage.clickTopBarAccountButton();
        AccountPage accountPage = new AccountPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(accountPage.getLogoutButton()));
        accountPage.clickLogoutButton();
        Assert.assertTrue("Пользователь не разлогинен", driver.getCurrentUrl().endsWith("/login"));
    }

}