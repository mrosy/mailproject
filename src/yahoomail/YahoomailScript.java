package yahoomail;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YahoomailScript 
{
	
	public static void main(String[] args) throws FileNotFoundException, JSONException, InterruptedException 
	{
		
		System.setProperty("webdriver.chrome.driver", "./src/browerdrivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait webForElement;
		JSONTokener token = new JSONTokener(new FileReader("D:\\yahoo.json"));
		JSONObject jsonobj = new JSONObject(token);
		
		driver.get(jsonobj.getString("BaseUrl"));
		//login
		driver.findElement(By.id("login-username")).sendKeys(jsonobj.getString("EmailId"));
		driver.findElement(By.id("login-signin")).click();
		webForElement=new WebDriverWait(driver,30);
		webForElement.until(ExpectedConditions.presenceOfElementLocated(By.id("login-passwd")));
		driver.findElement(By.id("login-passwd")).sendKeys(jsonobj.getString("Password"));
		driver.findElement(By.id("login-signin")).click();
		Thread.sleep(2000);
		
		//Get the total number of mails
		driver.findElement(By.xpath("//*[@id=\"mail-app-component\"]/div[1]/div/div[1]/div/div[1]/div/div/ul/li[2]/div/span/button")).click();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[7]/div/div[1]/div/ul/li[1]")).click();
		Thread.sleep(1000);
		String totalMails = driver.findElement(By.xpath("//*[@id=\"mail-app-component\"]/div[1]/div/div[1]/div/div[1]/div/div/ul/li[3]/div")).getText();
		System.out.println("Total mails in inbox " +totalMails);
				
		//number of unread mails
		String inbox = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/nav/div/div[3]/div[1]/ul/li[1]/div/a/span[2]/span")).getText();
		int inboxmails = Integer.parseInt(inbox);
		if(inboxmails>0)
		{
			System.out.println("You have Unread mail(s) in inbox");
			System.out.println("Total no of unread mails " +Integer.parseInt(inbox));
			
			
		}
		else
		{
			System.out.println("No new mails");
		}
		
		//open first unread mail
		List<WebElement> mailsObj=driver.findElements(By.xpath("//*[@class='o_h G_e J_x en_N u_b']"));
		if(mailsObj.size()>0)
		  {
			  mailsObj.get(0).click();
			  String fromAddress = driver.findElement(By.xpath("//*[@class='C4_Z2aVTcY']")).getText();
			  System.out.println(fromAddress);
		  }
	}
}
		 
		
		  
		 
		  
			
	


