package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

/**
 * Payment Page - https://www.hepsiburada.com/ayagina-gelsin/odeme
 */
public class PaymentInformationPage extends PageBase {

    private static final By ORDER_SUMMARY = By.id("short-summary");
    private static final By BANK_CAMPAIGN_BLOCK = By.id("bank-campaigns-container");
    private static final By PAYMENT_TITLE = By.className("payment-title");
    private static final By PAYMENT_TYPE_BLOCK = By.className("accordion-container");
    private static final By TRANSFER_PAYMENT = By.className("paymentType-2");
    private static final By NAVIGATION_BLOCK = By.className("checkout-navigation-container");
    private static final By PAYMENT_WITH_AKBANK = By.className("checkout-navigation-container");
    private static final By COMPLETE_ORDER_BUTTON = By.className("proceed-container");
    private static final By AKBANK_TRANSFER_BUTTON = By.className("bank-5");


    public PaymentInformationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {

        Assert.assertTrue("Banks campaign blocks are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BANK_CAMPAIGN_BLOCK), 5, 500));
        Assert.assertTrue("Order Summary are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(ORDER_SUMMARY), 5, 500));
        Assert.assertTrue("Payment title is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(PAYMENT_TITLE), 5, 500));
        Assert.assertTrue("Payment type blocks are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(PAYMENT_TYPE_BLOCK), 5, 500));
        Assert.assertTrue("Transfer Payment is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(TRANSFER_PAYMENT), 5, 500));
        Assert.assertTrue("Navigation blocks is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(NAVIGATION_BLOCK), 5, 500));

    }

    /**
     * Clicks "Devam et" button
     *
     * @return BankTransferPopup
     */
    public BankTransferPopup goToBankTransferPopup() {
        find(COMPLETE_ORDER_BUTTON).click();
        return new BankTransferPopup(driver);
    }

    /**
     * Clicks "Havale" tab and clicks 'Akbank' option
     */
    public void clickTransferWithAkbank() {
        find(TRANSFER_PAYMENT).click();
        find(AKBANK_TRANSFER_BUTTON).click();
    }
}
