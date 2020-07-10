package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

import java.util.List;
import java.util.Random;

/**
 * Best seller Books Category Page - https://www.hepsiburada.com/kampanyalar/cok-satan-kitaplar
 */
public class BestSellingBooksCategoryPage extends PageBase {

    private static final By PRODUCT = By.className("product");
    private static final By CURRENT_BREADCRUMBS = By.xpath(".//*[contains(@title, 'Çok Satan Kitaplar')]");
    private static final By FILTER_TAB = By.className("tabs");
    private static final By FILTER_BOX = By.className("filter-box-container");

    public BestSellingBooksCategoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        Assert.assertTrue("Products are not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(PRODUCT), 5, 500));
        Assert.assertTrue("Current Breadcrumbs is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CURRENT_BREADCRUMBS), 5, 500));
        Assert.assertTrue("Filter tab is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(FILTER_TAB), 5, 500));
        Assert.assertTrue("Filter Box is not visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(FILTER_BOX), 5, 500));
    }

    /**
     * Get all products list
     *
     * @return products
     */
    public List<WebElement> getProducts() {
        return findElements(PRODUCT);
    }

    /**
     * Choose random product
     *
     * @return ProductDetailsPage
     */
    public ProductDetailsPage clickRandomProduct() {
        List<WebElement> products = getProducts();
        int randomValue = new Random().nextInt(products.size());
        products.get(randomValue).click();
        return new ProductDetailsPage(driver);
    }

    /**
     * Get current breadcrumbs text for related page
     */
    public String getCurrentBreadcrumbs() {
        pause(1000);
        return find(CURRENT_BREADCRUMBS).getText();
    }
}
