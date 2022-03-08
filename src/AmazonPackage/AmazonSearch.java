package AmazonPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class AmazonSearch {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
		Class.forName("com.mysql.cj.jdbc.Driver");// Extending the driver for the connection class
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","root");	
		Statement stmt=conn.createStatement(); //Create statement object to execute SQL query
		ResultSet result= stmt.executeQuery("Select * from eproduct");//Execution of SQL Query
		WebElement searchTextBox=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchTextBox.sendKeys("IPHONE");
		WebElement searchBtn1=driver.findElement(By.id("nav-search-submit-button"));
		searchBtn1.click();
		
		List<WebElement> nameList=driver.findElements(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']/span"));
		List<WebElement> priceList=driver.findElements(By.xpath("//span[@class='a-price-whole']"));
	//	System.out.println(nameList.size());
		//System.out.println(priceList.size());
		System.out.println("The IPHONE search result - Names and Prices are :");
		for(int i=0;i<nameList.size();i++)
		{
			if (nameList.get(i).getText().toLowerCase().contains("iphone"))
			{
				System.out.println((i+1)+" "+nameList.get(i).getText()+" costs Rs."+priceList.get(i).getText());
			}
		}
		while (result.next())
		{ 	
			System.out.println("\n\n The "+ result.getString("Name")+" search result - Names and Prices are ");
		    driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).clear();
		    driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(result.getString("Name"));			
			driver.findElement(By.id("nav-search-submit-button")).click();
			//System.out.println(driver.findElements(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']/span")).size());
			//System.out.println(driver.findElements(By.xpath("//span[@class='a-price-whole']")).size());
			
			for(int i=0;i<driver.findElements(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']/span")).size();i++)
			{
				
				{
					System.out.println((i+1)+" "+driver.findElements(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']/span")).get(i).getText()+" costs Rs."+driver.findElements(By.xpath("//span[@class='a-price-whole']")).get(i).getText());
				
				}
			}
		
		}		
	    driver.close();
		conn.close();
	}
}
			
	
		

	


