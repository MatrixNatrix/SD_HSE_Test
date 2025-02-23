import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

/*
Покрытие тестов:

✅ Негативный сценарий: пустой пароль

✅ Позитивный сценарий: успешный вход

✅ Негативный сценарий: пустой логин

✅ Негативный сценарий: заблокированный пользователь

Рекомендации для дальнейшего улучшения:

Вынести общую логику инициализации драйвера в @BeforeMethod

Добавить параметризованные тесты

Реализовать паттерн Page Object

Добавить обработку исключений

Реализовать мультибраузерное тестирование
 */

public class LoginTest {

    // Тест 1: Негативный сценарий с пустым паролем
    @Test
    public void checkNegativeLoginWithEmptyPassword() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();

        String errorMessage = driver.findElement(By.cssSelector("[data-test=error]")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Password is required"); // Исправлена опечатка

        driver.quit();
    }

    // Тест 2: Позитивный сценарий входа
    @Test
    public void checkPositiveLogin() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Boolean titleIsVisible = driver.findElement(By.cssSelector("[data-test=title]")).isDisplayed();
        Assert.assertTrue(titleIsVisible);

        driver.quit();
    }

    // Тест 3: Негативный сценарий с пустым логином
    @Test
    public void checkNegativeLoginWithEmptyUsername() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String errorMessage = driver.findElement(By.cssSelector("[data-test=error]")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");

        driver.quit();
    }

    // Тест 4: Негативный сценарий с заблокированным пользователем
    @Test
    public void checkNegativeLoginWithLockedUser() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String errorMessage = driver.findElement(By.cssSelector("[data-test=error]")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.");

        driver.quit();
    }
}