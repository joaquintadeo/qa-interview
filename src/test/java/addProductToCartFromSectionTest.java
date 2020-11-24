import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static utils.commonFunctions.*;

public class addProductToCartFromSectionTest {

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
    public static void addProductToCartFromSectionTest(){
        searchProductFromSection("T-shirts");
        addProductToCart("(//ul[@class='product_list grid row']//*[@class='product-name'])[1]");
    }
}
