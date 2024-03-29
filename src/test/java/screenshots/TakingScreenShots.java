package screenshots;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TakingScreenShots {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		//maximize the window 
		driver.manage().window().maximize();

	}


	@Test(description="Screenshot of a complete Page")
	public void screenShotofaPage() throws InterruptedException, IOException {

		driver.get("https://www.ebay.com");

		//TakesScreenshot type object initialized
		TakesScreenshot ts = (TakesScreenshot)driver;
		//take the screenshot and save it as src format
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		//create Screenshots folder and specify the destination
		String destination = System.getProperty("user.dir")+"/Screnshots/" + "homePage" + ".png";
		
		//File type object initialized
		File target = new File(destination);
		
		/*
		If folder already there use this
		File target = new File(".\\Screnshots\\homePage.png");
		*/
		
		//copy src file to target file 
		FileUtils.copyFile(src,target);

	}

	@Test(description="Screenshot of a Section In a Page")
	public void screenShotofaSectionInaPage() throws IOException {

		driver.get("https://www.ebay.com");

		//Catch the section
		WebElement pageSection = driver.findElement(By.xpath("//*[@id=\"destinations_list1\"]/ul"));

		//Call method to highlight
		highlightElement(pageSection,driver);
		
		//take the screenshot and save it as src format
		File src = pageSection.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"/Screnshots/" + "pageSection" + ".png";
		File target = new File(destination);
		FileUtils.copyFile(src,target);

	}

	@Test(description="Screenshot of a Section In a Page")
	public void screenShotofaElement() throws IOException {

		driver.get("https://www.ebay.com");

		WebElement ElementLogo = driver.findElement(By.xpath("//*[@id=\"gh-la\"]"));
		highlightElement(ElementLogo,driver);
		File src = ElementLogo.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"/Screnshots/" + "ElementLogo" + ".png";
		File target = new File(destination);
		FileUtils.copyFile(src,target);

	}

	public void highlightElement (WebElement element, WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='2px solid red'", element);

	}


	@AfterMethod
	public void tearDown() {

		driver.close();

	}




}
