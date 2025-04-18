package FullStackAutomation.pageObject.ios;

import FullStackAutomation.pageObject.utils.IOSActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AlertViewPage extends IOSActions {
    IOSDriver driver;
    public AlertViewPage(IOSDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Text Entry'`]")
    private WebElement textEntryMenu;
    public void clickOnTextEntryMenu(){
        textEntryMenu.click();
    }

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
    private WebElement entryTextFieldOnPopup;
    public void EntryDetailsInEntryFieldPopup(String data){
        entryTextFieldOnPopup.sendKeys(data);
    }

    @iOSXCUITFindBy(accessibility = "OK")
    private WebElement okButtonOnPopup;
    public void clickOkButtonOnPopup(){
        okButtonOnPopup.click();
    }

    @iOSXCUITFindBy(iOSNsPredicate = "type ==  'XCUIElementTypeStaticText' AND value == 'Confirm / Cancel'")
    private WebElement confirmAndCancelButton;
    public void clickOnConfirmCancelMenu(){
        confirmAndCancelButton.click();
    }

    @iOSXCUITFindBy(iOSNsPredicate = "value BEGINSWITH[c] 'A message'")
    private WebElement messageOnConfirmCancelPopup;
    public void checkMessageOnConfirmPopupIsAsExpected(String expMessage) {
        String actMessage = messageOnConfirmCancelPopup.getText();
        System.out.println("Message is " + actMessage);
    }

    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm'")
    private WebElement confirmButton;
    public void clickOnConfirmButtonOfPopup(){
        confirmButton.click();
    }

}
