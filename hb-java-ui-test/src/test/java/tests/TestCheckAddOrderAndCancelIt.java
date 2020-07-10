package tests;

import core.TestBase;
import core.base.ScreenshotTestRule;
import core.pages.*;
import helper.Logger;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.File;


public class TestCheckAddOrderAndCancelIt extends TestBase {

    private static final String USERNAME = "gurhanhasann@gmail.com";
    private static final String PASSWORD = "Hhasan0134";
    private static final String SUCCESS_ADDED_CART_MESSAGE = "Ürün başarılı bir şekilde sepete eklenmiştir.";
    private static final String BESTSELLER_BOOK_BREADCRUMB = "Çok Satan Kitaplar";
    private static final String ORDER_COMPLETED_MESSAGE = "Siparişiniz Alındı";
    private static final String ORDER_SUCCESS_MESSAGE = "Alışverişlerinizde Hepsiburada’yı tercih ettiğiniz için teşekkür ederiz.";
    private static final String ORDER_CANCEL_MESSAGE = "Bu ürün iptal edildi";

    /**
     * Test Case:
     * 1. Check the Homepage and check that everything is working properly
     * 2. Log in to Hepsiburada site
     * 3. Go to the bestselling book category and choose a random book
     * 4. Add randomly selected book to cart page
     * 5. Go to Cart Page and pay the book by transfer with selected Bank
     * 6. Then cancel the order and check that it has been canceled
     */
    //@Rule
    //public ScreenshotTestRule failure = new ScreenshotTestRule(driver);

    @Test
    public void TestCheckAddOrderAndCancel() {
        Logger.info("1. Check the Homepage and check that everything is working properly");
        MainPage mainPage = new MainPage(driver);
        Logger.info("All the elements are in right place on Main Page!");

        Logger.info("2. Log in to Hepsiburada site");
        LoginPopup loginPopup = mainPage.clickLogInBtn();
        loginPopup.fillLoginForm(USERNAME, PASSWORD).submitLogIn();
        Logger.info("Login to the site successfully!");

        Logger.info("3. Go to the bestselling book category and choose a random book");
        BestSellingBooksCategoryPage bestSellerPage = mainPage.goToBestSellingBooks();
        Assert.assertEquals("In best seller page, breadcrumb comes wrong!",
                BESTSELLER_BOOK_BREADCRUMB, bestSellerPage.getCurrentBreadcrumbs());
        ProductDetailsPage productDetailsPage = bestSellerPage.clickRandomProduct();
        String productName = productDetailsPage.getProductName();
        Logger.info("Bestselling category was visited and a random book was selected");

        Logger.info("4. Add randomly selected book to cart page");
        productDetailsPage.addToBasket();
        Assert.assertEquals("In product details page, basket message comes wrong!",
                SUCCESS_ADDED_CART_MESSAGE, productDetailsPage.getAddedToBasketMessage());
        Assert.assertEquals("In basket preview, products item count comes wrong!",
                "1", productDetailsPage.getBasketCount());
        Logger.info("A random book was added to the cart!");

        Logger.info("5. Go to Cart Page and pay the book by transfer with selected Bank");
        CartPage cartPage = productDetailsPage.goToCartPage();
        Assert.assertEquals("The product name in the product details page and the product name in the basket are not similar!",
                productName, cartPage.getProductName());
        DeliveryInformationPage deliveryInformationPage = cartPage.goToDeliveryPage();
        PaymentInformationPage paymentInformationPage = deliveryInformationPage.goToPaymentInformationPage();
        paymentInformationPage.clickTransferWithAkbank();
        BankTransferPopup bankTransferPopup = paymentInformationPage.goToBankTransferPopup();
        OrderSummaryPage orderSummaryPage = bankTransferPopup.goToOrderSummaryPage();
        orderSummaryPage.clickAgreedCheckbox();
        OrderCompletedPage orderCompletedPage = orderSummaryPage.goToOrderCompletedPage();
        Assert.assertEquals("Order result message comes wrong in order completed page!",
                ORDER_COMPLETED_MESSAGE, orderCompletedPage.getOrderResultMessage());
        Assert.assertEquals("Order success message comes wrong in order completed page!",
                ORDER_SUCCESS_MESSAGE, orderCompletedPage.getOrderSuccessMessage());
        MyOrdersDetailsPage myOrdersDetailsPage = orderCompletedPage.goToMyOrdersDetailsPage();
        Logger.info("Order completed with transfer payment option");

        Logger.info("6. Then cancel the order and check that it has been canceled");
        CancelOrderPopup cancelOrderPopup = myOrdersDetailsPage.goToCancelOrderPopup();
        cancelOrderPopup.selectCancelReason().clickCancelOrderButton();
        cancelOrderPopup.clickCloseButton();
        driver.navigate().refresh();
        driver.navigate().refresh();
        Assert.assertEquals("In order details page, cancel message comes wrong!",
                ORDER_CANCEL_MESSAGE, myOrdersDetailsPage.getOrderCancelMessage());
        Logger.info("Order canceled successfully");
    }

    @After
    public void quitDriver() { tearDown(); }
}
