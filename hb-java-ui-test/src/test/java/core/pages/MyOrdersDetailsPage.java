package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

/**
 * Order details - https://www.hepsiburada.com/siparislerim/detay/{id}
 */
public class MyOrdersDetailsPage extends PageBase {

    private static final By CANCEL_ORDERS_BUTTON = By.className("hb-button--secondary");
    private static final By ORDER_STATE_BAR  = By.className("state-bar");
    private static final By PAYMENT_ORDER_BLOCK = By.className("order-information__column--payment");
    private static final By ORDER_ADDRESS_BLOCK = By.className("order-information__column--addresses");
    private static final By CANCEL_ORDER_MESSAGE = By.xpath("//*[@class='title']//b");

    public MyOrdersDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {

        Assert.assertTrue("'Iptal et' button is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CANCEL_ORDERS_BUTTON), 5, 500));
        Assert.assertTrue("Order state bar is not visible!",
               explicitWait(ExpectedConditions.visibilityOfElementLocated(ORDER_STATE_BAR), 5, 500));
        Assert.assertTrue("Payment blocks are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(PAYMENT_ORDER_BLOCK), 5, 500));
        Assert.assertTrue("Address block is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(ORDER_ADDRESS_BLOCK), 5, 500));
    }
    /**
     * Clicks 'Iptal Et' Button
     *
     * @return CancelOrderPopup
     */
    public CancelOrderPopup goToCancelOrderPopup() {
        find(CANCEL_ORDERS_BUTTON).click();
        return new CancelOrderPopup(driver);
    }
    /**
     * Gets order cancel message when order has been canceled
     *
     * @return canceled message
     */
    public String getOrderCancelMessage() {
        pause(1000);
        return find(CANCEL_ORDER_MESSAGE).getText();
    }

}
