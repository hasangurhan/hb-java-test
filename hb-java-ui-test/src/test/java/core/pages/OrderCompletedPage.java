package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

/**
 * Order Completed Page - https://www.hepsiburada.com/ayagina-gelsin/siparis-tamamlandi
 */
public class OrderCompletedPage extends PageBase {

    private static final By MY_ORDERS_BUTTON = By.className("link-type-one");
    private static final By BANK_INFORMATION_BLOCK = By.className("help-box");
    private static final By MONEY_TRANSFER_DESCRIPTION_BLOCK = By.className("money-order-desc");
    private static final By ORDER_INFORMATION_BLOCK = By.className("order-info-container");
    private static final By ORDER_RESULT = By.xpath("//h1[@class = 'box-header-title']");
    private static final By ORDER_SUCCESS_MESSAGE = By.xpath("//p//strong");


    public OrderCompletedPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {

        Assert.assertTrue("'Siparislerim' button is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MY_ORDERS_BUTTON), 5, 500));
        Assert.assertTrue("Bank information block is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BANK_INFORMATION_BLOCK), 5, 500));
        Assert.assertTrue("Money transfer description blocks are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MONEY_TRANSFER_DESCRIPTION_BLOCK), 5, 500));
        Assert.assertTrue("Order information block is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(ORDER_INFORMATION_BLOCK), 5, 500));
    }

    /**
     * Clicks "Siparislerim" button
     *
     * @return BankTransferPopup
     */
    public MyOrdersDetailsPage goToMyOrdersDetailsPage() {
        find(MY_ORDERS_BUTTON).click();
        return new MyOrdersDetailsPage(driver);
    }

    /**
     * Gets order results text
     *
     * @return result text
     */
    public String getOrderResultMessage() {
        return find(ORDER_RESULT).getText();
    }

    /**
     * Gets order success message description
     *
     * @return success description
     */
    public String getOrderSuccessMessage() {
        return find(ORDER_SUCCESS_MESSAGE).getText();
    }

}
