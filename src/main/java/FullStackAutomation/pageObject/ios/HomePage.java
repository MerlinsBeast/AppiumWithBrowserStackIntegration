package FullStackAutomation.pageObject.ios;

import FullStackAutomation.pageObject.utils.IOSActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends IOSActions {
    IOSDriver driver;
    public HomePage(IOSDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @iOSXCUITFindBy(accessibility = "Alert Views")
    private WebElement alertView;

    public AlertViewPage selectAlertView(){
        alertView.click();
        AlertViewPage alertViewPage= new AlertViewPage(driver);
        return alertViewPage;
    }

}
