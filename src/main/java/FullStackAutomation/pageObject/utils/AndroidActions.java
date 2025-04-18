package FullStackAutomation.pageObject.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidActions extends CommonAppiumUtils{
    AndroidDriver driver;
    public AndroidActions(AndroidDriver driver){
        this.driver=driver;
    }

    public void scrollIntoViewOfElementText(String elementText){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+elementText+"\"));"));
    }

    public void longPressOnElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId",((RemoteWebElement)element).getId(),"duration",2000));
    }

    public void scrollGestureToTheEnd(){
        boolean canScrollMore;
        do{
            canScrollMore= (Boolean)((JavascriptExecutor)driver).executeScript("mobile: scrollGesture",
                    ImmutableMap.of(
                            "left",100,
                            "top",100,
                            "width",200,
                            "height",200,
                            "direction","down",
                            "percent",3.0
                    ));}
        while(canScrollMore);

    }
    public void swipeGesture(WebElement element){
        ((JavascriptExecutor)driver).executeScript(
                "mobile: swipeGesture",
                ImmutableMap.of(
                        "elementId",((RemoteWebElement)element).getId(),
                        "direction","left",
                        "percent",0.75
                )
        );
    }

    public void dragAndDropGesture(WebElement element){
        ((JavascriptExecutor)driver).executeScript(
                "mobile: dragGesture",
                ImmutableMap.of(
                        "elementId",(RemoteWebElement)element,
                        "endX",825,
                        "endY",740
                )
        );
    }
}
