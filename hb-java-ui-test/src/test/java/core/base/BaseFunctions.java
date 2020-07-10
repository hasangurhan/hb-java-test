package core.base;

import com.google.common.base.Preconditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Base Functions
 */
public class BaseFunctions {
    protected WebDriver driver;

    public BaseFunctions(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Find element
     *
     * @param locator locator of the input
     */
    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Clear inputs and send text
     *
     * @param by   locator of the input
     * @param text random text to enter
     */
    protected void type(By by, String text) {
        WebElement e = find(by);
        e.clear();
        e.sendKeys(text);
    }

    /**
     * Find elements
     *
     * @param locator locator of the input
     */
    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }


    /**
     * Checks given locator is during default wait period
     *
     * @param by locator of the input
     */
    protected boolean isElementPresent(By by) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            List<WebElement> elements = driver.findElements(by);
            return elements.size() != 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    /**
     * Expectation
     */
    public boolean explicitWait(final ExpectedCondition<?> condition, long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        Preconditions.checkNotNull(condition, "Condition must be not null");
        Preconditions.checkArgument(TimeUnit.MINUTES.toSeconds(3) > maxCheckTimeInSeconds, "Max check time in seconds should be less than 3 minutes");
        checkConditionTimeouts(maxCheckTimeInSeconds, millisecondsBetweenChecks);
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            WebDriverWait explicitWait = new WebDriverWait(driver, maxCheckTimeInSeconds, millisecondsBetweenChecks);
            explicitWait.until(condition);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (driver != null) {
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            } else {
                throw new IllegalArgumentException("Driver shouldn't be null");
            }
        }
    }

    /**
     * Checks the timeout of check conditions and the interval between checks
     *
     * @param maxCheckTimeInSeconds     Maximum timeout duration
     * @param millisecondsBetweenChecks Duration between checks
     */
    private void checkConditionTimeouts(long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        Preconditions.checkState(maxCheckTimeInSeconds > 0, "maximum check time in seconds must be not 0");
        Preconditions.checkState(millisecondsBetweenChecks > 0, "milliseconds count between checks must be not 0");
        Preconditions.checkState(millisecondsBetweenChecks < (maxCheckTimeInSeconds * 1000),
                "Millis between checks must be less than max seconds to wait");
    }

    /**
     * If it is not possible to stop the flow of actions, write a message in System.out
     *
     * @param milliseconds time of pause in milliseconds
     */
    public void pause(int milliseconds) {
        Preconditions.checkArgument(milliseconds <= 120_000);
        //separate to 10 seconds chunks
        final int chunkSize = 10_000;
        pauseThread(milliseconds % chunkSize);
        milliseconds -= milliseconds % chunkSize;
        while (milliseconds > 0) {
            pauseThread(chunkSize);
            milliseconds -= chunkSize;
            isElementPresent(By.xpath("//body"));
        }
    }

    private void pauseThread(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
