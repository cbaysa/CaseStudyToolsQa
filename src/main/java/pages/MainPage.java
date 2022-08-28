package pages;

import base.BasePageMethods;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePageMethods {

    @FindBy(how = How.XPATH, using = "//h5[contains(text(),'Forms')]")
    private WebElement forms;


    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickForms() {
        try {
            clickWebElement(forms);
            log.info("Forms has been clicked");
        } catch (Exception e) {
            Assertions.fail("Error occured while clicking Forms!!!", e);
        }
    }


}
