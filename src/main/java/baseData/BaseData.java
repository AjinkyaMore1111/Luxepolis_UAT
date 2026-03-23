package baseData;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseData {
	
	 public WebDriver driver;

	    @BeforeSuite
	    public void OpenBrowser() {

	    	 ChromeOptions options = new ChromeOptions();
	    	    options.addArguments("--window-size=1920,1080");
	    	    options.addArguments("--disable-gpu");
	    	    options.addArguments("--no-sandbox");
	    	    options.addArguments("--disable-dev-shm-usage");
	    	    options.addArguments("--disable-extensions");
	    	    options.addArguments("--remote-allow-origins=*");

	    	    // ✅ Bypass bot detection
	    	    options.addArguments("--disable-blink-features=AutomationControlled");
	    	    options.setExperimentalOption("excludeSwitches", 
	    	        new String[]{"enable-automation"});
	    	    options.setExperimentalOption("useAutomationExtension", false);

	    	    // ✅ Set real user agent
	    	    options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
	    	        "AppleWebKit/537.36 (KHTML, like Gecko) " +
	    	        "Chrome/146.0.0.0 Safari/537.36");

	    	    WebDriverManager.chromedriver().setup();
	    	    driver = new ChromeDriver(options);
	    	    driver.manage().window().maximize();
	    	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

	    	   // driver.navigate().to("https://uatui.luxepolis.com/");
	    	    driver.navigate().to("https://www.luxepolis.com/");

	    	    // ✅ Retry if application error detected
	    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	    String pageSource = driver.getPageSource();
	    	    if (pageSource.contains("Application error") || 
	    	        pageSource.contains("client-side exception")) {
	    	        System.out.println("⚠️ App error on load, retrying...");
	    	        driver.navigate().refresh();
	    	    }

	    	    // ✅ Wait for full page load
	    	    new WebDriverWait(driver, Duration.ofSeconds(30))
	    	        .until(d -> ((JavascriptExecutor) d)
	    	            .executeScript("return document.readyState")
	    	            .equals("complete"));

	    	    System.out.println("Browser Opened Successfully ✅");
	    }
	    
	    @BeforeClass
	    public void login() throws InterruptedException
	    {
	    	

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	        // ✅ Step 1: Click User Icon
	        System.out.println("[" + sdf.format(new Date()) + "] Clicking User Icon...");
	        WebElement userIcon = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                By.xpath("//i[@class='lp--user fs--22 undefined']")
	            )
	        );
	        Actions action = new Actions(driver);
	        action.moveToElement(userIcon).click().build().perform();
	        System.out.println("[" + sdf.format(new Date()) + "] User Icon Clicked ✅");

	        // ✅ Step 2: Enter Mobile Number
	        System.out.println("[" + sdf.format(new Date()) + "] Entering Mobile No...");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//*[@id='mobileNo']")
	        )).sendKeys("9960130048");
	        System.out.println("[" + sdf.format(new Date()) + "] Mobile No Entered ✅");

	        // ✅ Step 3: Click Continue
	        System.out.println("[" + sdf.format(new Date()) + "] Clicking Continue...");
	        wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//*[@id='modal-btn']")
	        )).click();
	        System.out.println("[" + sdf.format(new Date()) + "] Continue Clicked ✅");

	        // ✅ Step 4: Wait for OTP field (longer wait — OTP SMS delivery time)
	        System.out.println("[" + sdf.format(new Date()) + "] Waiting for OTP Field...");
	        wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // ⬆ Extended wait for OTP
	        WebElement otpField = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//input[@name='otp1']")
	            )
	        );

	        // ✅ Step 5: Enter OTP
	        System.out.println("[" + sdf.format(new Date()) + "] Entering OTP...");
	        otpField.clear();
	        otpField.sendKeys("746587");
	        System.out.println("[" + sdf.format(new Date()) + "] OTP Entered ✅");

	        // ✅ Step 6: Click Submit
	        System.out.println("[" + sdf.format(new Date()) + "] Clicking Submit...");
	        wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//*[@type='submit']")
	        )).click();
	        System.out.println("[" + sdf.format(new Date()) + "] Submit Clicked ✅");

	        // ✅ Step 7: Verify Login Success
	        System.out.println("[" + sdf.format(new Date()) + "] Verifying Login...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(
	            By.xpath("//input[@name='otp1']")  // OTP modal closes = login success
	        ));
	        System.out.println("[" + sdf.format(new Date()) + "] Login Successful ✅");
	    	
	    	
	       
	 	    

	    	
	    }
	    
	    
	    

	   // @AfterClass
	    public void CloseBrowser() {
	        if (driver != null) {
	            driver.quit();
	            System.out.println("Browser Closed Successfully ✅");
	        }
	    }
	
    }
