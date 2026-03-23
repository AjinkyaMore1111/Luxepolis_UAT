package PageObject;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCart {


    WebDriver driver;
    WebDriverWait wait;

    public AddToCart(WebDriver driver) {
        this.driver = driver;
        // ✅ Increase wait to 30 seconds
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//button[@class='header--menu-search header--menu-search-icon position--relative pt--10 pb--10 "
    		+ "pl--15 fs--12  font--family-Montserrat font--normal color--grey-light font--left ']")
    WebElement Searchbox;


    @FindBy(xpath="//input[@id='search']")
    WebElement Searchkeyword;







    public void clickSearchbox()
    {
    	Searchbox.click();
    }

    public void EnterKeyword()
    {
    	Searchkeyword.sendKeys("TBHB2243");
    	Searchkeyword.sendKeys(Keys.ENTER);
    }



}
