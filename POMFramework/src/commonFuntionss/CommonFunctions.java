package commonFuntionss;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.openqa.selenium.TakesScreenshot;
public class CommonFunctions {
	public static Properties properties= null;
	public static WebDriver driver=null;
	public static File resultsSubFolder = null;
	public static String currentDir;
	public static   ExtentReports extentreports;
	public static 	ExtentSparkReporter htmlreporter;
	public static 	ExtentTest testcase;
	public static Logger logger = Logger.getLogger(CommonFunctions.class);
	
	// Loading the property file
	public Properties loadPropertyFile() throws IOException
	{
		FileInputStream fileInputStream= new FileInputStream("config.properties");
		properties= new Properties();
		properties.load(fileInputStream);
		return properties;

	}
	
	@BeforeSuite
	public void launchBrowser() throws IOException
	{
		loadPropertyFile();
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Orange HRM testing Started");
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

		extentreports = new ExtentReports();
		htmlreporter= new ExtentSparkReporter("ExtentReport_"+timeStamp+".html");
		extentreports.attachReporter(htmlreporter);

		logger.info("Log4j configured sucessfully");
		logger.info("Loading the property file");

		String browser = properties.getProperty("browser");
		String url= properties.getProperty("url");
		String location =properties.getProperty("location");
		logger.info("Reading the browser& Url values");
		
		currentDir = System.getProperty("user.dir");
		String subFolderName = timeStamp;
		resultsSubFolder = new File(currentDir + File.separator + "output" + File.separator + subFolderName);
		resultsSubFolder.mkdir();

		if( browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", location);
			driver = new ChromeDriver();
			logger.info("Launching Chrome Browser");
		}
		
		driver.manage().window().maximize();
		driver.get(url);
		captureScreen(driver);
		logger.info("Navigating to application");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		logger.info("Browser window maximized ");
	}


	public void captureScreen(WebDriver m_webdriver) {
		String timeStamp;

		try {
			File screenShotName;
			File scrFile = ((TakesScreenshot) m_webdriver).getScreenshotAs(OutputType.FILE);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			String snapFileName = resultsSubFolder + File.separator;
			screenShotName = new File(snapFileName + timeStamp + ".png");
			FileUtils.copyFile(scrFile, screenShotName);
			Reporter.log("<br><img src='" + screenShotName + "'height='300' width=�300'><br>");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logstatus(String msg)
	{
		logger.info(msg);
		testcase.log(Status.INFO,msg);

	}
	@AfterSuite
	public void tearDown()

	{
		driver.quit();
		logger.info("Execution complete .. closing the browser");
		logger.info("Orange HRM Testing Completed...");
		extentreports.flush();
	}

}
