package ru.netology.card;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.className;

public class CardTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    // проверка, когда все поля заполнены и заявка успешно отправляется

//    @Test
//    void validTest() {
//        List<WebElement> elements = driver.findElements(By.className("input__control"));
//        elements.get(0).sendKeys("Петров Петя");
//        elements.get(1).sendKeys("+79102671142");
//        driver.findElement(By.className("checkbox__box")).click();
//        driver.findElement(By.className("button")).click();
//
//        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
//        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
//    }

    @Test
    void validSelectorTest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Петя");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79102671142");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();

        assertEquals(expected, actual);
    }

    // тест с именем на латинице

//    @Test
//    void invalidNameTest() {
//        List<WebElement> elements = driver.findElements(className("input__control"));
//        elements.get(0).sendKeys("Petrov Petya");
//        elements.get(1).sendKeys("+79102671142");
//        driver.findElement(className("checkbox__box")).click();
//        driver.findElement(className("button")).click();
//
//        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
//        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();
//
//        assertEquals(expected, actual);
//    }

    @Test
    void invalidNameSelectorTest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Petrov Petya");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79102671142");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();

        assertEquals(expected, actual);
    }

    // тест с неверным номером телефона

//    @Test
//    void invalidPhoneTest() {
//        List<WebElement> elements = driver.findElements(className("input__control"));
//        elements.get(0).sendKeys("Петров Петя");
//        elements.get(1).sendKeys("+00909");
//        driver.findElement(className("button")).click();
//
//        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
//        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();
//
//        assertEquals(expected, actual);
//    }

    @Test
    void invalidPhoneSelectorTest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Петя");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+00909");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();

        assertEquals(expected, actual);
    }

    // тестирование "пустое имя"

//    @Test
//    void nameNullTest() {
//        List<WebElement> elements = driver.findElements(className("input__control"));
//        elements.get(1).sendKeys("+79102671142");
//        driver.findElement(className("checkbox__box")).click();
//        driver.findElement(className("button")).click();
//
//        String expected = "Поле обязательно для заполнения";
//        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();
//
//        assertEquals(expected, actual);
//    }

    @Test
    void nameNullSelectorTest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79102671142");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();

        assertEquals(expected, actual);
    }

    // пустой номер телефона

//    @Test
//    void phoneNullTest() {
//        List<WebElement> elements = driver.findElements(className("input__control"));
//        elements.get(0).sendKeys("Петров Петя");
//        driver.findElement(className("checkbox__box")).click();
//        driver.findElement(className("button")).click();
//
//        String expected = "Поле обязательно для заполнения";
//        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();
//
//        assertEquals(expected, actual);
//    }

    @Test
    void phoneNullSelectorTest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Петя");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();

        assertEquals(expected, actual);
    }

    // пропущен чек-бокс

//    @Test
//    void checkBoxNullTest() {
//        List<WebElement> elements = driver.findElements(className("input__control"));
//        elements.get(0).sendKeys("Петров Петя");
//        elements.get(1).sendKeys("+79102671142");
//        driver.findElement(className("button")).click();
//
//        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
//        String actual = driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid .checkbox__text")).getText().trim();
//
//        assertEquals(expected, actual);
//    }

    @Test
    void checkBoxNullSelectorTest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Петя");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79102671142");
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        String actual = driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid .checkbox__text")).getText();

        assertEquals(expected, actual);
    }

    // тест с фамилией через дефиз

//    @Test
//    void validTestLastName() {
//        List<WebElement> elements = driver.findElements(By.className("input__control"));
//        elements.get(0).sendKeys("Петров-Иванов");
//        elements.get(1).sendKeys("+79102671142");
//        driver.findElement(By.className("checkbox__box")).click();
//        driver.findElement(By.className("button")).click();
//
//        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
//        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
//    }

    @Test
    void doubleLastNameSelectorTest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров-Иванов Петя");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79102671142");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();

        assertEquals(expected, actual);
    }
}