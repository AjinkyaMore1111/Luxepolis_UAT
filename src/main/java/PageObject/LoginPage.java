package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LoginPage {

	 WebDriver driver;
	    WebDriverWait wait;

	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        // ✅ Increase wait to 30 seconds
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        PageFactory.initElements(driver, this);
	    }

	    @FindBy(xpath = "//button[@aria-label='user']")
	    WebElement UserIcon;

	    @FindBy(xpath = "//*[@id='mobileNo']")
	    WebElement MobileNo;

	    @FindBy(xpath = "//*[@type='submit']")
	    WebElement Continue;

	    @FindBy(xpath = "//*[@name='otp1']")
	    WebElement Password;

	    @FindBy(xpath = "//*[@type='submit']")
	    WebElement LoginBtn;

	    // ✅ Fixed — Replace Actions with JS Click
	    public void ClickOnUserIcon() throws InterruptedException {

	        // ✅ Wait for element to be present in DOM
	        wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//i[contains(@class,'lp--user')]")
	        ));

	        // ✅ Wait for element to be visible
	        wait.until(ExpectedConditions.visibilityOf(UserIcon));

	        // ✅ Scroll to element
	        ((JavascriptExecutor) driver)
	            .executeScript(
	                "arguments[0].scrollIntoView(true);", UserIcon
	            );
	        Thread.sleep(1000);

	        // ✅ JS Click — more reliable than Actions in Jenkins
	        ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].click();", UserIcon);

	        System.out.println("User Icon Clicked ✅");
	    }

	    // ✅ Fixed — Add wait before sending keys
	    public void Enter_Mobile_No(String mobile) {
	        wait.until(ExpectedConditions.visibilityOf(MobileNo));
	        MobileNo.clear();
	        MobileNo.sendKeys(mobile);
	        System.out.println("Mobile No Entered ✅");
	    }

	    // ✅ Fixed — Add wait before click
	    public void clickonContinue() {
	        wait.until(ExpectedConditions
	            .elementToBeClickable(Continue));
	        ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].click();", Continue);
	        System.out.println("Continue Clicked ✅");
	    }

	    // ✅ Fixed — Add wait before sending keys
	    public void Enter_Password(long pwd) {
	        wait.until(ExpectedConditions.visibilityOf(Password));
	        Password.clear();
	        Password.sendKeys(String.valueOf(pwd));
	        System.out.println("Password Entered ✅");
	    }

	    // ✅ Fixed — Add wait before click
	    public void clickLogin1() {
	        wait.until(ExpectedConditions
	            .elementToBeClickable(LoginBtn));
	        ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].click();", LoginBtn);
	        System.out.println("Login Clicked ✅");
	    }
}
