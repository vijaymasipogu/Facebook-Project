package com.basicscript_facebooktestng;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Facebook_Testng {
	public static WebDriver driver;
	 ChromeDriverService options = null;
	 String url;
	 String title1 = null;
	 
	@BeforeSuite
	public static List<String> listofchromeoptions() {
	List<String> listofoptions = new ArrayList<>();
	listofoptions.add("start-maximized");
//	listofoptions.add("Incognito");
	return listofoptions;
	}
	@BeforeTest(groups = "execution")
	private void browerLaunch() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments(listofchromeoptions());
        WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver(options);

	}
	@Test(priority = 0,groups = "execution")
    private void launchUrl() {
		String title = "facebook";
		url = "https://www.facebook.com/";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		title1 = driver.getTitle();

		System.out.println(title1);
		if (url.contains(title)) {
			System.out.println("Launch the url correctly");

		} else {
			System.out.println("not a correct url");
		}
		

	}
	@Test(priority = 1,groups = "execution")
	private void loginClick() throws InterruptedException {
		Thread.sleep(3000);
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("vijay123@gmail");
		WebElement pass = driver.findElement(By.id("pass"));
		pass.click();
		WebElement login = driver.findElement(By.name("login"));
		login.click();
		System.out.println("2");
	}
	@Test(priority = 2,groups = "execution1")
	private void screenshot1() throws IOException, InterruptedException {
		TakesScreenshot s = (TakesScreenshot) driver;

		File s1 = s.getScreenshotAs(OutputType.FILE);

		File f = new File(
				"C:\\Users\\lenovo\\eclipse-workspace\\FaceBookProject\\screenshots\\screenshot1.png");
		FileHandler.copy(s1, f);
		Thread.sleep(1000);
		
		
	}
	@Test(priority = 3,groups = "execution1")
	private void newRegistration() throws InterruptedException {
		Thread.sleep(5000);
		driver.navigate().to(url);
		WebElement createaccount = driver.findElement(By.xpath("//a[text()='Create New Account']"));
		createaccount.click();
		Thread.sleep(1000);
		WebElement firstname = driver.findElement(By.name("firstname"));
		firstname.sendKeys("vijay");
		WebElement lastname = driver.findElement(By.name("lastname"));
		lastname.sendKeys("m");
		WebElement mobileno = driver.findElement(By.name("reg_email__"));
		mobileno.sendKeys("vijay143@gmail");
		WebElement newpassword = driver.findElement(By.name("reg_passwd__"));
		newpassword.sendKeys("vj@12345");
		WebElement birthday = driver.findElement(By.name("birthday_day"));
		Select sel = new Select(birthday);
		boolean multiple = sel.isMultiple();
		System.out.println(multiple);

		sel.selectByIndex(16);
		WebElement birthmonth = driver.findElement(By.name("birthday_month"));
		Select sel1 = new Select(birthmonth);
		sel1.selectByValue("4");
		WebElement birthyear = driver.findElement(By.name("birthday_year"));
		Select sel2 = new Select(birthyear);
		sel2.selectByVisibleText("1997");
		WebElement sex = driver.findElement(By.xpath("//label[text()='Male']"));
		sex.click();
		WebElement submit = driver.findElement(By.xpath("//button[text()='Sign Up']"));
		submit.click();
		Thread.sleep(1000);
		
	}
	@Test(dependsOnMethods = "newRegistration",groups = "execution1")
    private void ascreenshot2() throws IOException, InterruptedException {
		
		TakesScreenshot t = (TakesScreenshot) driver;

		File s2 = t.getScreenshotAs(OutputType.FILE);

		File f1 = new File(
				"C:\\\\Users\\\\lenovo\\\\eclipse-workspace\\\\FaceBookProject\\\\screenshots\\\\screenshot2.png");
		FileUtils.copyFile(s2, f1);
		
		WebElement close = driver.findElement(By.xpath("//img[@class='_8idr img']"));
		close.click();
		String title2 = driver.getTitle();
		System.out.println("current page titile is:" + title2);
		Thread.sleep(1000);
		
		if (title1.equals(title2)) {
			System.out.println("currentpage and intial user launched page is same");

		} else {
			driver.quit();
	}
		
    }
	@AfterTest
    private void browserClose() {
    driver.close();
}
}