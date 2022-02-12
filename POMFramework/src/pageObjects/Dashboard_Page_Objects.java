package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class Dashboard_Page_Objects {
	//*[@id="task-list-group-panel-menu_holder"]/table/tbody/tr/td
	//@FindBy(linkText = "Apply Online")
//	public static WebElement applyOnline;
	
	
	@FindBy(xpath="//*[@id='task-list-group-panel-menu_holder']/following ::td[2]")
	public static WebElement pendingLeave;
	
	

}
