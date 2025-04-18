package FullStackAutomation.androidTests;

import FullStackAutomation.androidSetup.Android_BaseTest;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

public class AppiumBasics extends Android_BaseTest {

    @Test(enabled = false)
    public void WifiSettingTest() throws MalformedURLException {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        driver.setClipboardText("WiFi Vijay settings");
        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
        String expectedText="WiFi settings";
        String actualText= driver.findElement(AppiumBy.className("android.widget.TextView")).getText();
        Assert.assertEquals(expectedText,actualText);
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();

    }
    @Test(enabled = false)
    public void LongPressTest() throws MalformedURLException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1']")).click();
        WebElement expandableList_peopleNames=driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
        longPressOnElement(expandableList_peopleNames);
        WebElement sampleMenuElement= driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title']"));
        String peopleNames_sampleMenu=sampleMenuElement.getText();
        Assert.assertTrue(sampleMenuElement.isDisplayed());
        Assert.assertEquals(peopleNames_sampleMenu,"Sample menu");

    }

    @Test(enabled = false)
    public void ScrollGestureTest() throws MalformedURLException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
//        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));
        scrollGestureToTheEnd();

    }
    @Test(enabled = false)
    public void SwipeGestureTest() throws MalformedURLException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();

        WebElement firstImage=driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
        Assert.assertEquals(firstImage.getDomAttribute("focusable"),"true");
        swipeGesture(firstImage);
        Assert.assertEquals(firstImage.getDomAttribute("focusable"),"false");

    }
    @Test(enabled = false)
    public void DragAndDropGesture() throws MalformedURLException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement itemToDrag= driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
        dragAndDropGesture(itemToDrag);
        Assert.assertEquals(driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_result_text")).getText(),"Dropped!");

    }
    @Test(enabled = false)
    public void MiscellaneousActivity() throws MalformedURLException {
        driver.findElement(AppiumBy.accessibilityId("App")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Alert Dialogs\"]")).click();
        driver.findElement(AppiumBy.id("io.appium.android.apis:id/checkbox_button")).click();
        List<WebElement> allOption=driver.findElements(By.className("//android.widget.CheckedTextView"));
        for(int i=0;i<allOption.size();i++){
            String message= allOption.get(i).getText();
            if(message.equalsIgnoreCase("Every Monday") || message.equalsIgnoreCase("Every Thursday")){
                Assert.assertEquals(allOption.get(i).getDomAttribute("checked"),"true");
            }
            else{
                Assert.assertEquals(allOption.get(i).getDomAttribute("checked"),"false");
            }
        }

    }

    @Test(enabled = false)
    public void MiscellaneousActivity_DeviceToLandScapeMode() throws MalformedURLException {
        driver.findElement(AppiumBy.accessibilityId("App")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Alert Dialogs\"]")).click();
        DeviceRotation landscape =  new DeviceRotation(0,0,90);
        driver.rotate(landscape);
        driver.findElement(AppiumBy.id("io.appium.android.apis:id/checkbox_button")).click();
        List<WebElement> allOption=driver.findElements(By.className("//android.widget.CheckedTextView"));
        for(int i=0;i<allOption.size();i++){
            String message= allOption.get(i).getText();
            if(message.equalsIgnoreCase("Every Monday") || message.equalsIgnoreCase("Every Thursday")){
                Assert.assertEquals(allOption.get(i).getDomAttribute("checked"),"true");
            }
            else{
                Assert.assertEquals(allOption.get(i).getDomAttribute("checked"),"false");
            }
        }
    }

    @Test(enabled = false)
    public void MiscellaneousActivity_MobileEventsLikeHomeKeysEtc() throws MalformedURLException {
        driver.findElement(AppiumBy.accessibilityId("App")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Alert Dialogs\"]")).click();
        DeviceRotation landscape =  new DeviceRotation(0,0,90);
        driver.rotate(landscape);
        driver.findElement(AppiumBy.id("io.appium.android.apis:id/checkbox_button")).click();
        List<WebElement> allOption=driver.findElements(By.className("//android.widget.CheckedTextView"));
        for(int i=0;i<allOption.size();i++){
            String message= allOption.get(i).getText();
            if(message.equalsIgnoreCase("Every Monday") || message.equalsIgnoreCase("Every Thursday")){
                Assert.assertEquals(allOption.get(i).getDomAttribute("checked"),"true");
            }
            else{
                Assert.assertEquals(allOption.get(i).getDomAttribute("checked"),"false");
            }
        }
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }

    @Test(enabled = true)
    public void MiscellaneousActivity_LaunchAppViaActivity() throws MalformedURLException, InterruptedException {
        Activity activity= new Activity("io.appium.android.apis","io.appium.android.apis.view.DragAndDropDemo");
        driver.executeScript("mobile: startActivity", ImmutableMap.of("intent", "io.appium.android.apis/io.appium.android.apis.view.DragAndDropDemo"));
        Thread.sleep(4000);
    }

}
