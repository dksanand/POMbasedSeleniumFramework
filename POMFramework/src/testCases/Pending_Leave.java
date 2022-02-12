
package testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFuntionss.CommonFunctions;
import pageObjects.Dashboard_Page_Objects;
import pageObjects.Login_Page_Objects;

public class Pending_Leave extends CommonFunctions{
	 Logger logger = Logger.getLogger(Pending_Leave.class);
	public void login()
	{
		testcase =extentreports.createTest("Veryfing the Pending Leave details of the Orange HRM ");
		
		logstatus("Navigating to Login page");


		PageFactory.initElements(driver,Login_Page_Objects.class);
		
		logstatus("Reading UserName & Entering");
		Login_Page_Objects.userName.sendKeys(properties.getProperty("username"));
		captureScreen(driver);
		
        logstatus("Reading Password & entering ");
		Login_Page_Objects.password.sendKeys(properties.getProperty("password"));
		captureScreen(driver);
		
        logstatus("click the Login button ");
		captureScreen(driver);
		Login_Page_Objects.btnLogin.click();
        captureScreen(driver);
        logstatus("Login completed sucessfully ");
	}
	public void validation()
	{
		PageFactory.initElements(driver, Dashboard_Page_Objects.class);
        captureScreen(driver);
		String status= Dashboard_Page_Objects.pendingLeave.getText();
		logstatus("Getting the pending leave details ");
        captureScreen(driver);
        logstatus("Validating the actual mesage extracted from website ");
		if (status.contains("Total"))
		{
			Assert.assertTrue(true);
			logstatus("Total found in the actual message from webpage ");
		}
	}
	@Test 
	public void verfiyPendingLeave()
	{

		login();
		logstatus("Pending leave Test case  started ************ ");
        captureScreen(driver);
        validation();
        logstatus("Pending leave Test case  completed************ ");
	}
}
