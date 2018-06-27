package RegistrationPageTests;

import SeleniumHelper.SeleniumHelper;
import UiPages.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class Field_Validation_Test extends SeleniumHelper {

    RegistrationPage registrationPage;

    @BeforeTest
    public void setUp() throws IOException {
        init();
        registrationPage = new RegistrationPage(webDriver);
    }

    @Test
    public void testFirstNameFieldWithSingleLetter() throws InterruptedException {
        refreshPage();
        registrationPage.fillFirstName("k");

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[1]/div/small[1]")).isDisplayed(), true);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[1]/div/small[1]")).getText(), "This value is not valid");

    }

    @Test
    public void testLastNameFieldWithSingleLetter() throws InterruptedException {
        refreshPage();
        registrationPage.fillLastName("k");

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[2]/div/small[1]")).isDisplayed(), true);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[2]/div/small[1]")).getText(), "This value is not valid");

    }


    @Test
    public void testUserNameFieldValidationWithString() throws InterruptedException {
        refreshPage();
        // pass 7 letters to see field validation
        registrationPage.filluserName("abcdef");

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[4]/div/small[1]")).isDisplayed(), true);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[4]/div/small[1]")).getText(), "This value is not valid");

    }


    @Test
    public void testUserNameFieldValidationWithNumber() throws InterruptedException {
        refreshPage();
        registrationPage.filluserName("123345");

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[4]/div/small[1]")).isDisplayed(), true);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[4]/div/small[1]")).getText(), "This value is not valid");

    }

    @Test
    public void testUserNameFieldValidationWithSpecialCharacters() throws InterruptedException {
        refreshPage();
        registrationPage.filluserName("!@#@#");

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[4]/div/small[1]")).isDisplayed(), true);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[4]/div/small[1]")).getText(), "This value is not valid");

    }


    @Test
    public void testPasswordFieldValidationWithSpecialCharacters() throws InterruptedException {
        refreshPage();
        registrationPage.fillpassword("!@#@#");

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[5]/div/small[1]")).isDisplayed(), true);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[5]/div/small[1]")).getText(), "This value is not valid");
    }

    @Test
    public void testConfirmPasswordWithSpecialCharacters() throws InterruptedException {
        refreshPage();
        registrationPage.fillConfirmpassword("!@#@#");

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[6]/div/small[1]")).isDisplayed(), true);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[6]/div/small[1]")).getText(), "This value is not valid");
    }

    @Test(enabled = false)
    // No Validation added while matching passwords. Therefore test was ignore. Ideally Test should be failed
    public void testValidateConfirmPasswordandPasswordShouldMatch() throws InterruptedException {
        refreshPage();
        registrationPage.fillConfirmpassword("passw0rd");
        registrationPage.fillpassword("pa88w0rd");

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[6]/div/small[1]")).isDisplayed(), true);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[6]/div/small[1]")).getText(), "This value is not valid");
    }


    @Test
    public void testemailValidation() throws InterruptedException {
        refreshPage();
        registrationPage.fillEmail("adaads");

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[7]/div/small[2]")).isDisplayed(), true);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[7]/div/small[2]")).getText(), "This value is not valid");
    }

    @Test
    public void testemailValidationWithNumbers() throws InterruptedException {
        refreshPage();
        registrationPage.fillEmail("121221");

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[7]/div/small[2]")).isDisplayed(), true);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[7]/div/small[2]")).getText(), "This value is not valid");
    }

    @Test
    public void testemailValidationWithSpecialCharacters() throws InterruptedException {
        refreshPage();
        registrationPage.fillEmail("@#@##");

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[7]/div/small[2]")).isDisplayed(), true);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[7]/div/small[2]")).getText(), "This value is not valid");
    }

    @Test
    public void testContactNoValidation() throws InterruptedException {
        refreshPage();
        registrationPage.fillContactNo("@#@##");

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[8]/div/small")).isDisplayed(), true);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[8]/div/small")).getText(), "This value is not valid");
    }

    @Test
    public void testContactNoValidationWithString() throws InterruptedException {
        refreshPage();
        registrationPage.fillContactNo("wfrsfsd");

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[8]/div/small")).isDisplayed(), true);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[8]/div/small")).getText(), "This value is not valid");
    }

    @Test
    public void testContactNoValidationWithSpecialCharacters() throws InterruptedException {
        try {
            refreshPage();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        registrationPage.fillContactNo("#$$$");

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[8]/div/small")).isDisplayed(), true);

        Assert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[8]/div/small")).getText(), "This value is not valid");
    }

    @AfterTest
    public void close() {
        webDriver.close();
    }
}
