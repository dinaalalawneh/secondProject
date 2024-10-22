package newwebsite;

import java.awt.Container;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewWebsite {
	WebDriver driver = new ChromeDriver();

	String myWebsite = "https://automationteststore.com/";
	String[] firstNames = { "ahmad", "ali", "anas", "omar", "ayat", "alaa", "sawsan", "Rama" };
	String[] LastNames = { "Khaled", "mustafa", "Mohammad", "abdullah", "malek", "omar" };

	Random rand = new Random();

	@BeforeTest
	public void mySetup() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(myWebsite);

	}

	@Test(priority = 1)
	public void signUp() throws InterruptedException {

		int RandomIndexForTheFirstName = rand.nextInt(firstNames.length);
		int RandomIndexForTheLastName = rand.nextInt(LastNames.length);

		String UserFirstName = firstNames[RandomIndexForTheFirstName];
		String UserLastName = LastNames[RandomIndexForTheLastName];

		int randomNumberForTheEmail = rand.nextInt(564548);

		String domainName = "@gmail.com";

		String email = UserFirstName + UserLastName + randomNumberForTheEmail + domainName;

		;

		// take the full text only work with the (a)tag
		driver.findElement(By.linkText("Login or register")).click();

		WebElement SignUpButton = driver.findElement(By.xpath("//button[@title='Continue']"));

		SignUpButton.click();

		Thread.sleep(2000);

		WebElement FirstNameInput = driver.findElement(By.id("AccountFrm_firstname"));
		FirstNameInput.sendKeys(UserFirstName);
		WebElement LastNameInput = driver.findElement(By.id("AccountFrm_lastname"));
		LastNameInput.sendKeys(UserLastName);
		WebElement EmailInput = driver.findElement(By.id("AccountFrm_email"));
		EmailInput.sendKeys(email);
		WebElement AdressInput = driver.findElement(By.id("AccountFrm_address_1"));
		AdressInput.sendKeys("amman city - tlaa al ali");
		WebElement CityInput = driver.findElement(By.id("AccountFrm_city"));
		CityInput.sendKeys("capital city");

		WebElement CountryInput = driver.findElement(By.id("AccountFrm_country_id"));

		Select selector2 = new Select(CountryInput);

		int randomCountry = rand.nextInt(1, 240);

		selector2.selectByIndex(randomCountry);

		Thread.sleep(6000);

		WebElement ZoneIdInput = driver.findElement(By.id("AccountFrm_zone_id"));
		Select selector = new Select(ZoneIdInput);
		int randomState = rand.nextInt(1, 6);

		selector.selectByIndex(randomState);

		WebElement PostalCodeInput = driver.findElement(By.id("AccountFrm_postcode"));
		PostalCodeInput.sendKeys("13310");
		WebElement LoginNameInput = driver.findElement(By.id("AccountFrm_loginname"));

		LoginNameInput.sendKeys(UserFirstName + UserLastName + randomNumberForTheEmail);
		WebElement PasswordInput = driver.findElement(By.id("AccountFrm_password"));
		PasswordInput.sendKeys("iLoveMyMom123@");
		WebElement ConfirmPasswordInput = driver.findElement(By.id("AccountFrm_confirm"));
		ConfirmPasswordInput.sendKeys("iLoveMyMom123@");

		WebElement AgreeCheckBox = driver.findElement(By.id("AccountFrm_agree"));
		AgreeCheckBox.click();
		Thread.sleep(2000);
		WebElement ContinueButton = driver.findElement(By.xpath("//button[@title='Continue']"));

		ContinueButton.click();
		WebElement WelcomeBack = driver.findElement(By.xpath("//*[@id=\"customer_menu_top\"]/li/a/div"));
		Actions actions = new Actions(driver);
		actions.moveToElement(WelcomeBack).perform();
		Thread.sleep(2000);
		WebElement LogOut = driver.findElement(By.xpath("/html/body"));
		LogOut.click();
	}

	//@Test (priority=2){
		
		@Test(priority = 2)

		public void AddItemToThecart() throws InterruptedException {

			String[] WebSitesForTheItems = { "https://automationteststore.com/index.php?rt=product/category&path=68",
			"https://automationteststore.com/index.php?rt=product/category&path=36",
	  		"https://automationteststore.com/index.php?rt=product/category&path=43",
					"https://automationteststore.com/index.php?rt=product/category&path=49",
					"https://automationteststore.com/index.php?rt=product/category&path=58",
				"https://automationteststore.com/index.php?rt=product/category&path=52",
					"https://automationteststore.com/index.php?rt=product/category&path=65" };
			
			int Items =rand.nextInt(WebSitesForTheItems.length);
			String BasicItems = WebSitesForTheItems[Items];
			driver.get(BasicItems);
			Thread.sleep(3000);
			

			WebElement subElem =driver.findElement(By.cssSelector(".thumbnails.row"));
			int totalNumberOfItems = subElem.findElements(By.tagName("li")).size();
			int randomITem =rand.nextInt(totalNumberOfItems);
			subElem.findElements(By.tagName("li")).get(randomITem).click();
			Thread.sleep(2000);
			WebElement InterialIT = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));
	     	int subi =  InterialIT.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).size();
	     	int Getrand =rand.nextInt(subi);
	     	 InterialIT.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).get(Getrand).click();
	     	 
	     	WebElement ULList = driver.findElement(By.className("productpagecart"));

			
			int LiItem = ULList.findElements(By.tagName("li")).get(0).findElements(By.tagName("span")).size();

			
			if (LiItem > 0) {
				driver.get(myWebsite);
				Thread.sleep(2000);

;System.out.println("sorry the item out of the stock ");
String Expected ="https://automationteststore.com/";
String Actual = driver.getCurrentUrl();
Assert.assertEquals(Actual, Expected);
				
			} else {

				driver.findElement(By.className("cart")).click();
				Thread.sleep(2000);
				String ExpetedResult ="https://automationteststore.com/index.php?rt=checkout/cart";
String ActualResult =driver.getCurrentUrl();
Assert.assertEquals(ActualResult, ExpetedResult);
			}
		
	     	
	     	
	     
			   
		
			
	    
	     	
			
			
	     	 
	}

		
	
	
	
}
