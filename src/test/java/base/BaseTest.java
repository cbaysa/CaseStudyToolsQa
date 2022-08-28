package base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BaseTest {

    static WebDriver driver;


    @BeforeAll
    public static void setup() {
        String driverPath = System.getProperty("user.dir") + "/webdrivers/chromedriver.exe";
        ChromeOptions options = new ChromeOptions();
        //disable chrome save your password popups
        options.setCapability("credentials_enable_service", "false");
        options.setCapability("profile.password_manager_enabled", "false");
        //get alerts risky unsafe sites
        options.setCapability("safebrowsing.enabled", "true");

        //no need cookies,temproray internet files,history
        options.addArguments("--incognito");
        //The /dev/shm partition is too small in certain environments, causing Chrome to fail or crash
        options.addArguments("--disable-dev-shm-usage");
        //ignore SSL sertificate errors
        options.addArguments("--ignore-certificate-errors");
        //browser-level switch for testing purposes only.
        // disable processes that execute within a very restrictive environment.
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");

        options.addArguments("--disable-extensions"); //to disable browser extension popup
        System.setProperty("webdriver.chrome.driver", driverPath);
        setWebDriver(new ChromeDriver(options));


    }

    public static WebDriver getWebDriver() {
        return driver;
    }

    public static void setWebDriver(WebDriver driver) {
        BaseTest.driver = driver;
    }


    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

}
