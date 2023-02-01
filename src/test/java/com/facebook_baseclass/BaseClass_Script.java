package com.facebook_baseclass;

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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import com.facebook.FaceBookProject.Base_Class;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass_Script extends Base_Class {
	public static WebDriver driver;

	public static void main(String[] args) throws Exception {
		driver = browserLaunch("chrome");

		String title = "facebook";
		String url = "https://www.facebook.com/";
		// driver.get(url);
		launchUrl(url);

//		String title1 = "facebook";
//		String url = "https://www.facebook.com";
//		launchUrl(url);//driver.get("https://www.facebook.com");
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String title1 = getTitle();

		System.out.println(title1);

		if (url.contains(title1)) {
			System.out.println("Correcturl");
		} else {
			System.out.println("not correct url");
		}

		sleep();
		// WebElement email = driver.findElement(By.id("email"));
		WebElement email = driver.findElement(By.name("email"));
		userInput(email, "vijay123@gmail");

		WebElement pass = driver.findElement(By.id("pass"));
		userInput(pass, "123458");

		WebElement log = driver.findElement(By.name("login"));
		clickOnElement(log);
		capture(driver);
		driver.navigate().to(url);
		sleep();
		WebElement newacct = driver.findElement(By.xpath("//a[text()='Create new account']"));
		clickOnElement(newacct);
		sleep();
		//WebElement firstname = driver.findElement(By.name("firstname"));
		WebElement firstname = driver.findElement(By.name("firstname"));
		userInput(firstname, "vijay");
		WebElement lastname = driver.findElement(By.name("lastname"));
		userInput(lastname, "m");

		WebElement regemail = driver.findElement(By.name("reg_email__"));
		userInput(regemail, "vijay1432@gmail.com");

		WebElement newpass = driver.findElement(By.xpath("(//input[@type='password'])[2]"));
		userInput(newpass, "59745545");

		WebElement day = driver.findElement(By.name("birthday_day"));
		Select s = new Select(day);
		boolean multiple = selectIsMul(day);
		System.out.println(multiple);
		s.selectByIndex(4);
		WebElement month = driver.findElement(By.id("month"));
		Select s1 = new Select(month);
		s1.selectByValue("3");
		WebElement year = driver.findElement(By.name("birthday_year"));
		Select s2 = new Select(year);
		s2.selectByVisibleText("1997");

		WebElement sex = driver.findElement(By.xpath("//label[text()='Male']"));
		sex.click();

		WebElement signup = driver.findElement(By.xpath("(//button[text()='Sign Up'])[1]"));
		clickOnElement(signup);

		capture(driver);

		driver.findElement(By.xpath("//img[@class='_8idr img']")).click();
		String title2 = getTitle();
		System.out.println("current page title" + title2);
		if (title1.equals(title2)) {
			System.out.println("currentpage and initial user launched is same");
		} else {
			windowQuit();
		}
	}
}
