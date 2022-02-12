package pageObjects;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class User_Role_Page_Objects {

	//txtUsername
	//txtPassword
	//btnLogin
	@FindBy(id="menu_admin_viewAdminModule")
	public static WebElement adminLink;
	
	@FindBy(id="menu_admin_UserManagement")
	public static WebElement usermgmtLink;
	
	@FindBy(id="menu_admin_viewSystemUsers")
	public static WebElement userLink;
	
	
	
	@FindBy(id="searchSystemUser_userType")
	public static WebElement userRoleDropdown;
	
	@FindBy(id="searchSystemUser_status")
	public static WebElement userStatusDropdown;
	
	@FindBy(xpath="//tr/td[3]")
	public static WebElement userRoleValue;
	
	@FindBy(xpath="//tr/td[5]")
	public static WebElement userStatusValue;
	
	@FindBy(id="searchBtn")
	public static WebElement searchButton;
	
	
	
	
	
}
