package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

/**
 * Cancel Order Popup will visible when click 'iptal et' button
 */
public class CancelOrderPopup extends PageBase {

    private static final By CANCEL_ORDERS_POPUP = By.className("ReactModal__Content");
    private static final By CANCEL_REASON_DROPDOWN = By.className("selectbox-placeholder");
    private static final By SELECT_CANCEL_REASON = By.xpath("//*[@class='selectbox-result__list']//li[@tabindex='1']");
    private static final By ORDER_ADDRESS_BLOCK = By.className("order-information__column--addresses");
    private static final By CANCEL_ORDER_BUTTON = By.xpath("//*[contains(@class, 'hb-button--primary')][text() = 'Seçili Ürünleri İptal Et']");
    private static final By CLOSE_BUTTON = By.className("modal__body__close-button");

    public CancelOrderPopup(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {

        Assert.assertTrue("Cancel orders popup is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CANCEL_ORDERS_POPUP), 5, 500));
        Assert.assertTrue("Dropdown is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CANCEL_REASON_DROPDOWN), 5, 500));
        Assert.assertTrue("Address block is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(ORDER_ADDRESS_BLOCK), 5, 500));
        Assert.assertTrue("Cancel order button is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CANCEL_ORDER_BUTTON), 5, 500));
    }

    /**
     * Clicks 'Seçili ürünleri iptal et' Button
     */
    public void clickCancelOrderButton() {
        find(CANCEL_ORDER_BUTTON).click();
    }

    /**
     * Click close button on the popup
     *
     * @return MyOrdersDetailsPage
     */
    public MyOrdersDetailsPage clickCloseButton() {
        find(CLOSE_BUTTON).click();
        return new MyOrdersDetailsPage(driver);
    }

    /**
     * Selects dropdown
     */
    public void selectDropdown() {
        find(CANCEL_REASON_DROPDOWN).click();
    }

    /**
     * Selects reason for cancellation
     */
    public CancelOrderPopup selectCancelReason() {
        selectDropdown();
        find(SELECT_CANCEL_REASON).click();
        return this;
    }


}
