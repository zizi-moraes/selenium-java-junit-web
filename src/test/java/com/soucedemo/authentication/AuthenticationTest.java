package com.soucedemo.authentication;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testes automatizados da funcionalidade SignUp")
public class AuthenticationTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().browserVersion("121.0.6167.85").setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(1000);
            driver.quit();
        }
    }

    @Test
    @DisplayName("Realizar login com sucesso")
    public void realizarLoginComSucesso(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String titleProductsPage = driver.findElement(By.cssSelector("span.title")).getText();
        Assertions.assertEquals("Products", titleProductsPage);
    }

    @Test
    @DisplayName("RealizarLogout")
    public void realizarLogout(){

        realizarLoginComSucesso();

        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();

        WebElement loginTextField = driver.findElement(By.id("user-name"));
        assertTrue(loginTextField.isDisplayed());
    }


}
