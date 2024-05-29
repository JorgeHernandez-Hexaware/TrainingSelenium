package course;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.checkerframework.framework.qual.SubtypeOf;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestDelta {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Language Modal
		By langModal = By.xpath("//button[text()=' México - Español ']");
		
		By hotel = By.xpath("//li[@class='shopping-link-2']");
		
		//Book page
		By btnGoingto = By.xpath("//button[@aria-label='Going to']");
		By inpGoTo = By.xpath("//input[@id='location-field-destination']");
		By optionGoTo = By.xpath("//button[@aria-label='Guadalajara Jalisco, Mexico']");
		By btnCheckIn = By.xpath("//button[@id='d1-btn']");
		By btnCheckOut = By.xpath("//button[@id='d2-btn']");
		By day1 = By.xpath("(//button[@data-day='10'])[1]");
		By dayBtnDone = By.xpath("(//button[text()='Done'])[2]");
		By day2 =  By.xpath("(//button[@data-day='11'])[1]");
		By travelers = By.xpath("(//button[@type='button'])[2]");
		By search = By.xpath("//button[@type='submit']");
		By selectedHotel = By.xpath("(//a[@class='uitk-card-link'])[2]");
		By btnReserveRoomList = By.xpath("//button[text()='Reserve a room']");
		By reserve = By.xpath("(//span[text()='Reserve'])[1]");
		By payProperty = By.xpath("//span[text()='Pay at property']");
		
		//Booking
		By firstName = By.xpath("(//input[@type='text'])[2]");
		By lastName = By.xpath("(//input[@type='text'])[3]");
		By email = By.xpath("//input[@type='email']");
		By phone = By.xpath("(//input[@type='tel'])[1]");
		
		//Validation dates and total due today
		By checkInDate = By.xpath("//span[contains(@class, 'date-range') and contains(text(), 'Mon, Jun 10')]");
		By checkOutDate = By.xpath("//span[contains(@class, 'date-range') and contains(text(), 'Tue, Jun 11')]");
		By totalDue = By.xpath("(//span[contains(@class, 'amount-value') and contains(text(), '$0.00')])[2]");
		
		//Set driver path in System Properties
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jorge\\OneDrive\\Documents\\Training Selenium\\Drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		
		//Create Chrome driver
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		try {
			//Navigate to Page
			driver.get("https://www.delta.com");
			
			//Select Language
			WebElement modalLang = wait.until(ExpectedConditions.visibilityOfElementLocated(langModal));
			highlightElement(modalLang, driver);
			modalLang.click();
					
			//Go to Comprar Hoteles
			
			//Scroll to get element visible
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 250);");
			
			//Get the identifier of current window
			String originalWindow = driver.getWindowHandle();
			
			WebElement hoteles = wait.until(ExpectedConditions.visibilityOfElementLocated(hotel));
			highlightElement(hoteles, driver);
			hoteles.click();
					
			//Switch to the recent opened window
			for(String window: driver.getWindowHandles()){
				if(!originalWindow.contentEquals(window)){
					driver.switchTo().window(window);
					break;
				}
			}
			
			//Get the identifier of current window
			String secondWindow = driver.getWindowHandle();
			
			WebElement btnGoTo = wait.until(ExpectedConditions.visibilityOfElementLocated(btnGoingto));
			btnGoTo.click();
			WebElement inputGoTo = wait.until(ExpectedConditions.visibilityOfElementLocated(inpGoTo));
			inputGoTo.sendKeys("Guadalajara");
			WebElement optGoTo = wait.until(ExpectedConditions.visibilityOfElementLocated(optionGoTo));
			highlightElement(btnGoTo, driver);
			optGoTo.click();
			
			WebElement btnDateCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(btnCheckIn));
			highlightElement(btnDateCheckIn, driver);
			btnDateCheckIn.click();
			
			WebElement d1 = wait.until(ExpectedConditions.visibilityOfElementLocated(day1));
			highlightElement(d1, driver);
			d1.click();
			
			WebElement btnDone = wait.until(ExpectedConditions.visibilityOfElementLocated(dayBtnDone));
			highlightElement(btnDone, driver);
			btnDone.click();
			
			WebElement btnDateCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(btnCheckOut));
			highlightElement(btnDateCheckOut, driver);
			btnDateCheckOut.click();
			
			WebElement d2 = wait.until(ExpectedConditions.visibilityOfElementLocated(day2));
			highlightElement(d2, driver);
			d2.click();
			
			WebElement btnDone2 = wait.until(ExpectedConditions.visibilityOfElementLocated(dayBtnDone));
			highlightElement(btnDone2, driver);
			btnDone2.click();
			
			WebElement searchStay = wait.until(ExpectedConditions.visibilityOfElementLocated(search));
			highlightElement(searchStay, driver);
			searchStay.click();
			
			
			WebElement selectHotel = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedHotel));
			highlightElement(selectHotel, driver);
			takeScreenshot("List of hotels", driver);
			selectHotel.click();
			
			//Get the identifier of current window
			Set<String> handles = driver.getWindowHandles();

			//Get 3rd window
			List<String> handleList = new ArrayList<>(handles);
			
			String thirdWindow = handleList.get(handleList.size()-1);
			
			driver.switchTo().window(thirdWindow);
			
			WebElement btnReserveRoom = wait.until(ExpectedConditions.visibilityOfElementLocated(btnReserveRoomList));
			highlightElement(btnReserveRoom, driver);
			btnReserveRoom.click();
			
			WebElement reserveRoom = wait.until(ExpectedConditions.visibilityOfElementLocated(reserve));
			highlightElement(reserveRoom, driver);
			reserveRoom.click();
			
			WebElement payAyProperty = wait.until(ExpectedConditions.visibilityOfElementLocated(payProperty));
			highlightElement(payAyProperty, driver);
			payAyProperty.click();
			
			WebElement firstNameInp = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
			highlightElement(firstNameInp, driver);
			firstNameInp.sendKeys("Jorge");
			
			WebElement lastNameInp = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
			highlightElement(lastNameInp, driver);
			lastNameInp.sendKeys("Hernandez");
			
			WebElement emailInp = wait.until(ExpectedConditions.visibilityOfElementLocated(email));
			highlightElement(emailInp, driver);
			emailInp.sendKeys("test@test.com");
			
			WebElement phoneInp = wait.until(ExpectedConditions.visibilityOfElementLocated(phone));
			highlightElement(phoneInp, driver);
			phoneInp.sendKeys("8441231234");
			
			//Validate data in checkout
			String valCheckIn = "Mon, Jun 10";
			String valChecOut = "Tue, Jun 11";
			String valTotalDue = "$0.00";
			
			WebElement checkInVal = wait.until(ExpectedConditions.visibilityOfElementLocated(checkInDate));
			if(checkInVal.getText().equalsIgnoreCase(valCheckIn)) {
				highlightElement(checkInVal, driver);
				System.out.println("Check-in Date Validated");
			}
			
			WebElement checkOutVal = wait.until(ExpectedConditions.visibilityOfElementLocated(checkOutDate));
			if(checkOutVal.getText().equalsIgnoreCase(valChecOut)) {
				highlightElement(checkOutVal, driver);
				System.out.println("Check-out Date Validated");
			}
			
			//Scroll to get element visible
			jse.executeScript("scroll(0, 250);");
			
			WebElement totalDueval = wait.until(ExpectedConditions.visibilityOfElementLocated(totalDue));
			if(totalDueval.getText().equalsIgnoreCase(valTotalDue)) {
				highlightElement(totalDueval, driver);
				System.out.println("Total Due Today Validated");
			}
			
			takeScreenshot("Checkout Booking Hotel", driver);
			
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
			takeScreenshot("Error", driver);
		}
			//Close Driver
			driver.quit();
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
