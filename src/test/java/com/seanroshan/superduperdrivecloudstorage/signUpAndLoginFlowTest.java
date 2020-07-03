package com.seanroshan.superduperdrivecloudstorage;

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
public class signUpAndLoginFlowTest {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private HomePage homePage;


    private final String firstName = "Sean";
    private final String lastName = "Roshan";
    private final String userName = "Admin";
    private final String password = "Test_Pass_2020";

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


}
