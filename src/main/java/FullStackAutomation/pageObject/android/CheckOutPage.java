package FullStackAutomation.pageObject.android;

import FullStackAutomation.pageObject.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckOutPage extends AndroidActions {
    AndroidDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    public CheckOutPage(AndroidDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedBtn;
    @AndroidFindBy(id="com.androidsample.generalstore:id/productName")
    private List<WebElement> productNamesOnCart;
    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPricesOnCart;
    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement productTotalOnCart;
    @AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
    private WebElement termsAndConditions;

    @AndroidFindBy(id="com.androidsample.generalstore:id/alertTitle")
    private WebElement alertTitle;

    @AndroidFindBy(id="android:id/button1")
    private WebElement closeButtonOnAlert;
    @AndroidFindBy(xpath="//android.widget.CheckBox[@package='com.androidsample.generalstore']")
    private WebElement AgreeCheckBox;

    public void clickOnAgreeCheckBox(){
        wait.until(ExpectedConditions.elementToBeClickable(AgreeCheckBox));
        AgreeCheckBox.click();
    }

    public WebElement proceedButton(){
     return proceedBtn;
    }
    public void clickOnProceedButton(){
        proceedBtn.click();
    }
    public WebElement getTermsAndConditions(){
        return termsAndConditions;
    }
    public WebElement getAlertTitle(){
        return alertTitle;
    }
    public void clickOnGetAlertCloseButton(){
        closeButtonOnAlert.click();
    }
    public List<WebElement> listOfProductNamesOnCard(){
        return productNamesOnCart;
    }
    public List<WebElement> listOfProductPricesOnCard(){
        return productPricesOnCart;
    }
    public String getTotalProductCostInCart(){
         return productTotalOnCart.getText();
    }
}
