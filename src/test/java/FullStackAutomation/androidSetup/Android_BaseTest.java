package FullStackAutomation.androidSetup;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Android_BaseTest {
    public AndroidDriver driver;
    AppiumDriverLocalService service;

    @BeforeMethod(alwaysRun = true)
    public void configureAppium() throws MalformedURLException {
        service= new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\Vijay_Yadav\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("VijayEmulator");
        options.setApp("C:\\AppiumProject\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");


        driver= new AndroidDriver(new URL("http://0.0.0.0:4723"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAppium(){
        driver.quit();
        service.stop();
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
