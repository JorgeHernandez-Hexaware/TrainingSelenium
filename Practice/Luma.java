package course;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

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
import org.openqa.selenium.support.ui.WebDriverWait;

import io.netty.handler.timeout.TimeoutException;

public class Luma {

	public static void main(String[] args) throws InterruptedException, IOException {
		//Data
		/*
		 * Firstname: Jorge,
		 * Last Name: Hernandez,
		 * Email: jorgeT@gmail.com,
		 * Password: xT3ST@45*9,
		 * 
		 * Phone: 8447693218,
		 * Street Address: 1583, Lopez Mateos,
		 * */
		
		//Login Elements
		By btnSignIn = By.xpath("(//a)[3]");
		By email = By.xpath("//input[@id='email']");
		By password = By.xpath("(//input[contains(@id, 'pass') and contains(@type, 'password')])[1]");
		By btnLogIn = By.xpath("(//button[contains(@id, 'send2') and contains(@type, 'submit')])[1]");
		
		//First Item
		By womenMenu = By.xpath("//a[@id='ui-id-4']");
		By typeItem1 = By.xpath("//a[text()='Hoodies & Sweatshirts']");
		By item1 = By.xpath("(//div[@class='product-item-info'])[3]");
		By sizeItem1 = By.xpath("(//div[@class='swatch-option text'])[2]");
		By colorItem1 = By.xpath("//div[@id='option-label-color-93-item-52']");
		By btnAddToCart1 = By.xpath("//button[@id='product-addtocart-button']");		
		
		//Second Item
		By menMenu = By.xpath("//a[@id='ui-id-5']");
		By typeItem2 = By.xpath("//a[text()='Jackets']");
		By item2 = By.xpath("(//div[@class='product-item-info'])[4]");
		By sizeItem2 = By.xpath("//div[@id='option-label-size-143-item-169']");
		By colorItem2 = By.xpath("//div[@id='option-label-color-93-item-49']");
		By btnAddToCart2 = By.xpath("//button[@id='product-addtocart-button']");
		
		//Third Item
		By gearMenu = By.xpath("//a[@id='ui-id-6']");
		By typeItem3 = By.xpath("(//a[text()='Bags'])[2]");
		By item3 = By.xpath("(//div[@class='product-item-info'])[8]");
		By btnAddToCart3 = By.xpath("//button[@id='product-addtocart-button']");
		
		//Cart
		By cart = By.xpath("//a[@class='action showcart']");
		By btnCheckout = By.xpath("//button[@id='top-cart-btn-checkout']"); // Tomar SS
		
		//Shipping
		By shipping = By.xpath("//div[@class='shipping-address-item selected-item']");
		By btnNext = By.xpath("(//button[@type='submit'])[2]");
		
		//Review & Payments
		By addressReview = By.xpath("//div[@class='billing-address-details']");
		By cardSubtotal = By.xpath("(//span[@class='price'])[3]");
		By itemList = By.xpath("//ol[@class='minicart-items']");
		By divListItems = By.xpath("(//div[contains(@class, 'title') and contains(@aria-expanded, 'false')])[2]");
		By divListItemsActive = By.xpath("//div[@class='block items-in-cart active']");
		By btnPlaceOrder = By.xpath("//button[@class='action primary checkout']");
		
		//Checkout complete
		By msgBuy = By.xpath("//span[contains(@class,'base') and contains(text(), 'Thank you for your purchase!')]");
		
		//Set driver path in System Properties
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jorge\\OneDrive\\Documents\\Training Selenium\\Drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		
		//Create Chrome driver
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		
		try {
			//Navigate to Page
			driver.get("https://magento.softwaretestingboard.com/?ref=hackernoon.com");
			
			//Login
			WebElement buttonSignIn  = getWebElement(wait, btnSignIn);
			highlightElement(buttonSignIn , driver);
			buttonSignIn.click();
			
			String[] dataAddress = {"Jorge Hernandez", "1583, Lopez Mateos", "Monterrey, Nuevo León 12245", "Mexico", "8447693218"};
			
			WebElement emailField = getWebElement(wait, email);
			highlightElement(emailField, driver);
			emailField.sendKeys("jorgeT@gmail.com");
			
			WebElement pwdField = getWebElement(wait, password);
			highlightElement(pwdField, driver);
			pwdField.sendKeys("xT3ST@45*9");
			
			WebElement buttonLogIn = getWebElement(wait, btnLogIn);
			highlightElement(buttonLogIn, driver);
			
			takeScreenshot("Typing_Credentials", driver);
			buttonLogIn.click();
			
			//Adding First Item
			WebElement womanMenuItems = getWebElement(wait, womenMenu);
			highlightElement(womanMenuItems, driver);
			womanMenuItems.click();
			
			WebElement typeFirstItem = getWebElement(wait, typeItem1);
			highlightElement(typeFirstItem, driver);
			typeFirstItem.click();
			
			WebElement cardItem1 = getWebElement(wait, item1);
			highlightElement(cardItem1, driver);
			cardItem1.click();
			
			//Get item name and price
			String nameItem1 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='base']"))).getText();
			System.out.println(nameItem1);
			String price1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='product-price-1178']/span"))).getText();
			price1 = price1.substring(1);
			System.out.println(price1);
			
