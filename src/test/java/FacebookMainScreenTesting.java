import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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

    @Test
    public void findElementsByXpathTest() {
        WebElement newAccountButtonElement = driver.findElement(By.xpath("//a[text()='Create new account']"));
        assertNotNull(newAccountButtonElement);
        newAccountButtonElement.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghjklmnopqrstuvwxyz0123456789",
            "l!@#$%^&*()_k",
            "wrong email.address"
    })
    public void wrongTextTesting(String param){
        WebElement newAccountButtonElement = driver.findElement(By.xpath("//a[text()='Create new account']"));
        assertNotNull(newAccountButtonElement);
        newAccountButtonElement.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(firstNameElement);
//        String longText = ;
        firstNameElement.sendKeys(param);
        String firstNameValue = firstNameElement.getAttribute("value");
        assertEquals(param, firstNameValue);

        WebElement lastNameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
        assertNotNull(lastNameElement);
//        String wrongChars = ;
        lastNameElement.sendKeys(param);

        WebElement phoneEmailElement = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        assertNotNull(phoneEmailElement);
        phoneEmailElement.sendKeys(param);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void genderElementsTesting(){
        WebElement newAccountButtonElement = driver.findElement(By.xpath("//a[text()='Create new account']"));
        assertNotNull(newAccountButtonElement);
        newAccountButtonElement.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
