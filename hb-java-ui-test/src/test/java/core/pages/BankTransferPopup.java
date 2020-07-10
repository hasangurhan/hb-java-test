package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

/**
 * Bank Transfer Popup
 */
public class BankTransferPopup extends PageBase {


    private static final By BANK_TRANSFER_POPUP = By.className("eft-context");
    private static final By BANK_LOGO = By.className("bank-logo");
    private static final By EFT_BUTTON = By.className("eft-button");
    private static final By EFT_WITH_IBAN_BUTTON = By.className("iban-eft");
    private static final By CLOSE_BUTTON = By.className("fancybox-close");
    private static final By CONTINUE_BUTTON = By.className("long");

    public BankTransferPopup(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {

        Assert.assertTrue("Products are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BANK_TRANSFER_POPUP), 5, 500));
        Assert.assertTrue("Order Summary are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BANK_LOGO), 5, 500));
        Assert.assertTrue("Order Summary are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(EFT_BUTTON), 5, 500));
        Assert.assertTrue("Order Summary are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(EFT_WITH_IBAN_BUTTON), 5, 500));
        Assert.assertTrue("Order Summary are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CLOSE_BUTTON), 5, 500));
    }

    /**
     * Get and return basket product bubble count
     */
    public OrderSummaryPage goToOrderSummaryPage() {
        find(EFT_WITH_IBAN_BUTTON).click();
        find(CONTINUE_BUTTON).click();
        return new OrderSummaryPage(driver);
    }
}
