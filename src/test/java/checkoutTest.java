import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static utils.commonFunctions.*;

public class checkoutTest {

    @BeforeClass
    public static void setupClass() {
        openBrowserChrome();
        goToSite(ecommerceUrl);
        verifyLandingPage();
    }

    @AfterClass
    public static void tearDown() {
        closeBrowser();
    }

    @Test
    public static void checkoutTest(){
        searchProduct("Printed");
        checkProductAddedToCart("(//ul[@class='product_list grid row']//*[@class='product-name'])[1]");
        checkout();

    }

    private static void checkout(){
        WebElement proceed = getDriver().findElement(By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']"));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(proceed).perform();
        proceed.click();
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("authentication")));
    }
}
