package core.pages;

import core.base.PageBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends PageBase {

    private static final By SEARCH_INPUT = By.className("desktopOldAutosuggestTheme-input");
    private static final By LOGO_ICON = By.className("hbLogo");
    private static final By ACCOUNT_BTN = By.id("myAccount");
    private static final By TAB_NAVIGATION = By.className("navigationBar");
    private static final By BASKET_BTN = By.id("shoppingCart");
    private static final By CAMPAIGN_SLIDER = By.id("herousel_3");
    private static final By FOOTER = By.className("footer-global");
    private static final By LOGIN_BTN = By.id("login");
    private static final By CATEGORY = By.xpath(".//*[contains(@class, 'MenuItems-5_2RN')]//span");
    private static final By BEST_SELLER_BOOKS = By.xpath(".//*[contains(@href, 'cok-satan-kitaplar')]");


    public MainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        Assert.assertTrue("Search input isn't visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT), 5, 500));

        Assert.assertTrue("navigation block isn't visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(TAB_NAVIGATION), 5, 500));

        Assert.assertTrue("Hepsiburada logo isn't visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(LOGO_ICON), 5, 500));

        Assert.assertTrue("Account button isn't visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_BTN), 5, 500));

        Assert.assertTrue("Basket button isn't visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BASKET_BTN), 5, 500));

        Assert.assertTrue("Campaign slider isn't visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CAMPAIGN_SLIDER), 5, 500));

        Assert.assertTrue("Footer isn't visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(FOOTER), 5, 500));

    }

    /**
     * Click Account button and enter Log in popup
     *
     * @return LoginPopup
     */
    public LoginPopup clickLogInBtn() {

        find(ACCOUNT_BTN).click();
        find(LOGIN_BTN).click();
        return new LoginPopup(driver);
    }

    /**
     * Get all tab categories
     *
     * @return all category
     */
    public List<WebElement> getCategories() {
        return findElements(CATEGORY);
    }

    /**
     * Select Best seller books category tab
     *
     * @return Best Seller Books Page
     */
    public BestSellingBooksCategoryPage goToBestSellingBooks() {

        List<WebElement> categories = getCategories();
        categories.get(8).click();
        find(BEST_SELLER_BOOKS).click();
        return new BestSellingBooksCategoryPage(driver);
    }

}
