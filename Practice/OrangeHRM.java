package course;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrangeHRM {

	public static void main(String[] args)throws Exception {
		
		//Login Elements
		By usernameF = By.name("username");
		By passwordF = By.name("password");
		By loginBtn = By.xpath("//button[@type=\"submit\"]");
		
		//Menu Elements
		By adminBtn = By.xpath("//a[contains(@class, 'oxd-main-menu-item') and contains(.//span, 'Admin')]");
		
		//Admin page elements
		By UsrRole = By.xpath("(//div[@tabindex=0])[1]");
		By statusDiv = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
		By SearchBtn = By.xpath("//button[@type='submit']");
		By btnUsr = By.xpath("(//button[@type='button'])[9]");	
		
		//Edit User Elements
		By inputUsername = By.xpath("(//input)[3]");
		By statDiv = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
		By sbmBtn = By.xpath("//button[@type='submit']");
		By msgSuccess = By.xpath("//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']");
		
		//Set driver path in System Properties
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jorge\\OneDrive\\Documents\\Training Selenium\\Drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		
		//Create Chrome driver
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		try {
			//Navigate to PageX
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
			
			
			//Go to admin		
			WebElement adminButton = wait.until(ExpectedConditions.visibilityOfElementLocated(adminBtn));
			adminButton.click();
			
			//Search in System Users
			WebElement userRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UsrRole));
			userRole.click();
			userRole.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
			
			WebElement status = driver.findElement(statusDiv);
			status.click();
			status.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
			
			takeScreenshot("Before_searching_User", driver);
			
			WebElement searchButton = driver.findElement(SearchBtn);
			searchButton.click();
			
			

			takeScreenshot("After_searching_User", driver);
			
			WebElement userBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(btnUsr));
			userBtn.click();
			
			//Editing User
			takeScreenshot("Before_editing_User", driver);
			
			
			List<WebElement> inptUsername = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inputUsername));
			inptUsername.get(0).click();
			inptUsername.get(0).clear(); 
			inptUsername.get(0).sendKeys(Keys.CONTROL, "a");
			inptUsername.get(0).sendKeys("JorgeA");
			
			
			WebElement statDv = wait.until(ExpectedConditions.visibilityOfElementLocated(statDiv));
			statDv.click();
			
			statDv.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
			
			takeScreenshot("After_Editing_User", driver);
			
			WebElement submitBtn = driver.findElement(sbmBtn);
			takeScreenshot("Afer_editing_user", driver);
			submitBtn.click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(msgSuccess));
			takeScreenshot("Validating_edit_user", driver);
				
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
