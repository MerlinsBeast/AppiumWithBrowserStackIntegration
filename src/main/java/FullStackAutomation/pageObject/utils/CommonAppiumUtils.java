package FullStackAutomation.pageObject.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class CommonAppiumUtils {
    AppiumDriverLocalService service;

    public AppiumDriverLocalService startAppiumServer(String appiumPath, String ipAddress, int port){
    //this optimization of having strating apppium service in common methdos doesn't work for me as the path for appium is different for me in my windows and mac system
        service= new AppiumServiceBuilder()
                .withAppiumJS(new File(appiumPath))
                .withIPAddress(ipAddress)
                .usingPort(port)
                .build();
        return service;
    }

    public void waitForElementToBeClickable(WebElement element, AppiumDriver driver){
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public List<HashMap<String, String>> getJsonData(String jsonPath)throws IOException {
//        System.getProperty("user.dir")+"src/test/java/FullStackAutomation/testData/E_CommerceApplication/E_CommerceApplication.json"
        String jsonContent= FileUtils.readFileToString(new File(jsonPath));
        ObjectMapper mapper= new ObjectMapper();
        List<HashMap<String,String>> data= mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;
    }
    public String getScreenshot(String testcaseName, AppiumDriver driver) throws IOException {
        File source= driver.getScreenshotAs(OutputType.FILE);
        String destinationFile= System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
        FileUtils.copyFile(source, new File(destinationFile));

        return destinationFile;
    }
}