			WebElement selectSize1 = getWebElement(wait, sizeItem1);
			highlightElement(selectSize1, driver);
			selectSize1.click();
			
			WebElement selectColor1 = getWebElement(wait, colorItem1);
			highlightElement(selectColor1, driver);
			selectColor1.click();
			
			WebElement btnAdd = getWebElement(wait, btnAddToCart1);
			highlightElement(btnAdd, driver);
			btnAdd.click();
			
			//Second Item
			WebElement menMenuItems = getWebElement(wait, menMenu);
			highlightElement(menMenuItems, driver);
			menMenuItems.click();
			
			WebElement typeSecondItem = getWebElement(wait, typeItem2);
			highlightElement(typeSecondItem, driver);
			typeSecondItem.click();
			
			WebElement cardItem2 = getWebElement(wait, item2);
			highlightElement(cardItem2, driver);
			cardItem2.click();
			
			//Get item name and price
			String nameItem2 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='base']"))).getText();
			System.out.println(nameItem2);
			String price2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='product-price-382']/span"))).getText();
			price2 = price2.substring(1);
			System.out.println(price2);
			
			WebElement selectSize2 = getWebElement(wait, sizeItem2);
			highlightElement(selectSize2, driver);
			selectSize2.click();
			
			WebElement selectColor2 = getWebElement(wait, colorItem2);
			highlightElement(selectColor2, driver);
			selectColor2.click();
			
			WebElement btnAdd2 = getWebElement(wait, btnAddToCart2);
			highlightElement(btnAdd2, driver);
			btnAdd2.click();
			
			//Third Item
			WebElement menuGearItems = getWebElement(wait, gearMenu);
			highlightElement(menuGearItems, driver);
			menuGearItems.click();
			
			WebElement typeThirdItem = getWebElement(wait, typeItem3);
			highlightElement(typeThirdItem, driver);
			typeThirdItem.click();
			
			scroll(driver);
			
			WebElement cardItem3 = getWebElement(wait, item3);
			highlightElement(cardItem3, driver);
			cardItem3.click();
			
			//Get item name and price
			String nameItem3 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='base']"))).getText();
			System.out.println(nameItem3);
			String price3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='product-price-7']/span"))).getText();
			price3 = price3.substring(1);
			
			double totalAmount = Double.parseDouble(price1.toString()) + Double.parseDouble(price2) + Double.parseDouble(price3);
			System.out.println(price3);
			System.out.println("Total Amount: " + totalAmount);
			
			WebElement btnAdd3 = getWebElement(wait, btnAddToCart3);
			highlightElement(btnAdd3, driver);
			btnAdd3.click();
			
			//Cart
			WebElement cartIcon = getWebElement(wait, cart);
			highlightElement(cartIcon, driver);
			cartIcon.click();
			
			WebElement buttonCheckout = getWebElement(wait, btnCheckout);
			highlightElement(buttonCheckout, driver);
			takeScreenshot("Items_in_cart", driver);
			buttonCheckout.click();
			
			WebElement shippingPage = getWebElement(wait, shipping);
			highlightElement(shippingPage, driver);
			String[] data_values = shippingPage.getText().split("\n");
			
			for (int i = 0; i < data_values.length; i++) {
				System.out.println(dataAddress[i]+ ": " + data_values[i].equals(dataAddress[i]));
			}
			
			WebElement buttonNext = getWebElement(wait, btnNext);
			highlightElement(buttonNext, driver);
			takeScreenshot("Shipping_information", driver);
			buttonNext.click();
			
			WebElement reviewAddress = getWebElement(wait, addressReview);
			highlightElement(reviewAddress, driver);
			String[] addressValidation = reviewAddress.getText().split("\n");
			
			for (int i = 0; i < addressValidation.length; i++) {
				System.out.println(dataAddress[i]+ ": " + addressValidation[i].equals(dataAddress[i]));
			}
			
			WebElement subtotal = getWebElement(wait, cardSubtotal);
			Double subtotalAmount = Double.parseDouble(subtotal.getText().toString().substring(1));
			
			System.out.println(subtotalAmount);
			System.out.println("Subtotal equals to total price products?: " + (totalAmount==subtotalAmount));
			
			scroll(driver);
			
			try {
				WebElement divList = getWebElement(wait, divListItems);
				System.out.println(divList.isDisplayed());
				if(divList.isDisplayed()) {
					divList.sendKeys(Keys.ENTER);
				}
			} catch (TimeoutException e) {
				System.out.println("Element not found");
			}
			
			WebElement itemListCheckout = getWebElement(wait, itemList);
			highlightElement(itemListCheckout, driver);
			
			WebElement buttonPlaceOrder = getWebElement(wait, btnPlaceOrder);
			highlightElement(buttonPlaceOrder, driver);
			takeScreenshot("Checkout", driver);
			buttonPlaceOrder.click();
			
			WebElement msgB = getWebElement(wait, msgBuy);
			highlightElement(msgB, driver);
			
			takeScreenshot("Validation_purchase", driver);
						
		} catch (Exception e) {
			System.out.println("Error: " + e);
			takeScreenshot("Error", driver);
		}
		
		//Close Driver
		driver.quit();

	}
	
	private static void scroll(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250);");
	}
	
	private static WebElement getWebElement(WebDriverWait wait, By by) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return element;
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
