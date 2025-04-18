package FullStackAutomation.androidSetup;

import FullStackAutomation.pageObject.utils.CommonAppiumUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class AndroidBaseTest_eCommerce extends CommonAppiumUtils {
    public AndroidDriver driver;
    AppiumDriverLocalService service;



    @BeforeMethod(alwaysRun = true)
    public void configureAppium() throws IOException, InterruptedException {

        Properties properties= new Properties();
        FileInputStream fileInputStream= new FileInputStream(System.getProperty("user.dir")+"/src/main/java/FullStackAutomation/pageObject/resources/data.properties");
        properties.load(fileInputStream);
        String android_AppiumPath= properties.get("androidAppiumPath").toString();
        String ipAddress=  System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : properties.get("ipAddress").toString();
        int port= Integer.parseInt(properties.get("port").toString());

        service= startAppiumServer(android_AppiumPath,ipAddress,port);
        service.start();

        //above method will take care of starting appium using specific appium server path
        /*service= new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\Vijay_Yadav\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();*/
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(properties.get("androidDeviceName").toString());
        options.setApp("C:\\AppiumProject\\Appium\\src\\test\\java\\resources\\General-Store.apk");
        options.setChromedriverExecutable("C:\\AppiumProject\\Appium\\src\\test\\java\\resources\\chromedriver.exe");


        driver= new AndroidDriver(service.getUrl(),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//        Thread.sleep(7);
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
