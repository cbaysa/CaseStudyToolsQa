package base;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BasePageMethods {

    public static final Logger log = LogManager.getLogger(BasePageMethods.class);
    WebDriver driver;
    WebDriverWait webDriverWait;
    JavascriptExecutor jsExec;
    Actions builder;
    String parentWindowId;
    long timeOutInSeconds = 90L;

    public BasePageMethods(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(90L));
        this.jsExec = (JavascriptExecutor) this.driver;
        this.builder = new Actions(this.driver);
        this.parentWindowId = parentWindowId;
        PageFactory.initElements(driver, this);
    }

    public WebElement waitUntilClickable(WebElement webElement) {
        WebElement element = null;

        try {
            Wait<WebDriver> wait = (new FluentWait(this.driver)).withTimeout(Duration.ofSeconds(this.timeOutInSeconds)).pollingEvery(Duration.ofMillis(100L)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception e) {
            Assertions.fail("Web Element can not be clicked!!", e);
        }

        return element;
    }


    public void scrollTo(int x, int y) {
        this.jsExec.executeScript("scrollTo(" + x + "," + y + ");", new Object[0]);
    }

    public void clickWebElement(WebElement element) {
        this.scrollTo(element, 100);
        this.waitUntilClickable(element).click();
        this.waitForPageToLoad();
    }

    public void scrollTo(WebElement element, int margin) {
        this.scrollTo(element.getLocation().x, element.getLocation().y - margin);
    }


    public WebElement getWebElement(By locator) {
        return driver.findElement(locator);
    }


    public void waitForJsLoad() {
        try {
            ExpectedCondition<Boolean> jsLoad = (webDriver) -> jsExec.executeScript("return document.readyState", new Object[0]).toString().equals("complete");
            String jsReadyState;
            do {
                webDriverWait.until(jsLoad);
                jsReadyState = jsExec.executeScript("return document.readyState", new Object[0]).toString();
            } while (!jsReadyState.equalsIgnoreCase("complete"));
        } catch (Exception e) {
            log.error("JS LOAD on page failed !!!");
            throw e;
        }
    }

    public void waitForPageToLoad() {
        try {
            waitForJsLoad();
            waitForJqueryLoad();
        } catch (Exception e) {
            Assertions.fail("Wait for page failed", e);
        }
    }


    public void waitForJqueryLoad() {
        try {
            ExpectedCondition jQueryLoad = (webDriver) -> (Long) jsExec.executeScript("return jQuery.active", new Object[0]) == 0L;

            String jQueryCount;
            do {
                webDriverWait.until(jQueryLoad);
                jQueryCount = jsExec.executeScript("return jQuery.active", new Object[0]).toString();
            } while (Integer.parseInt(jQueryCount) != 0);

        } catch (Exception e) {
            log.error("JQUERY LOAD failed!!");
            throw e;
        }

    }

    public void waitUntilElementVisibleWithTimeout(WebElement element, int seconds) {
        try {
            FluentWait<WebDriver> wait = (new WebDriverWait(this.driver, Duration.ofSeconds(seconds)).pollingEvery(Duration.ofMillis(100L)).withTimeout(Duration.ofSeconds(seconds)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            Assertions.fail("Element: " + element + " is not visible !!", e);
        }
    }
}
