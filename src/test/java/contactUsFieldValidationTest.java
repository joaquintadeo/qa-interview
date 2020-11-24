import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static utils.commonFunctions.*;

public class contactUsFieldValidationTest {

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
    public static void contactUsFieldValidationTest(){
        contactUsEmailValidation();
        contactUsMessageValidation();
        contactUsSubjectValidation();
    }

    private static void contactUsEmailValidation() {
        goToContactUs();
        WebElement submit = getContactElement("submitMessage");
        submit.click();
        verifyWarningMessage("Invalid email address.");
        WebElement email = getDriver().findElement(By.id("email"));
        email.sendKeys("qa");
        WebElement reference = getDriver().findElement(By.id("id_order"));
        reference.click();
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='form-group form-error']")));
        email.sendKeys("qa@interview.com");
        reference.click();
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='form-group form-ok']")));
    }

    private static void contactUsMessageValidation(){
        goToContactUs();
        WebElement email = getContactElement("email");
        email.sendKeys("qa@interview.com");
        WebElement submit = getDriver().findElement(By.id("submitMessage"));
        submit.click();
        verifyWarningMessage("The message cannot be blank.");
        WebElement message = getDriver().findElement(By.id("message"));
        message.sendKeys("This is a qa interview");
    }

    private static void contactUsSubjectValidation(){
        goToContactUs();
        WebElement email = getDriver().findElement(By.id("email"));
        email.sendKeys("qa@interview.com");
        WebElement message = getDriver().findElement(By.id("message"));
        message.sendKeys("This is a qa interview");
        WebElement submit = getDriver().findElement(By.id("submitMessage"));
        submit.click();
        verifyWarningMessage("Please select a subject from the list provided.");
    }
}
