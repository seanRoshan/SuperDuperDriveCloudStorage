package com.seanroshan.superduperdrivecloudstorage;

import com.seanroshan.superduperdrivecloudstorage.model.Note;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SuperDuperDriveCloudStorageApplicationSeleniumTest {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private HomePage homePage;
    private NotePage notePage;
    private ResultPage resultPage;

    private final String userName = "Admin";
    private final String password = "Test_Pass_2020";

    private Note note1;
    private Note note2;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() {
        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
        homePage = new HomePage(driver);
        notePage = new NotePage(driver);
        resultPage = new ResultPage(driver);
        note1 = new Note();
        note1.setNoteTitle("NOTE TITLE");
        note1.setNoteDescription("NOTE DESCRIPTION");
        note2 = new Note();
        note2.setNoteTitle("EDIT NOTE TITLE");
        note2.setNoteDescription("EDIT NOTE DESCRIPTION");
    }

    @Test
    @Order(1)
    public void testHomePageNotAccessible() throws InterruptedException {
        driver.navigate().to("http://localhost:" + port + "/home");
        assertEquals(loginPage.getPageHeader(), "Login");
    }

    @Test
    @Order(2)
    public void testNavigateToSignUpPageButton() throws InterruptedException {
        driver.get("http://localhost:" + port + "/login");
        loginPage.clickOnSignUpPage();
        assertEquals(signupPage.getPageHeader(), "Sign Up");
    }


    @Test
    @Order(3)
    public void testSignUp() throws InterruptedException {
        driver.get("http://localhost:" + port + "/signup");
        String firstName = "Sean";
        String lastName = "Roshan";
        signupPage.signup(firstName, lastName, userName, password);
        String successMessage = signupPage.getSuccessMessage();
        assertTrue(successMessage.contains("You successfully signed up!"));
    }


    @Test
    @Order(4)
    public void testLogin() throws InterruptedException {
        driver.get("http://localhost:" + port + "/login");
        loginPage.login(userName, password);
    }

    @Test
    @Order(5)
    public void testHomePageNotAccessibleAfterLogout() throws InterruptedException {
        homePage.logout();
        driver.navigate().to("http://localhost:" + port + "/home");
        assertEquals(loginPage.getPageHeader(), "Login");
    }

    @Test
    @Order(6)
    public void testAddNote() throws InterruptedException {
        driver.get("http://localhost:" + port + "/login");
        loginPage.login(userName, password);
        notePage.navigateToNoteTab();
        notePage.addNewNote(note1);
        resultPage.clickOnContinueButton();
        notePage.navigateToNoteTab();
        Note added = notePage.getNote();
        assertEquals(added.getNoteTitle(), note1.getNoteTitle());
        assertEquals(added.getNoteDescription(), note1.getNoteDescription());
        homePage.logout();
        Thread.sleep(1000);
    }

    @Test
    @Order(7)
    public void testEditNote() throws InterruptedException {
        driver.get("http://localhost:" + port + "/login");
        loginPage.login(userName, password);
        notePage.navigateToNoteTab();
        notePage.editNote(note2);
        resultPage.clickOnContinueButton();
        notePage.navigateToNoteTab();
        Note edited = notePage.getNote();
        assertEquals(edited.getNoteTitle(), note2.getNoteTitle());
        assertEquals(edited.getNoteDescription(), note2.getNoteDescription());
        Thread.sleep(1000);
    }

    @Test
    @Order(8)
    public void testDeleteNote() throws InterruptedException {
        driver.get("http://localhost:" + port + "/login");
        loginPage.login(userName, password);
        notePage.navigateToNoteTab();
        notePage.deleteNote();
        resultPage.clickOnContinueButton();
        notePage.navigateToNoteTab();
        String noNoteText = notePage.getNoNoteText();
        assertTrue(noNoteText.contains("YOU DO NOT HAVE ANY NOTE"));
        Thread.sleep(1000);
    }


}
