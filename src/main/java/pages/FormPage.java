package pages;

import base.BasePageMethods;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class FormPage extends BasePageMethods {

    @FindBy(how = How.XPATH, using = "//li[span[contains(text(),'Practice Form')]]")
    private WebElement practiceFormLink;

    @FindBy(how = How.ID, using = "firstName")
    private WebElement firstName;

    @FindBy(how = How.ID, using = "lastName")
    private WebElement lastName;

    @FindBy(how = How.ID, using = "userEmail")
    private WebElement userEmail;

    @FindBy(how = How.ID, using = "currentAddress")
    private WebElement address;

    @FindBy(how = How.ID, using = "userNumber")
    private WebElement mobile;

    @FindBy(how = How.ID, using = "submit")
    private WebElement submitBtn;

    @FindBy(how = How.XPATH, using = "//div[@class='modal-header']/div[text()='Thanks for submitting the form']")
    private WebElement formPopup;

    @FindBy(how = How.ID, using = "closeLargeModal")
    private WebElement closeBtn;

    public FormPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickPracticeForm() {
        try {
            clickWebElement(practiceFormLink);
            log.info("Practice Form link has been clicked");
        } catch (Exception e) {
            Assertions.fail("Error occured while clicking Practice Form link!!!", e);
        }
    }

    public void fillFirstName(String value) {
        try {
            firstName.sendKeys(value);
            log.info("First Name filled with {}", value);
        } catch (Exception e) {
            log.error("First Name value : {}", value);
            Assertions.fail("Error occured while filling value !!!", e);
        }
    }

    public void fillLastName(String value) {
        try {
            lastName.sendKeys(value);
            log.info("Last Name filled with {}", value);
        } catch (Exception e) {
            log.error("Last Name value: {}", value);
            Assertions.fail("Error occured while filling value !!!", e);
        }
    }

    public void fillMailAddress(String value) {
        try {
            userEmail.sendKeys(value);
            log.info("Mail Address filled with {}", value);
        } catch (Exception e) {
            log.error("Mail Address value: {}", value);
            Assertions.fail("Error occured while filling value !!!", e);
        }
    }

    public void fillAddress(String value) {
        try {
            address.sendKeys(value);
            log.info("Address filled with {}", value);
        } catch (Exception e) {
            log.error("Address value: {}", value);
            Assertions.fail("Error occured while filling value !!!", e);
        }
    }

    public void fillMobileNumber() {
        String value = generateDigits(10);
        try {
            mobile.sendKeys(value);
            log.info("Mobile Number filled with {}", value);
        } catch (Exception e) {
            log.error("Mobile Number: {}", value);
            Assertions.fail("Error occured while filling value !!!", e);
        }
    }

    public String generateDigits(int digitCount) {
        int minimum = (int) Math.pow(10, digitCount - 1);
        int maximum = (int) Math.pow(10, digitCount) - 1;
        Random random = new Random();
        return String.valueOf(minimum + random.nextInt((maximum - minimum) + 1));
    }

    public void selectGender(String value) {
        try {
            WebElement gender = getWebElement(By.xpath("//div[input[contains(@id,'gender-radio') and @value='" + value + "']]"));
            clickWebElement(gender);
            log.info("Gender {} has been selected", value);
        } catch (Exception e) {
            log.error("Gender : {}", value);
            Assertions.fail("Error occured while selecting gender!!!", e);
        }
    }

    public void clickSubmitButton() {
        try {
            clickWebElement(submitBtn);
            log.info("Submit button has been clicked");
        } catch (Exception e) {
            Assertions.fail("Error occured while clicking submit button!!!", e);
        }
    }


    public void waitForPopup(int seconds) {
        try {
            //waitUntilElementVisibleWithTimeout(formPopup,seconds);
            TimeUnit.SECONDS.sleep(seconds);
            log.info("Popup waited for {} seconds", seconds);
        } catch (Exception e) {
            Assertions.fail("Error occured while waiting!!!", e);
        }
    }

    public void clickCloseButton() {
        try {
            clickWebElement(closeBtn);
            log.info("Close button on popup has been clicked");
        } catch (Exception e) {
            Assertions.fail("Error occured while clicking Close button on popup!!!", e);
        }
    }


}




