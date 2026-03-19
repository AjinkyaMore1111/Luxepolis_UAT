package Test_Cases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import PageObject.LoginPage;
import baseData.BaseData;

@Listeners(Utilities.Listener.class)	
public class TC_001_Login_Functionality extends BaseData  {

	@Test
	public void login() throws InterruptedException {
		
        System.out.println(" driver is inherited from BaseData ✅");
        LoginPage LP = new LoginPage(driver);

        Thread.sleep(7000);
        LP.ClickOnUserIcon();
  

        LP.Enter_Mobile_No("9960130048");
        Thread.sleep(3000);
        
        LP.clickonContinue();
        Thread.sleep(3000);
        
        LP.Enter_Password(746587);
        Thread.sleep(3000);

        LP.clickLogin1();
        Thread.sleep(3000);

  

      
    }
}	

