package FullStackAutomation.androidTests;

import FullStackAutomation.androidSetup.AndroidBaseTest_eCommerce;
import FullStackAutomation.pageObject.android.CheckOutPage;
import FullStackAutomation.pageObject.android.ItemsPage;
import FullStackAutomation.pageObject.android.LandingPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class E_CommerceApplication extends AndroidBaseTest_eCommerce {

    @Test(enabled = false)
    public void addDetailsOnLandingPage() throws InterruptedException {
        Thread.sleep(7000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.androidsample.generalstore:id/nameField']"))));
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vijay Emulator");
        driver.hideKeyboard();
//        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.xpath("//android.widget.Spinner[@package='com.androidsample.generalstore']")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
    }
    @Test(enabled = false)
    public void checkToastMessageOnLandingPage() throws InterruptedException {
        Thread.sleep(7000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.Toast)[1]")));
        Assert.assertEquals(driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getDomAttribute("name"),"Please enter your name");

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vijay Emulator");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data= getJsonData(System.getProperty("user.dir")+"/src/test/java/FullStackAutomation/testData/E_CommerceApplication/E_CommerceApplication.json");
//        return new Object [][]{{"Vijay Emulator Test","female","Argentina"},{"Just the Emulator","male","India"}};
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }

    @Test(dataProvider = "getData",groups = "Regression")
    public void addItemsToCart(HashMap<String, String> addItemsTestData) throws InterruptedException {
        Thread.sleep(7000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        LandingPage landingPage = new LandingPage(driver);
        landingPage.addDetailsToNameField(addItemsTestData.get("addTextDetails"));
        landingPage.selectFemaleRadioButton();
        ItemsPage itemsPage=landingPage.clickOnLetsShopButton();

        ArrayList<String> itemsToAdd= new ArrayList<>(Arrays.asList("Converse All Star","PG 3"));
        itemsPage.scrollIntoViewOfElementText(itemsToAdd.get(0));
//        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+itemsToAdd.get(0)+"\"));"));
        List<WebElement> allProductsInView= itemsPage.getProductsInView();
        for(int i=0;i<allProductsInView.size();i++){
            String eachProductName= allProductsInView.get(i).getText();
            if(eachProductName.equalsIgnoreCase(itemsToAdd.get(0))){
                itemsPage.getListOfAddToCartBtn().get(i).click();
            }
        }
        itemsPage.scrollIntoViewOfElementText(itemsToAdd.get(1));
        List<WebElement> allProductsInView_2= itemsPage.getProductsInView();
        for(int i=0;i<allProductsInView_2.size();i++){
            String eachProductName= allProductsInView_2.get(i).getText();
            if(eachProductName.equalsIgnoreCase(itemsToAdd.get(1))){
                itemsPage.getListOfAddToCartBtn().get(i).click();
            }
        }

       CheckOutPage checkOutPage= itemsPage.clickOnViewCartBtn();

        // check the items is available in cart as well
        wait.until(ExpectedConditions.visibilityOf(checkOutPage.proceedButton()));
        for(int i=0;i<checkOutPage.listOfProductNamesOnCard().size();i++){
            Assert.assertEquals(checkOutPage.listOfProductNamesOnCard().get(i).getText(),itemsToAdd.get(i));
        }

        List<WebElement> allProductsPrices= checkOutPage.listOfProductPricesOnCard();
        float actSum=0;
        for(int i=0;i<allProductsPrices.size();i++){
            String val= allProductsPrices.get(i).getText().replace("$","");
            actSum=actSum+ Float.parseFloat(val);
        }

        String expSum= checkOutPage.getTotalProductCostInCart();
        Assert.assertEquals(expSum,"$ "+String.valueOf(actSum));

        longPressOnElement(checkOutPage.getTermsAndConditions());
        wait.until(ExpectedConditions.visibilityOf(checkOutPage.getAlertTitle()));
        checkOutPage.clickOnGetAlertCloseButton();
        checkOutPage.clickOnAgreeCheckBox();
        checkOutPage.clickOnProceedButton();
        Thread.sleep(5000);

        // hybrid app browser code below
        Set<String> allContext=driver.getContextHandles();
        for(String eachContext: allContext){
            System.out.println("Context details  "+ eachContext);
        }
        driver.context("WEBVIEW_com.androidsample.generalstore");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("q"))));
        driver.findElement(By.name("q")).sendKeys("appium");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");

    }
}
