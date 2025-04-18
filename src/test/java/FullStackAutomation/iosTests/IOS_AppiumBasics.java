package FullStackAutomation.iosTests;

import FullStackAutomation.appiumSetup.IOS_BaseTest;
import FullStackAutomation.pageObject.ios.AlertViewPage;
import FullStackAutomation.pageObject.ios.HomePage;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

public class IOS_AppiumBasics extends IOS_BaseTest {

    @Test(enabled = true)
    public void IOSFirstBasicTest(){
        HomePage homePage= new HomePage(driver);
        AlertViewPage alertViewPage=homePage.selectAlertView();
        alertViewPage.clickOnTextEntryMenu();
        alertViewPage.EntryDetailsInEntryFieldPopup("Vijay");
        alertViewPage.clickOkButtonOnPopup();
//        driver.findElement(AppiumBy.iOSNsPredicateString("type ==  'XCUIElementTypeStaticText' AND value BEGINSWITH[c]   'Confirm'")).click();
        //        driver.findElement(AppiumBy.iOSNsPredicateString("type ==  'XCUIElementTypeStaticText' AND value ENDSWITH[c]   'Confirm'")).click();
        alertViewPage.clickOnConfirmCancelMenu();
        alertViewPage.checkMessageOnConfirmPopupIsAsExpected("A message");
        alertViewPage.clickOnConfirmButtonOfPopup();

    }

    @Test(enabled = false)
    public void IOS_LongPressGesture() throws InterruptedException {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(AppiumBy.accessibilityId("Steppers"))));
        Thread.sleep(2000);
        driver.findElement(AppiumBy.iOSNsPredicateString("name == 'Steppers'")).click();
//            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Increment\"`][3]"))));
        Thread.sleep(2000);
            WebElement elementToLongPressOn=driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Increment\"`][3]"));
                driver.executeScript("mobile:touchAndHold",
                        ImmutableMap.of("elementId",((RemoteWebElement)elementToLongPressOn).getId(),
                                "duration", 2000));
    }

    @Test(enabled = false)
    public void IOS_ScrollGesture() throws InterruptedException {
        WebElement elementTillWhichScrollToBePerformed=driver.findElement(AppiumBy.accessibilityId("Web View"));
        driver.executeScript("mobile:scroll",
                ImmutableMap.of(
                        "elementId",((RemoteWebElement)elementTillWhichScrollToBePerformed).getId(),
                        "direction","down"
                ));
        Thread.sleep(2000);
        elementTillWhichScrollToBePerformed.click();
        driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name='UIKitCatalog']")).click();
        driver.findElement(AppiumBy.accessibilityId("Picker View")).click();
    }

    @Test(enabled = false)
    public void IOS_PickerSelection(){
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == \"Picker View\"`]")).click();
        driver.findElement(AppiumBy.accessibilityId("Red color component value")).sendKeys("80");
        driver.findElement(AppiumBy.accessibilityId("Green color component value")).sendKeys("220");
        driver.findElement(AppiumBy.accessibilityId("Blue color component value")).sendKeys("105");
        Assert.assertEquals(driver.findElement(AppiumBy.accessibilityId("Blue color component value")).getText(),"105");

    }

    @Test(enabled = false)
    public void IOS_SliderGesture() throws MalformedURLException {
        configureTestApp();
        WebElement sliderElement=driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeSlider[`label == 'AppElem'`]"));
        sliderElement.sendKeys("1%");
        Assert.assertEquals(sliderElement.getDomAttribute("value"),"100%");

    }

    @Test(enabled = false)
    public void IOS_SwipeGesture_LaunchFromActivity() throws InterruptedException {
        //Similar to package and Activity name we have in android we have bundle ID for IOS applications
        //This bundle Id is mostly provided by the developer
             driver.executeScript("mobile:launchApp",
                     ImmutableMap.of(
                             "bundleId","com.apple.mobileslideshow"
                     ));

             WebElement AlbumButton=driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"albums-shelf-details-disclosure\"`]"));
             driver.executeScript("mobile: scroll",
                     ImmutableMap.of(
                             "elementId",((RemoteWebElement)AlbumButton).getId(),
                             "direction","down"
                     ));
             AlbumButton.click();
             driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == \"Test\"`][2]")).click();

             Thread.sleep(1000);
             List<WebElement> listOfOptions=driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeImage[`name == \"PXGGridLayout-Info\"`]"));
//             driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == \"Test\"`][2]")).click();

//             driver.findElement(AppiumBy.iOSNsPredicateString("label == 'All Photos'")).click();
//        List<WebElement> allPhotos= driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeCell"));
        Thread.sleep(1000);
        int count= listOfOptions.size();
        Assert.assertEquals(count,6);
        listOfOptions.get(0).click();
        for(int i=0;i<count;i++){
            driver.executeScript("mobile: swipe",
                    ImmutableMap.of(
                            "direction","left"
                    ));
        }
        driver.navigate().back();

    }

}
