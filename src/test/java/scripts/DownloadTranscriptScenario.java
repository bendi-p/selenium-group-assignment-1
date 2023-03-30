package scripts;

import java.awt.AWTException;
import java.awt.*;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import pages.SingleSignOnPOM;
import pages.StudentHubPOM;
import utility.ReadFromExcel;
public class DownloadTranscriptScenario {

	private WebDriver driver;

	@BeforeClass
	public void setDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pavani Bendi\\Downloads\\Compressed\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}

	@DataProvider(name = "DP1")
	public Object[][] createData() throws IOException {
		ReadFromExcel readFromeExcel = new ReadFromExcel();
		Object[][] retObjArr = readFromeExcel.getExcelData("src/test/resources/" + "data.xls", "login");
		return retObjArr;
	}
	
	@Test(dataProvider = "DP1")
	public void downloadTranscripts(String user,String pass) throws InterruptedException, AWTException {
		
		// Initialize dependencies
		StudentHubPOM stdhb = new StudentHubPOM(driver);
		SingleSignOnPOM sso = new SingleSignOnPOM(driver);

		// TS - 1: Load canvas
		stdhb.clickOnLogin();

		// TS - 2: Enter username
		sso.setUsername(user);

		// TS - 2: Enter password
		sso.setPassword(pass);

		// TS - 3: Click submit
		sso.clickOnSubmit();
		
		// Set Device
		Thread.sleep(7000);
		sso.setDevice("phone2");
		
		// Send push notification
		Thread.sleep(5000);
		sso.clickOnSendPush();
		
		// Loading StudentHub
		Thread.sleep(10000);sso.setDontShowAgain();
		Thread.sleep(10000);sso.clickOnYes();
		
		// TS - 7: Click on close
		Thread.sleep(10000);stdhb.onClose();
		
		// TS - 8: Click on resources
		stdhb.clickOnResources();
		
		// TS - 9: Click on registration
		Thread.sleep(5000);
		stdhb.clickOnAcademicReg();
		
		// TS - 10: Click on My Transcripts
		Thread.sleep(2000);stdhb.clickOnTranscripts();
		
		String currentHandle = driver.getWindowHandle();
		for (String handle: driver.getWindowHandles()) {
			if (!handle.equals(currentHandle)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		// Set graduate level
		Thread.sleep(5000);
		stdhb.setGraduateLevel("GR");
		
		// Set transcript type
		stdhb.setTranscriptType("AUDI");
		
		// Click submit
		stdhb.clickSubmit();
		
		Thread.sleep(3000);
		Robot robot = new Robot();
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("setTimeout(window.print, 5);");
		try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_T);
        robot.keyPress(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_E);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_T);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		
		// Closing driver
		driver.quit();
		
	}
	
}
