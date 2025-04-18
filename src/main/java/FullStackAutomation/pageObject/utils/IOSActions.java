package FullStackAutomation.pageObject.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class IOSActions extends CommonAppiumUtils{
    IOSDriver driver;
    public IOSActions(IOSDriver driver){
        this.driver=driver;
    }


}
