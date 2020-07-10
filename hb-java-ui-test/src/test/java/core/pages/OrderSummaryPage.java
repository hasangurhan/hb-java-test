package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

/**
 * Order Summary Page - https://www.hepsiburada.com/ayagina-gelsin/siparis-ozeti
 */
public class OrderSummaryPage extends PageBase {

    private static final By ORDER_SUMMARY = By.id("short-summary");
    private static final By CONTRACT_BLOCK = By.className("eula");
    private static final By DELIVERY_INFORM = By.cssSelector("div > section:nth-child(3)");
    private static final By PAYMENT_INFORM = By.cssSelector("div > section:nth-child(4)");
    private static final By COMPLETE_ORDER_BUTTON = By.className("icon-chevron-right");

    public OrderSummaryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {

        Assert.assertTrue("Order Summary are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(ORDER_SUMMARY), 5, 500));
        Assert.assertTrue("Contract block is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CONTRACT_BLOCK), 5, 500));
        Assert.assertTrue("Delivery Information blocks are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(DELIVERY_INFORM), 5, 500));
        Assert.assertTrue("Payment Information blocks are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(PAYMENT_INFORM), 5, 500));

    }

    /**
     * Clicks 'Siparisi Onayla' Button
     *
     * @return OrderCompletedPage
     */
    public OrderCompletedPage goToOrderCompletedPage() {
        find(COMPLETE_ORDER_BUTTON).click();
        return new OrderCompletedPage(driver);
    }

    /**
     * Clicks contract checkbox with use javascript executor
     */
    public void clickAgreedCheckbox() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"label[for=contract-agreed]\").click()");
    }

}
