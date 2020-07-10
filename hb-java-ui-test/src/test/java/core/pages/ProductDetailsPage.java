package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

/**
 * Product Details Page
 */
public class ProductDetailsPage extends PageBase {

    private static final By NOTIFICATION_MESSAGE = By.id("notification");
    private static final By ADD_TO_CART_BTN = By.id("addToCart");
    private static final By CART_ITEM_COUNT = By.id("cartItemCount");
    private static final By SHOPPING_CART = By.id("shoppingCart");
    private static final By PRODUCT_NAME = By.id("product-name");
    private static final By PRODUCT_IMAGE = By.id("productDetailsCarousel");
    private static final By SELLER_BLOCK = By.className("seller-container");
    private static final By QUANTITY_BLOCK = By.id("quantity");
    private static final By PRODUCT_DETAIL_RIGHT = By.className("productDetailRight");


    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        Assert.assertTrue("Product image is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(PRODUCT_IMAGE), 5, 500));
        Assert.assertTrue("Seller text is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(SELLER_BLOCK), 5, 500));
        Assert.assertTrue("Quantity blocks are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(QUANTITY_BLOCK), 5, 500));
        Assert.assertTrue("Product details blocks are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(PRODUCT_DETAIL_RIGHT), 5, 500));
        Assert.assertTrue("Add to cart button is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(ADD_TO_CART_BTN), 5, 500));

    }

    /**
     * Adds the product to the basket
     */
    public void addToBasket() {
        Assert.assertTrue("'Add to basket' button isn't visible!", isElementPresent(ADD_TO_CART_BTN));
        find(ADD_TO_CART_BTN).click();
        Assert.assertTrue("Basket preview and product count bubble in basket preview aren't visible!",
                isElementPresent(CART_ITEM_COUNT) && isElementPresent(SHOPPING_CART));
    }

    /**
     * Go to cart page
     */
    public CartPage goToCartPage() {
        pause(1000);
        find(SHOPPING_CART).click();
        return new CartPage(driver);
    }

    /**
     * Get and return basket view count
     *
     * @return added basket count
     */
    public String getBasketCount() {
        driver.navigate().refresh();
        pause(5000);
        return find(CART_ITEM_COUNT).getText();
    }

    /**
     * Get and return basket product bubble count
     *
     * @return message when product add to cart successfully
     */
    public String getAddedToBasketMessage() {
        pause(1000);
        Assert.assertTrue("'Add to basket' button isn't visible!", isElementPresent(NOTIFICATION_MESSAGE));
        return find(NOTIFICATION_MESSAGE).getText();
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
