import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static utils.commonFunctions.*;

public class productSearchTest {

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
    public static void productSearchTest(){
        searchProduct("Printed");

    }

    private static void searchProduct(String product){
        WebElement searchQuery = getDriver().findElement(By.id("search_query_top"));
        searchQuery.sendKeys(product);
        searchQuery.submit();
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.className("compare-form")));
        int productsCount = getDriver().findElements(By.xpath("//ul[@class='product_list grid row']//*[@class='product-name']")).size();
        for (int i=1; i <= productsCount; i++){
            String productsTitle = getDriver().findElement(By.xpath("(//ul[@class='product_list grid row']//*[@class='product-name'])[" + i + "]")).getText();
            boolean result = productsTitle.contains(product);
            Assert.assertEquals(result, true);
        }
    }
}
