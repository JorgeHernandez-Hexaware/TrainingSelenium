package course;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestDelta2 {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {

		//Language Modal
		By langModal = By.xpath("//button[text()=' México - Español ']");
		
		//Booking
		By fromAirpotLine = By.xpath("//a[@id='fromAirportName']");
		By inputPlace = By.xpath("//input[@id='search_input']");
		By fromToOption = By.xpath("//li[contains(@class, 'airport-list ng-star-inserted') and contains(@data-index, '0')]");
		By toAirportLine = By.xpath("//a[@id='toAirportName']");
		By selectTripType = By.xpath("//span[@id='selectTripType-val']");
		By tripTypeOption = By.xpath("//li[contains(@id, 'ui-list-selectTripType1') and contains(@data, '1')]");
		By date = By.xpath("//div[@id='input_departureDate_1']");
		By selectedDate = By.xpath("(//a[contains(text(), '20')])[1]");
		By btnDate = By.xpath("//button[@class='donebutton']");
		By passengers = By.xpath("(//span[contains(@class, 'select-ui-wrapper ng-tns-c1-2')])[2]");
		By numPassengers = By.xpath("(//li[contains(@id, 'ui-list-passengers')])[1]");
		By search = By.xpath("(//button[contains(@type, 'submit') and contains(text(), 'BUSCAR')])[1]");
		
		//Price
		By priceCM = By.xpath("//div[contains(@id, 'grid-row-0-fare-cell-desktop-MAIN') or contains(@id, 'grid-row-0-fare-cell-desktop-AMCL')]");
		
		//Set driver path in System Properties
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jorge\\OneDrive\\Documents\\Training Selenium\\Drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		
		//Create Chrome driver
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
		try {
			//Navigate to Delta
			driver.get("https://www.delta.com");
			
			//Select Language
			WebElement modalLang = wait.until(ExpectedConditions.visibilityOfElementLocated(langModal));
			highlightElement(modalLang, driver);
			modalLang.click();
			
			//Select From Place
			WebElement fromPlace = wait.until(ExpectedConditions.visibilityOfElementLocated(fromAirpotLine));
			highlightElement(fromPlace, driver);
			fromPlace.click();
			
			WebElement inpFromPlace = wait.until(ExpectedConditions.visibilityOfElementLocated(inputPlace));
			highlightElement(inpFromPlace, driver);
			inpFromPlace.sendKeys("MTY");
			
			WebElement fromOptionPlace = wait.until(ExpectedConditions.visibilityOfElementLocated(fromToOption));
			highlightElement(fromOptionPlace, driver);
			fromOptionPlace.click();
			
			//Select To Place
			WebElement toPlace = wait.until(ExpectedConditions.visibilityOfElementLocated(toAirportLine));
			highlightElement(toPlace, driver);
			toPlace.click();
			
			WebElement inpToPlace = wait.until(ExpectedConditions.visibilityOfElementLocated(inputPlace));
			highlightElement(inpToPlace, driver);
			inpToPlace.sendKeys("LAX");
			
			WebElement toOptionPlace = wait.until(ExpectedConditions.visibilityOfElementLocated(fromToOption));
			highlightElement(toOptionPlace, driver);
			toOptionPlace.click();
			
			//Select Type Of Flight
			WebElement tripType = wait.until(ExpectedConditions.visibilityOfElementLocated(selectTripType));
			highlightElement(tripType, driver);
			tripType.click();
			
			WebElement tripOption = wait.until(ExpectedConditions.visibilityOfElementLocated(tripTypeOption));
			highlightElement(tripOption, driver);
			tripOption.click();
			
			//Select Date(s)
			WebElement dateToGo = wait.until(ExpectedConditions.visibilityOfElementLocated(date));
			highlightElement(dateToGo, driver);
			dateToGo.click();
			
			WebElement selectedDay = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedDate));
			highlightElement(selectedDay, driver);
			selectedDay.click();
			
			WebElement btnDayDone = wait.until(ExpectedConditions.visibilityOfElementLocated(btnDate));
			highlightElement(btnDayDone, driver);
			btnDayDone.click();
			
			//Select Number Of Passengers
			WebElement btnPassengers = wait.until(ExpectedConditions.visibilityOfElementLocated(passengers));
			highlightElement(btnPassengers, driver);
			btnPassengers.click();
			
			WebElement numPassengersOption =  wait.until(ExpectedConditions.visibilityOfElementLocated(numPassengers));
			highlightElement(numPassengersOption, driver);
			numPassengersOption.click();
			
			
			
			//Search For Flight
			WebElement btnSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(search));
			
			//SS data
			highlightElement(btnSearch, driver);
			
			takeScreenshot("Form_Info_Flight", driver);

			btnSearch.click();
			
			//Select Price
			WebElement priceClassicMain = wait.until(ExpectedConditions.visibilityOfElementLocated(priceCM));
			highlightElement(priceClassicMain, driver);
			priceClassicMain.click();
			
			
			//Continue
			
			
			//Fill Form
			
			
			Thread.sleep(6000);
			
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
