package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.example.page.LoginPage;
import org.example.page.MainPage;
import org.example.page.ProfilePage;
import org.example.page.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseUiTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPage loginPage;
    protected String name;
    protected String email;
    protected SoftAssertions softAssertions;
    protected RegisterPage registerPage;
    protected MainPage mainPage;
    protected ProfilePage profilePage;

    @Before
    public void setup() {
        if ("yandex".equalsIgnoreCase(System.getProperty("browser"))) {
            setupYandexDriver();
        } else {
            setupChromeDriver();
        }
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        name = "User" + new Random().nextInt(100);
        email = name.toLowerCase() + "@email.com";
        softAssertions = new SoftAssertions();
        registerPage = new RegisterPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
    }

    private void setupChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox",
                "--disable-dev-shm-usage",
                "start-maximized");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
    }

    private void setupYandexDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        options.addArguments("--no-sandbox",
                "--disable-dev-shm-usage",
                "start-maximized");
        WebDriverManager.chromedriver().browserVersion("114").setup();
        driver = new ChromeDriver(options);
    }

    @After
    public void quitBrowser() {
        driver.close();
    }

}
