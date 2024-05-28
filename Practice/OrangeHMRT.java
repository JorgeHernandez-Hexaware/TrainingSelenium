package course;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrangeHMRT {
	public static void main(String[] args)throws Exception {
		//Login Elements
		By usernameF = By.name("username");
		By passwordF = By.name("password");
		By loginBtn = By.xpath("//button[@type=\"submit\"]");
				
		//Menu Elements
		By timeBtn = By.xpath("//a[contains(@class, 'oxd-main-menu-item') and contains(.//span, 'Time')]");
		
		//Time Elements
		By inputEmpName = By.xpath("//input[@placeholder='Type for hints...']");
		By viewBtnEmpName = By.xpath("//button[@type='submit']");
		
		//Timesheet for Employee
		By btnTimesheet = By.xpath("//button[text()=' Create Timesheet ']");
		By btnEditTimeSheet = By.xpath("//button[text()=' Edit ']");
		By noTS = By.xpath("//p[text()='No Timesheets Found']");
		By divDrop = By.xpath("//div[@role='listbox']");
		By BtnTrash = By.xpath("(//button[@class='oxd-icon-button orangehrm-timesheet-icon'])[1]");
		
		//Edit TS for Employee
		By inputProject = By.xpath("//input[@placeholder='Type for hints...']");
		By divAct = By.xpath("//div[@class='oxd-select-text-input']");
		By inpHour = By.xpath("//input[@class='oxd-input oxd-input--active']");
		By btnSave = By.xpath("//button[@type='submit']");
		
		
		//Set driver path in System Properties
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jorge\\OneDrive\\Documents\\Training Selenium\\Drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		
		//Create Chrome driver
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		
		try {
			//Navigate to Page
			driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			takeScreenshot("Before_typing_credentials", driver);
			
			//Type credentials to login
			WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameF));
			highlightElement(usernameField, driver);
			usernameField.sendKeys("Admin");
			
			WebElement passwordField = driver.findElement(passwordF);
			highlightElement(passwordField, driver);
			passwordField.sendKeys("admin123");
			
			WebElement loginButton  = driver.findElement(loginBtn);
			
			takeScreenshot("After_typing_credentials", driver);
			highlightElement(loginButton, driver);
			loginButton.click();
			
			//Go to time
			WebElement timeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(timeBtn));
			timeButton.click();
					
			WebElement inptEmpName = wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmpName));
			inptEmpName.click();
			inptEmpName.sendKeys("James  Butler");
			
			WebElement divD = wait.until(ExpectedConditions.visibilityOfElementLocated(divDrop));
			Boolean divDrp = wait.until(ExpectedConditions.textToBe(divDrop, "James Butler"));
			if(divDrp) {
				inptEmpName.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
			}
				
			WebElement viewButtonEmpName = wait.until(ExpectedConditions.visibilityOfElementLocated(viewBtnEmpName));
			viewButtonEmpName.click();
			
			
			WebElement btnEditTS = wait.until(ExpectedConditions.visibilityOfElementLocated(btnEditTimeSheet));
			btnEditTS.click();
			
			WebElement btnTrashIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(BtnTrash));
			if(btnTrashIcon.isDisplayed()) {
				btnTrashIcon.click();
			}
			
			WebElement inpPEmployee = wait.until(ExpectedConditions.visibilityOfElementLocated(inputProject));
			inpPEmployee.click();
			inpPEmployee.sendKeys("ACME");
			WebElement divDProject = wait.until(ExpectedConditions.visibilityOfElementLocated(divDrop));
			highlightElement(divDProject, driver);
			Boolean divDrpPj = wait.until(ExpectedConditions.textToBe(divDrop, "ACME Ltd - ACME Ltd"));
			if(divDrpPj) {
				inpPEmployee.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
			}
			
			WebElement divActivity = driver.findElement(divAct);
			divActivity.click();
			WebElement actDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(divDrop));
			
			Thread.sleep(2000);
			
			if(actDiv.isDisplayed()) {
				divActivity.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
			}
			
			 List<WebElement> inpHours = driver.findElements(inpHour);
			 inpHours.get(1).click();
			 inpHours.get(1).sendKeys("10");
			 inpHours.get(2).click();
			 inpHours.get(2).sendKeys("10");
			 inpHours.get(3).click();
			 inpHours.get(3).sendKeys("10");
			 inpHours.get(4).click();
			 inpHours.get(4).sendKeys("10");
			 inpHours.get(5).click();
			 inpHours.get(5).sendKeys("10");
			 inpHours.get(6).click();
			 inpHours.get(6).sendKeys("10");
			 inpHours.get(7).click();
			 inpHours.get(7).sendKeys("10");
			 
			 WebElement buttonSave = driver.findElement(btnSave);
			 buttonSave.click();
			 
			 Thread.sleep(3000);
			 takeScreenshot("Validating_create_timesheet", driver);
			 
			 takeScreenshot("Validating_total_hours", driver);
			 
			
			//Close driver
			driver.quit();
			
		} catch (NullPointerException e) {
			System.out.println("Error: " + e);
			driver.quit();
		}
		
		
	}
	
	private static void takeScreenshot(String name, WebDriver driver) throws IOException{
		String ssFolder = "C:\\Users\\jorge\\OneDrive\\Documents\\Training Selenium\\SS\\%s.png";
		String ssName = String.format(ssFolder, name);
		
		TakesScreenshot ss = (TakesScreenshot) driver;
		File screenShot = ss.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screenShot, new File(ssName));
	}
	
	public static void highlightElement(WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.outline = '#f00 solid 2px' ", element);
	}
	
	
}
