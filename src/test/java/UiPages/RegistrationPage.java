package UiPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.logging.Logger;

public class RegistrationPage {

    public static Logger logger = Logger.getLogger(RegistrationPage.class.getName());
    WebDriver webDriver;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[1]/div/div/input")
    WebElement firstName;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[2]/div/div/input")
    WebElement lastName;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[3]/div/div/select")
    WebElement departmentName;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[4]/div/div/input")
    WebElement userName;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[5]/div/div/input")
    WebElement password;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[6]/div/div/input")
    WebElement confirmPassword;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[7]/div/div/input")
    WebElement email;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[8]/div/div/input")
    WebElement contactNo;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[10]/div/button")
    WebElement submit;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[1]/div/small[2]")
    WebElement firstNameErr;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[2]/div/small[2]")
    WebElement lastNameErr;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[4]/div/small[2]")
    WebElement userNameErr;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[5]/div/small[2]")
    WebElement passwordErr;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[6]/div/small[2]")
    WebElement confirmPasswordErr;

    @FindBy(xpath = "//*[@id=\"contact_form\"]/fieldset/div[7]/div/small[1]")
    WebElement emailAddressErr;


    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickSubmit() {
        submit.click();
    }

    public boolean errToastsVisibleOnRegistrationPage() {
        if (firstNameErr.isDisplayed() && lastNameErr.isDisplayed() && userNameErr.isDisplayed() && passwordErr.isDisplayed() && confirmPasswordErr.isDisplayed() && emailAddressErr.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void fillFirstName(String FirstName) {
        firstName.sendKeys(FirstName);
    }

    public void fillLastName(String LastName) {
        lastName.sendKeys(LastName);
    }

    public void filldepartName(int DepartName) {
        Select select = new Select(departmentName);
        select.selectByIndex(DepartName);
    }

    public void filluserName(String UserName) {
        userName.sendKeys(UserName);
    }

    public void fillpassword(String Pasword) {
        password.sendKeys(Pasword);
    }

    public void fillConfirmpassword(String ConfirmPassword) {
        confirmPassword.sendKeys(ConfirmPassword);
    }

    public void fillEmail(String Email) {
        email.sendKeys(Email);
    }

    public void fillContactNo(String ContactNo) {
        contactNo.sendKeys(ContactNo);
    }
}
