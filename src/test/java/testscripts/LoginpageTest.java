package testscripts;

import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductsPage;
import com.swaglabs.utils.Browser;
import com.swaglabs.utils.DataConfigConstants;
import com.swaglabs.utils.ReadData;

public class LoginpageTest extends Browser {
//1. setup --> launch browser and open URL
//2. teardown --> close browser  
	//      ---> configuration methods
//3. testcases --> maintestcase
	
	@BeforeMethod(alwaysRun = true)
	@Parameters("browsername")
	public void setup(String bname)
	{
		launchBrowser(bname);
		openURL();
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown()
	{
		close();
	}
	
	@Test (enabled=false,groups="sanity")
	public void verifyLoginBtnFunctionalityWithBlankUsernameAndBlankPassword()
	{
		LoginPage loginpage= new LoginPage(driver,"loginpage");
		loginpage.clickOnLoginBtn();
		String actualErrorMsg= loginpage.getErrorMsg();
		Assert.assertEquals(actualErrorMsg, DataConfigConstants.BLANK_USERNAME); //Hard assert
	}
	@Test(groups="sanity")
	public void verifyLoginBtnFunctionalityWithBlankPassword()
	{
		ReadData read = new ReadData("swaglabslogindata");
		String uname=read.getData(2, 0);
		LoginPage loginpage= new LoginPage(driver,"loginpage");
		loginpage.enterUsername(uname);
		loginpage.clickOnLoginBtn();
		String actualErrorMsg= loginpage.getErrorMsg();
		Assert.assertEquals(actualErrorMsg, DataConfigConstants.BLANK_PASSWORD); //Hard assert
	}
	
	@Test(groups="sanity")
	public void verifyLoginBtnFunctionalityWithInvalidUsernamePassword()
	{
		ReadData read = new ReadData("swaglabslogindata");
		List<String> logindata=read.getData(9);
		LoginPage loginpage= new LoginPage(driver,"loginpage");
		loginpage.enterUsername(logindata.get(0));
		loginpage.enterPassword(logindata.get(1));
		loginpage.clickOnLoginBtn();
		String actualErrorMsg= loginpage.getErrorMsg();
		Assert.assertEquals(actualErrorMsg, DataConfigConstants.INVALID_USERNAME_PASSWORD); //Hard assert
	}
	
	
	@Test(groups="smoke")
	public void verifyLoginBtnFunctionalityWithValidUsernamePassword() throws InterruptedException
	{
		ReadData read = new ReadData("swaglabslogindata");
		List<String> logindata=read.getData(1);
		LoginPage loginpage= new LoginPage(driver,"loginpage");
		loginpage.enterUsername(logindata.get(0));
		loginpage.enterPassword(logindata.get(1));
		loginpage.clickOnLoginBtn();
		
		Thread.sleep(3000);
		ProductsPage pro = new ProductsPage(driver,"productspage");
		Assert.assertTrue(pro.isProductHeadingVisible(),"Testscript failed as Prodcut Title not visible");
		Reporter.log("verifyLoginBtnFunctionalityWithValidUsernamePassword :    Status : PASS");
	}
	
	
	@Test(dataProvider="logindata",groups="smoke")
	public void verifyLoginBtnFunctionalityWithValidUsernamePassword(String uname,String pass) throws InterruptedException
	{
		
		LoginPage loginpage= new LoginPage(driver,"loginpage");
		loginpage.enterUsername(uname);
		loginpage.enterPassword(pass);
		loginpage.clickOnLoginBtn();
		
		Thread.sleep(3000);
		ProductsPage pro = new ProductsPage(driver,"productspage");
		Assert.assertTrue(pro.isProductHeadingVisible(),"Testscript failed as Prodcut Title not visible");
		Reporter.log("verifyLoginBtnFunctionalityWithValidUsernamePassword :"+ uname +"  " +pass+"    Status : PASS");

	}
	
	
	
	@DataProvider(name="logindata")
	public String[][] logindata()
	{
		
			ReadData read = new ReadData("swaglabslogindata");
			return read.getData();
			
	}
	
}















