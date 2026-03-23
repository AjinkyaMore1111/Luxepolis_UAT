package Test_Cases;




import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import PageObject.AddToCart;

import baseData.BaseData;


@Listeners(Utilities.Listener.class)
public class TC_001_Add_To_Cart_COD extends BaseData  {


@Test(priority=1)	
public void AddTocartToCOD() throws InterruptedException
{
	
AddToCart AD=new AddToCart(driver);
AD.clickSearchbox();
Thread.sleep(3000);
AD.EnterKeyword();

} 
     
     
}
