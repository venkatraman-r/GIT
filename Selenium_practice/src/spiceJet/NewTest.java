package spiceJet;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class NewTest {
	
WebDriver driver;
screenshot scr;
 /* @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
  }*/
  @BeforeMethod
  public void beforeMethod() throws MalformedURLException {
	  
	  //DesiredCapabilities capabilities = new DesiredCapabilities();
	  //capabilities.setBrowserName("firefox");
	  //capabilities.setPlatform(Platform.WINDOWS);
	 
	 // capabilities.setVersion("43.0.1");
	  
	  //driver = new RemoteWebDriver(new URL("192.168.0.104"),capabilities);
	  driver = new FirefoxDriver();
	  
	  //Maximize the browser
	  driver.manage().window().maximize();
	  //driver.get("https://www.spicejet.com/");
	  driver.navigate().to("https://www.spicejet.com/");
  }

  @Test(priority = 1, groups = {"First Page"})
  public void spicefirstpage()
  {
	Assert.assertEquals(driver.getTitle(), "SpiceJet - Flight Booking for Domestic and International, Cheap Air Tickets");
	new screenshot(driver).screenprint("Titlepage");
	try
	{
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		System.out.println("Alert present with this text  "+alert.getText()+"and dismissed");
	}
	catch(NoAlertPresentException e)
	{
	System.out.println("Alert not present");	
	}
	finally
	{
		//System.out.println("Finally block executed without catch");
	}
  }
  
  @Test(priority = 2,groups = {"First Page"})
  public void spicemenu1()
  {
	  List<WebElement> smoothmenu1 = driver.findElements(By.id("smoothmenu1"));
	  
	  for (WebElement smoothmenuelement : smoothmenu1)
	  {
		  Actions action = new Actions(driver);
		  action.moveToElement(smoothmenuelement).perform();
		  try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  System.out.println(smoothmenuelement.getText());
	  }
	  
	 List<WebElement> traveloptions = driver.findElements(By.xpath("//*[@id='travelOptions']//tr//td"));
	 
	for(int i=1;i<traveloptions.size();i++)
	{
			WebElement option = driver.findElement(By.xpath("//*[@id='travelOptions']//tr//td["+i+"]"));
			
			boolean flag = option.isSelected();
			
			if(flag)
			{
				System.out.println("The Selected option is "+traveloptions.get(i).getText());	
			}
			else
			{
				System.out.println("The option are "+traveloptions.get(i).getText());	
			}
	}
		

  }
  
  @Test(priority = 3,groups = {"First Page"})
  public void FromandTO() throws ClassNotFoundException, SQLException
  {
	 
	  Connection conn;
	  Statement stmt;
	  
	  ResultSet resultset;
	 
	  Class.forName("com.mysql.jdbc.Driver");
	  
	  conn = DriverManager.getConnection("jdbc:mysql://MC0XYC9C/TestSelenium");
	  
	  stmt = conn.createStatement();
	  
	  resultset = stmt.executeQuery("Select * from spicejetplace");
	  
	  System.out.println(resultset.getString(1));
	  
	 driver.findElement(By.xpath("//*[@id='ctl00_mainContent_ddl_originStation1_CTXT']")).sendKeys("MAA");
	 String IndiaPlaces = driver.findElement(By.xpath("//input[@id='ctl00_mainContent_group_details']")).getAttribute("value");
	 String[] Places = IndiaPlaces.split("\\|\\|");

	 System.out.println(Arrays.toString(Places));
	// System.out.println(Places.length);
	 
	 String comp[] = {"AMD","AIP","MAA"};
	 ArrayList<String> result = new ArrayList<String>();
	 
	 for(int i=0;i<Places.length;i++)
	 {
		 for(int j=0;j<comp.length;j++)
		 {
			 if(!Places[i].equals(comp[j]))
			 {
				 result.add(Places[i]);	 
				
			 }
			 else
			 {
				 continue;
			}
		 }
	 }
	 
	 System.out.println(result.size());
	 
	 for(String str : result)
	 {
		 System.out.println(str);
	 }
	 
	 }
  /*
  
  
  @AfterMethod
  public void afterMethod() {
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }*/

}
