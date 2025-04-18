package FullStackAutomation.pageObject.android;

import FullStackAutomation.pageObject.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ItemsPage extends AndroidActions {
    AndroidDriver driver;
    public ItemsPage(AndroidDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(id="com.androidsample.generalstore:id/productName")
    private List<WebElement> allProductsInSpecificView;
    @AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
    protected List<WebElement> addToCartBtn;
    @AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement ViewCartBtn;


    public CheckOutPage clickOnViewCartBtn(){
        ViewCartBtn.click();
        CheckOutPage checkOutPage =  new CheckOutPage(driver);
        return checkOutPage;
    }
    public List<WebElement> getListOfAddToCartBtn(){
        return addToCartBtn;
    }
    public List<WebElement> getProductsInView(){
        return allProductsInSpecificView;
    }
}
