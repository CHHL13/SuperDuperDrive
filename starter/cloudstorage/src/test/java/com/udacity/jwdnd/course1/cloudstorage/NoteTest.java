package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTest {

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
    public void createNote() {
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Home", driver.getTitle());

        HomePage homePage = new HomePage(this.driver);

        homePage.goToNoteTab();


        String title = "Note Test 1";
        String description = "Note Test Description";


        homePage.addNewNote(title, description);
        Assertions.assertEquals("Result", driver.getTitle());

        ResultPage resultPage = new ResultPage(this.driver);

        resultPage.changeSuccessGoToHome();
        Assertions.assertEquals("Home", driver.getTitle());


        homePage = new HomePage(this.driver);
        homePage.goToNoteTab();

        Note note = homePage.getNote();

        Assertions.assertEquals(title, note.getNotetitle());
        Assertions.assertEquals(description, note.getNotedescription());

        homePage.deleteNote();

    }

    @Test
    public void editNote() {
        driver.get("http://localhost:" + this.port + "/home");

        HomePage homePage = new HomePage(this.driver);
        homePage.goToNoteTab();

        String title = "Note Test 1";
        String description = "Note Test Description";


        homePage.addNewNote(title, description);

        ResultPage resultPage = new ResultPage(this.driver);
        resultPage.changeSuccessGoToHome();

        homePage.goToNoteTab();

        title = "Note Edit Test 1";
        description = "Note Edit Test Description";

        homePage.editNote(title, description);


        Assertions.assertEquals("Result", driver.getTitle());

        resultPage = new ResultPage(this.driver);

        resultPage.changeSuccessGoToHome();
        Assertions.assertEquals("Home", driver.getTitle());


        homePage = new HomePage(this.driver);
        homePage.goToNoteTab();

        Note note = homePage.getNote();

        Assertions.assertEquals(title, note.getNotetitle());
        Assertions.assertEquals(description, note.getNotedescription());

        homePage.deleteNote();
    }

    @Test
    public void deleteNote() {
        driver.get("http://localhost:" + this.port + "/home");

        HomePage homePage = new HomePage(this.driver);
        homePage.goToNoteTab();

        String title = "Note Test 1";
        String description = "Note Test Description";


        homePage.addNewNote(title, description);

        ResultPage resultPage = new ResultPage(this.driver);
        resultPage.changeSuccessGoToHome();

        homePage.goToNoteTab();

        Assertions.assertTrue(homePage.isNoteExist(this.driver));

        homePage.deleteNote();

        Assertions.assertFalse(homePage.isNoteExist(this.driver));

    }
}
