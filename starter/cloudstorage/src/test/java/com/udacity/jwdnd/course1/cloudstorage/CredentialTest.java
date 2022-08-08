package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    public String baseURL;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        baseURL = "http://localhost:" + port;

        String firstName = "John";
        String lastName = "Doe";
        String username = "testUser1";
        String password = "abc0123456789";

        driver.get("http://localhost:" + this.port + "/signup");

        SignupPage signupPage = new SignupPage(this.driver);
        signupPage.signup(firstName, lastName, username, password);

        driver.get("http://localhost:" + this.port + "/login");

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login(username, password);

        driver.get("http://localhost:" + this.port + "/home");
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void createCredential() {
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Home", driver.getTitle());

        HomePage homePage = new HomePage(this.driver);

        homePage.goToCredentialTab();

        String url = "www.google.com";
        String username = "JohnDoe123";
        String password = "JD0123456789";

        homePage.addNewCredential(url, username, password);

        Assertions.assertEquals("Result", driver.getTitle());

        ResultPage resultPage = new ResultPage(this.driver);

        resultPage.changeSuccessGoToHome();
        Assertions.assertEquals("Home", driver.getTitle());

        homePage = new HomePage(this.driver);
        homePage.goToCredentialTab();

        Credential credential = homePage.getCredential();

        Assertions.assertEquals(url, credential.getUrl());
        Assertions.assertEquals(username, credential.getUsername());
        Assertions.assertEquals(password, credential.getPassword());

        homePage.deleteCredential();
    }

    @Test
    public void editCredential() {
        driver.get("http://localhost:" + this.port + "/home");

        HomePage homePage = new HomePage(this.driver);

        homePage.goToCredentialTab();

        String url = "www.google.com";
        String username = "JohnDoe123";
        String password = "JD0123456789";

        homePage.addNewCredential(url, username, password);

        ResultPage resultPage = new ResultPage(this.driver);

        resultPage.changeSuccessGoToHome();

        homePage = new HomePage(this.driver);
        homePage.goToCredentialTab();


        url = "www.4399.com";
        username = "player1";
        password = "abc0123456789";

        homePage.editCredential(url, username, password);

        Assertions.assertEquals("Result", driver.getTitle());

        resultPage = new ResultPage(this.driver);

        resultPage.changeSuccessGoToHome();
        Assertions.assertEquals("Home", driver.getTitle());


        homePage = new HomePage(this.driver);
        homePage.goToCredentialTab();

        Credential credential = homePage.getCredential();


        Assertions.assertEquals(url, credential.getUrl());
        Assertions.assertEquals(username, credential.getUsername());
        Assertions.assertEquals(password, credential.getPassword());

        homePage.deleteCredential();
    }

    @Test
    public void deleteCredential() {
        driver.get("http://localhost:" + this.port + "/home");

        HomePage homePage = new HomePage(this.driver);

        homePage.goToCredentialTab();

        String url = "www.google.com";
        String username = "JohnDoe123";
        String password = "JD0123456789";

        homePage.addNewCredential(url, username, password);

        ResultPage resultPage = new ResultPage(this.driver);

        resultPage.changeSuccessGoToHome();

        homePage = new HomePage(this.driver);
        homePage.goToCredentialTab();

        Assertions.assertTrue(homePage.isCredentialExist(this.driver));

        homePage.deleteCredential();

        Assertions.assertFalse(homePage.isCredentialExist(this.driver));
    }

}
