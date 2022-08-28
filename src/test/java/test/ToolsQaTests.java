package test;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pages.FormPage;
import pages.MainPage;

import static util.FieldChecker.verifyFieldsFromCsv;
import static util.ScreenshotTaker.takeScreenshot;


public class ToolsQaTests extends BaseTest {

    private TestInfo testInfo;

    @BeforeEach
    void init(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/personalinfo.csv", numLinesToSkip = 1)
    public void fillPracticeForm(String firstName, String lastName, String mail, String address)  {
        verifyFieldsFromCsv(firstName, lastName, mail, address);
        String homePageUrl = "https://demoqa.com/";
        WebDriver drv = getWebDriver();
        MainPage mp = new MainPage(drv);
        drv.get(homePageUrl);
        mp.waitForPageToLoad();
        mp.clickForms();

        FormPage fp = new FormPage(drv);
        fp.clickPracticeForm();
        fp.fillFirstName(firstName);
        fp.fillLastName(lastName);
        fp.fillMailAddress(mail);
        fp.fillAddress(address);
        fp.selectGender("Female");
        fp.fillMobileNumber();
        fp.clickSubmitButton();
        fp.waitForPopup(5);
        takeScreenshot(testInfo.getTestClass().get().getName(), testInfo.getTestMethod().get().getName());
        fp.clickCloseButton();

    }

}
