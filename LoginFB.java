package com.facebook;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;




import com.xls.ReadWriteXlsx;

class LoginFB{

String url;
ChromeDriver cdriver;
ReadWriteXlsx obx;
int i;

@BeforeMethod
public void setUp() throws IOException{


obx=new ReadWriteXlsx("c:\\TestData.xlsx");
System.setProperty("webdriver.chrome.driver","C:\\Users\\spal\\workspace1\\BrowserAutomationMaven\\Driver_March_2017\\chromedriver.exe");
//System.setProperty("webdriver.chrome.driver","C:\\Users\\spal\\workspace1\\BrowserAutomationMaven\\driver\\chromedriver.exe");
cdriver=new ChromeDriver();
cdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

}

@Test(dataProvider="dpIteratorFB",dataProviderClass=com.dataprovider.AllDataProvider.class)

public void testLogin(String uname,String pwd,String row)throws Exception{

	int rindex=Integer.parseInt(row);

	url="https://www.facebook.com/";
	cdriver.get(url);

	cdriver.findElement(By.id("email")).sendKeys(uname);
	cdriver.findElement(By.id("pass")).sendKeys(pwd);
	cdriver.findElement(By.xpath("//input[@value='Log In']")).click();

	try{
		cdriver.findElement(By.id("email"));
		obx.writeData("Sheet3",rindex,2,"Fail");
		throw new IOException("Not Able to Login");

	}
	catch(NoSuchElementException e){
	obx.writeData("Sheet3",rindex,2,"Pass");
	}

}




@AfterMethod
public void tearDown(){

cdriver.close();

}















}


