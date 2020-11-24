import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static utils.commonFunctions.*;

public class productShouldBeAddedFromSeactionToCartTest {

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
    public static void productShouldBeAddedFromSearchToCartTest(){
        searchProductFromSection("T-shirts");
        checkProductAddedToCart("(//ul[@class='product_list grid row']//*[@class='product-name'])[1]");
    }
}
