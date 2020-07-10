package core;

import core.base.ScreenshotTestRule;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestBase {
    public WebDriver driver;

    @Before
    public void setUp() {
        openBrowser();

    }

    public void tearDown() {
        stop();
    }

    /**
     * Delete all cookies at the start of each scenario to avoid
     */
    public void openBrowser() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            browser = System.getenv("BROWSER");
            if (browser == null) {
                browser = "chrome";
            }
        }
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver");
                this.driver = new ChromeDriver();


                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/test/java/drivers/geckodriver");
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }

        System.out.println("Opening Browser.... : " + browser);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://hepsiburada.com/");
    }

    public void stop() {
        driver.quit();
    }
}
