package FullStackAutomation.pageObject.android;

import FullStackAutomation.pageObject.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AndroidActions {

    AndroidDriver driver;
    public LandingPage(AndroidDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;
    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    private WebElement femaleRadioButton;
    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement letsShopRadioBtn;



    public void addDetailsToNameField(String data){
        nameField.sendKeys(data);
        driver.hideKeyboard();
    }
    public void selectFemaleRadioButton(){
        femaleRadioButton.click();
    }
    public ItemsPage clickOnLetsShopButton(){
        letsShopRadioBtn.click();
        ItemsPage itemsPage = new ItemsPage(driver);
        return itemsPage;
    }

}
