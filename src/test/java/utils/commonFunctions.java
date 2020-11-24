package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class commonFunctions {

    private static WebDriver driver;
    private static WebDriverWait wait;
    public static final String ecommerceUrl = "http://automationpractice.com/";
    private static final String siteIndex = "index";
    private static final String searchBarr = "search_query_top";
    private static String selectedProductName = null;
    private static String compareButton = "compare-form";
    private static String firstAddButton = "(//*[@class='button ajax_add_to_cart_button btn btn-default'])[1]";
    private static String okIcon = "icon-ok";
    private static String viewCart = "//a[@title='View my shopping cart']";
    private static String cartItemValue = "//td[@class='cart_quantity text-center']//input[@type='hidden']";
    private static String contactLink = "contact-link";
    private static String contactForm = "contact-form-box";
    private static String warningMsg = "//ol//li";

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        wait = new WebDriverWait(getDriver(), 10);
        return wait;
    }

    public static void openBrowserChrome(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    public static void closeBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    public static void goToSite(String url){
        driver.get(url);
    }

    public static void verifyLandingPage(){
        getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(siteIndex)));
    }

    public static void searchProduct(String product){
        WebElement searchQuery = getDriver().findElement(By.id(searchBarr));
        searchQuery.sendKeys(product);
        searchQuery.submit();
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.className(compareButton)));
    }

    public static void searchProductFromSection(String section){
        WebElement selectedSection = getDriver().findElement(By.xpath("(//a[@title='" + section + "'])[2]"));
        selectedSection.click();
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("center_column")));
    }

    public static void addProductToCart(String firstResult){
        WebElement selectedProduct = getDriver().findElement(By.xpath(firstResult));
        selectedProductName = selectedProduct.getText();
        Actions actions = new Actions(getDriver());
        actions.moveByOffset(234,100);
        actions.moveToElement(selectedProduct).perform();
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(firstAddButton)));
        WebElement addToCartFirstProduct = getDriver().findElement(By.xpath(firstAddButton));
        addToCartFirstProduct.click();
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.className(okIcon)));
    }

    public static void checkProductAddedToCart(String firstResult){
        addProductToCart(firstResult);
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.className("cross")));
        WebElement closeWindow = getDriver().findElement(By.className("cross"));
        closeWindow.click();
        WebElement cart = getDriver().findElement(By.xpath(viewCart));
        cart.click();
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("cart_title")));
        String productAddedToCart = getDriver().findElement(By.xpath("//td[@class='cart_description']//p[@class='product-name']")).getText();
        Assert.assertEquals(productAddedToCart, selectedProductName);
        String quantity = getDriver().findElement(By.xpath(cartItemValue)).getAttribute("value");
        Assert.assertNotEquals(quantity, "0");
    }

    public static void goToContactUs(){
        WebElement contactUs = getDriver().findElement(By.id(contactLink));
        contactUs.click();
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.className(contactForm)));
    }

    public static WebElement getContactElement(String locator){
        WebElement element = getDriver().findElement(By.id(locator));
        return element;
    }

    public static void verifyWarningMessage(String msg){
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(warningMsg)));
        WebElement warning = getDriver().findElement(By.xpath(warningMsg));
        String warningMessage = warning.getText();
        Assert.assertEquals(warningMessage, msg);
    }
}
