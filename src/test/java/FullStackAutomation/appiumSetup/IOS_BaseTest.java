package FullStackAutomation.appiumSetup;

import FullStackAutomation.pageObject.utils.CommonAppiumUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
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

public class IOS_BaseTest extends CommonAppiumUtils {
    public IOSDriver     driver;
    AppiumDriverLocalService service;

    @BeforeMethod
    public void configureAppium() throws IOException {
        Properties properties= new Properties();
        FileInputStream fileInputStream= new FileInputStream(System.getProperty("user.dir")+"/src/main/java/FullStackAutomation/pageObject/resources/data.properties");
        properties.load(fileInputStream);
        String iOS_AppiumPath= properties.get("iOSAppiumPath").toString();
        String ipAddress=  System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : properties.get("ipAddress").toString();
        int port= Integer.parseInt(properties.get("port").toString());

        service= startAppiumServer(iOS_AppiumPath,ipAddress,port);
        service.start();

      /*  service= new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\Vijay_Yadav\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();*/

//        /users/admin/Library/Developer/Xcode/DerivedData/UIKitCatalog-/Users/admin/Library/Developer/Xcode/DerivedData/UIKitCatalog-fzkrxxrrlqucsrfecnhzcpgwctlh/Build/Products/Debug-iphonesimulator


        XCUITestOptions options= new XCUITestOptions();
        options.setDeviceName(properties.get("iOSDeviceName").toString());
        options.setApp("/Users/admin/Library/Developer/Xcode/DerivedData/UIKitCatalog-fzkrxxrrlqucsrfecnhzcpgwctlh/Build/Products/Debug-iphonesimulator/UIKitCatalog.app");
        options.setPlatformVersion(properties.get("IOSPlatformVersion").toString());
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));


        driver= new IOSDriver(service.getUrl(),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public void configureTestApp() throws MalformedURLException {
        service= new AppiumServiceBuilder()
                .withAppiumJS(new File("/Users/admin/.volta/bin/volta-shim"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();
        XCUITestOptions options= new XCUITestOptions();
        options.setDeviceName("iPhone 16");
        options.setApp("/Users/admin/IdeaProjects/AppiumAutomatio_2025/src/test/java/resources/TestApp 3.app");
        options.setPlatformVersion("18.4");
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));


        driver= new IOSDriver(new URL("http://0.0.0.0:4723"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterMethod
    public void tearDownAppium(){
        driver.quit();
        service.stop();
    }

}
