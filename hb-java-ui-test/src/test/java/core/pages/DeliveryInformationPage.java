package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

/**
 * Delivery Information Page - https://www.hepsiburada.com/ayagina-gelsin/teslimat
 */
public class DeliveryInformationPage extends PageBase {

    private static final By COMPLETE_ORDER_BUTTON = By.className("proceed-container");
    private static final By DELIVERY_ADDRESS_BLOCK = By.className("delivery-addresses");
    private static final By INVOICE_ADDRESS_BLOCK = By.className("invoice-addresses");
    private static final By SHIPPING_LIST = By.className("shipping-list-group");
    private static final By ORDER_SUMMARY = By.id("short-summary");

    public DeliveryInformationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {

        Assert.assertTrue("Address blocks are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(DELIVERY_ADDRESS_BLOCK), 5, 500));
        Assert.assertTrue("Order Summary is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(ORDER_SUMMARY), 5, 500));
        Assert.assertTrue("'Devam et' button is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(COMPLETE_ORDER_BUTTON), 5, 500));
        Assert.assertTrue("'Fatura Bilgileri' blocks is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(INVOICE_ADDRESS_BLOCK), 5, 500));
        Assert.assertTrue("Delivery Blocks is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(SHIPPING_LIST), 5, 500));
    }

    /**
     * Clicks "Devam et" button
     *
     * @return Payment Information Page
     */
    public PaymentInformationPage goToPaymentInformationPage() {
        find(COMPLETE_ORDER_BUTTON).click();
        return new PaymentInformationPage(driver);
    }
}
