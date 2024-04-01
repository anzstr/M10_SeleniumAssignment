import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.Thread;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FacebookMainScreenTesting {
    private static final String HOME_PAGE_URL = "https://www.facebook.com/";
    public static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();

        driver.get(HOME_PAGE_URL);
    }

    @AfterAll
    public static void classTearDown() {
        SharedDriver.closerDriver();
    }

    @AfterEach
    public void testTearDown() {
        driver.get(HOME_PAGE_URL);
    }

    @BeforeEach
    public void createNewAccTest() throws InterruptedException {
        WebElement newAccountButtonElement = driver.findElement(By.xpath("//a[text()='Create new account']"));
        assertNotNull(newAccountButtonElement);
        newAccountButtonElement.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void findElementsByXpathTest() {

        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(firstNameElement);
        WebElement lastNameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
        assertNotNull(lastNameElement);
        WebElement phoneEmailElement = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        assertNotNull(phoneEmailElement);
        WebElement passwordElement = driver.findElement(By.xpath("//input[@name='reg_passwd__']"));
        assertNotNull(passwordElement);
        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type='submit']"));
        assertNotNull(loginButtonElement);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_",
            "wrong email.address",
            "correct@email.address"
    })
    public void specialCharactersInvalidInputTesting(String param) {
        WebElement phoneEmailElement = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        assertNotNull(phoneEmailElement);
        phoneEmailElement.sendKeys(param);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String phoneEmailValue = phoneEmailElement.getAttribute("value");
        assertEquals(param, phoneEmailValue);
    }


    @Test
    public void longTextTesting() {
        String longText = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghjklmnopqrstuvwxyz0123456789";
        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(firstNameElement);
        firstNameElement.sendKeys(longText);
        String firstNameValue = firstNameElement.getAttribute("value");
        assertEquals(longText, firstNameValue);

        WebElement lastNameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
        assertNotNull(lastNameElement);
        lastNameElement.sendKeys(longText);
        String lastNameValue = lastNameElement.getAttribute("value");
        assertEquals(longText, lastNameValue);
    }

    @Test
    public void customGenderField() {
//        createNewAccTest();
        WebElement radioGenderElement = driver.findElement(By.xpath("//input[@name='sex' and @value='-1']"));
        assertNotNull(radioGenderElement);
        radioGenderElement.click();
        WebElement customGenderElement = driver.findElement(By.xpath("//input[@name='custom_gender']"));
        assertNotNull(customGenderElement);
        customGenderElement.sendKeys("no gender");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
