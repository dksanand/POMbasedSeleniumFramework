package testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;


import org.testng.asserts.*;

import com.aventstack.extentreports.Status;

import commonFuntionss.CommonFunctions;
import pageObjects.User_Role_Page_Objects;

public class User_Role extends CommonFunctions{



	Logger logger = Logger.getLogger(User_Role.class);

	public void movetoUserPage()
	{
		logstatus("Moving to User page ");

		Actions actions = new Actions(driver);

		actions.moveToElement(User_Role_Page_Objects.adminLink);
		logstatus("Admin link clicked");

		actions.moveToElement(User_Role_Page_Objects.usermgmtLink);
		logstatus("User Management link clicked");

		actions.moveToElement(User_Role_Page_Objects.userLink);
		logstatus("User link clicked");

		captureScreen(driver);
		actions.click().build().perform();

		captureScreen(driver);
	}
	
	public void selectUserRole()
	{
		Select select = new Select(User_Role_Page_Objects.userRoleDropdown);
		select.selectByIndex(1);

		logstatus("Select the user role admin from the dropdown ");
		captureScreen(driver);
	}
	
	public void selectUserStatus()
	{
		Select select = new Select(User_Role_Page_Objects.userStatusDropdown);
		select.selectByIndex(1);

		logstatus("Select the user status Enabled from the dropdown ");
		captureScreen(driver);
	}
	public void validation()
	{
		User_Role_Page_Objects.searchButton.click();
		captureScreen(driver);
		logstatus("Search button cliked .... ");
		logstatus("Getting the user role value from the search results ");

		String actualUservalue=User_Role_Page_Objects.userRoleValue.getText();
		String actualStatusvalue=User_Role_Page_Objects.userStatusValue.getText();
		
		captureScreen(driver);
		logstatus("Getting the user status value from the search results ");
		logstatus("Validating the the user role value from the search results with expected ");
		
		Assert.assertEquals(actualUservalue, "Admin");
		logstatus("Validating the user status value from the search results with expected");
		Assert.assertEquals(actualStatusvalue, "Enabled");
		captureScreen(driver);


	}
	@Test
	public void checkrole()
	{
		PageFactory.initElements(driver,User_Role_Page_Objects.class);
		testcase =extentreports.createTest("Veryfing the User role and status details of the Orange HRM ");

		logstatus("User Role Test case Started ************ ");
		captureScreen(driver);

		movetoUserPage();
		selectUserRole();
		selectUserStatus();
		validation();
		logstatus("User Role Test case Compelted ************ ");

	}


}
