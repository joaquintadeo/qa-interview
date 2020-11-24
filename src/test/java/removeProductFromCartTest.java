import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static utils.commonFunctions.*;

public class removeProductFromCartTest {

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
    public static void removeProductFromCartTest(){
        searchProductFromSection("T-shirts");
        checkProductAddedToCart("(//ul[@class='product_list grid row']//*[@class='product-name'])[1]");
        emptyCart();

    }

    private static void emptyCart(){
        int delete = getDriver().findElements(By.xpath("//a[@title='Delete']")).size();
        for (int i = 0; i < delete; i++){
            getDriver().findElement(By.xpath("//a[@title='Delete']")).click();
        }
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your shopping cart is empty.')]")));
    }
}
