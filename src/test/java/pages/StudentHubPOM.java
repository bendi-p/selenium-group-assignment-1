package pages;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

// POM for StudentHub
public class StudentHubPOM {

	private WebDriver driver;

	public StudentHubPOM(WebDriver driver) {
		this.driver = driver;
	}

	// web elements
	final String URL = "http://me.northeastern.edu";
	By login = By.xpath("//*[@id=\"bySelection\"]/div[2]/div/span");
	By btnClose = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/button[1]/span/i");
	By btnResources = By.xpath("//*[@id=\"spSiteHeader\"]/div/div[2]/div/div[3]/div/div/div/span[4]/a/span");
	By btnAcademicReg = By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div[3]/section/article/div[1]/div/div/div/div[1]/div/div/div/div/div/div[1]/div/div/div/div/div/div/div[1]/div[2]/div/div[1]/div/p");
	By btnMyTranscripts = By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div[3]/section/article/div[1]/div/div/div/div[1]/div/div/div/div/div/div[1]/div/div/div/div/div/div/div[2]/div/div/div[1]/div/div[20]/div/div/a");
	By selectGraduateLevel = By.xpath("//*[@id=\"levl_id\"]");
	By selectTranscriptType = By.xpath("//*[@id=\"type_id\"]");
	By btnSubmit = By.xpath("/html/body/div[3]/form/input");
	
	// web element actions
	public void hitURL() {
		this.driver.get(URL);
	}

	public void clickOnLogin() {
		hitURL();
		driver.findElement(login).click();
	}

	public void onClose() {
		driver.findElement(By.xpath("*")).sendKeys(Keys.ESCAPE);;
	}

	public void clickOnResources() {
		driver.findElement(btnResources).click();
	}

	public void clickOnAcademicReg() {
		driver.findElement(btnAcademicReg).click();
	}
	
	public void clickOnTranscripts() {
		driver.findElement(btnMyTranscripts).click();
	}
	
	public void setGraduateLevel(String level) {
		Select obj = new Select(driver.findElement(selectGraduateLevel));
		obj.selectByValue(level);
	}
	
	public void setTranscriptType(String type) {
		Select obj = new Select(driver.findElement(selectTranscriptType));
		obj.selectByValue(type);
	}
	
	public void clickSubmit() {
		driver.findElement(btnSubmit).click();
	}

}
