package core.pages;

import core.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;


/**
 * Login popup visible after Account button is clicked
 */
public class LoginPopup extends PageBase {

    private static final By EMAIL_INPUT = By.id("txtUserName");
    private static final By PASSWORD_INPUT = By.id("txtPassword");
    private static final By FORGOT_PASSWORD_LINK = By.xpath(".//*[contains(text(), 'Şifremi unuttum')]");
    private static final By SUBMIT_LOGIN_BUTTON = By.id("btnLogin");
    private static final By LOGIN_BY_GOOGLE = By.id("btnGoogle");
    private static final By REGISTER_LINK = By.xpath(".//*[contains(text(), 'Üye ol')]");

    public LoginPopup(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        Assert.assertTrue("Email input isn't visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT), 5, 500));
        Assert.assertTrue("Password input isn't visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT), 5, 500));
        Assert.assertTrue("'Forgot password' link isn't visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(FORGOT_PASSWORD_LINK), 5, 500));
        Assert.assertTrue("'Submit log in' button isn't visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(SUBMIT_LOGIN_BUTTON), 5, 500));
        Assert.assertTrue("Google log in option isn't visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(LOGIN_BY_GOOGLE), 5, 500));
        Assert.assertTrue("Sign in link isn't visible!",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(REGISTER_LINK), 5, 500));
    }

    /**
     * Fill all inputs to log in
     *
     * @return LoginPopup
     */
    public LoginPopup fillLoginForm(String email, String password) {
        Assert.assertTrue("Email input isn't visible!", isElementPresent(EMAIL_INPUT));
        type(EMAIL_INPUT, email);
        Assert.assertTrue("Password input isn't visible!", isElementPresent(PASSWORD_INPUT));
        type(PASSWORD_INPUT, password);
        pause(1000);
        return this;
    }

    /**
     * Click Submit button for log in
     *
     * @return MainPage
     */
    public MainPage submitLogIn() {
        Assert.assertTrue("Submit login button isn't visible!", isElementPresent(SUBMIT_LOGIN_BUTTON));
        find(SUBMIT_LOGIN_BUTTON).click();
        pause(1000);
        return new MainPage(driver);
    }
}
