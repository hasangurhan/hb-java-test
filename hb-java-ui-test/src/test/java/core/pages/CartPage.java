package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

/**
 * Cart Page - https://www.hepsiburada.com/ayagina-gelsin/sepetim
 */
public class CartPage extends PageBase {

    private static final By CART_TITLE = By.className("cart-title");
    private static final By CART_ITEM_LIST = By.className("cart-item-list");
    private static final By ORDER_SUMMARY = By.id("short-summary");
    private static final By COMPLETE_ORDER_BUTTON = By.className("btn-primary");
    private static final By BACK_TO_SHOPPING_BUTTON = By.className("btn-secondary");
    private static final By TAKE_IT_LATER_BUTTON = By.className("btn-saved-cart");
    private static final By ORDER_DELETE_BUTTON = By.className("btn-delete");
    private static final By QUANTITY_BLOCK = By.className("quantity-wrapper");
    private static final By PRODUCT_NAME = By.className("product-name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {

        Assert.assertTrue("Cart title is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CART_TITLE), 5, 500));
        Assert.assertTrue("Order Summary are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(ORDER_SUMMARY), 5, 500));
        Assert.assertTrue("Complete order button is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(COMPLETE_ORDER_BUTTON), 5, 500));
        Assert.assertTrue("Back to shipping button is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BACK_TO_SHOPPING_BUTTON), 5, 500));
        Assert.assertTrue("Order delete button is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(ORDER_DELETE_BUTTON), 5, 500));
        Assert.assertTrue("Take it later button is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(TAKE_IT_LATER_BUTTON), 5, 500));
        Assert.assertTrue("Cart item list are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CART_ITEM_LIST), 5, 500));
        Assert.assertTrue("Quantity blocks are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(QUANTITY_BLOCK), 5, 500));
    }

    /**
     * Clicks complete order button
     *
     * @return DeliveryInformationPage
     */
    public DeliveryInformationPage goToDeliveryPage() {
        find(COMPLETE_ORDER_BUTTON).click();
        return new DeliveryInformationPage(driver);
    }

    /**
     * Get product name
     *
     * @return product name
     */
    public String getProductName() {
        pause(1000);
        return find(PRODUCT_NAME).getText();
    }
}
