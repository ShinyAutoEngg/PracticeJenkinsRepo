package Employee;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

import genericBaseCaseUtility.BaseClassUtility;
import generic_FileUtility.FileUtility;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;
public class addEmployeeTest extends BaseClassUtility
{

	String empName="";
		
		@Test(priority=0) 
		public void addEmployee() throws IOException
		{  
			Random ran = new Random();
			int ranNum=ran.nextInt();
			empName="Deepak_"+ranNum;
			String expMsg="Employee Added Successfully";
			String jbody ="{\r\n"
					+ "  \"designation\": \"Architect\",\r\n"
					+ "  \"dob\": \"11/05/1985\",\r\n"
					+ "  \"email\": \"deepak@gmail.com\",\r\n"
					+ "  \"empName\": \""+empName+"\",\r\n"
					+ "  \"experience\": 18,\r\n"
					+ "  \"mobileNo\": \"9007970088\",\r\n"
					+ "  \"Employee\": \"TechPyramid\",\r\n"
					+ "  \"role\": \"CTO\",\r\n"
					+ "  \"username\": \""+empName+"\"\r\n"
					+ "}"; 
			
			
			
			Response resp = given().spec(reqSpecBuild)
					.body(jbody.toString()).header("Connection","Keep-alive")
			.when().post("/employees");
			resp.then().log().all();
			 String actMsg=resp.jsonPath().getString("msg");
			 String actempName=resp.jsonPath().getString("employeeName");
			 String actUsername=resp.jsonPath().getString("username");
			 Assert.assertEquals(actempName,empName);
			 Assert.assertEquals(actUsername,empName);
			 Assert.assertEquals(actMsg,expMsg);
		}
		
		@Test(priority=1)                                                          //(dependsOnMethods = "addEmployee")
		public void fronEndValidation() throws IOException, InterruptedException
		{
			flib=new FileUtility();
			String USERNAME=flib.getDataFromPropertiesFile("Login_Username");
			String PASSWORD=flib.getDataFromPropertiesFile("Login_Password");
			System.out.println(USERNAME+"/t"+PASSWORD);
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get("http://49.249.28.218:8091/");
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys(USERNAME,Keys.TAB,PASSWORD,Keys.TAB,Keys.ENTER);
			Thread.sleep(4000);
			driver.findElement(By.xpath("//a[.='Employees']")).click();
			Thread.sleep(2000);
			WebElement search=driver.findElement(By.xpath("//input[@placeholder='Search by Employee Name']"));
			search.click();
			search.sendKeys(empName);
			List<WebElement> empNames = driver.findElements(By.xpath("//table//tbody/tr[*]/td[3]"));
			for(WebElement e: empNames)
			{
				if(e.getText().equals(empName))
				{
					System.out.println("Employee Added successfully");
					break;
				}
				else
				{
					System.out.println("Employee Not added");
				}
			}
			
			
			
		}
		
		@Test
		public void databaseValidation()
		{
			
		}
		
		@Test(priority=2)                                                //(dependsOnMethods ="addEmployee")
		public void addEmployeWithDuplicateName()
		{
			String jbody ="{\r\n"
					+ "  \"designation\": \"Architect\",\r\n"
					+ "  \"dob\": \"11/05/1985\",\r\n"
					+ "  \"email\": \"deepak@gmail.com\",\r\n"
					+ "  \"empName\": \""+empName+"\",\r\n"
					+ "  \"experience\": 18,\r\n"
					+ "  \"mobileNo\": \"9007970088\",\r\n"
					+ "  \"Employee\": \"TechPyramid\",\r\n"
					+ "  \"role\": \"CTO\",\r\n"
					+ "  \"username\": \""+empName+"\"\r\n"
					+ "}"; 
			given().spec(reqSpecBuild).body(jbody)
			.when().post("/employees")
			.then().log().all().statusCode(409).extract().response();
		}
		
		@Test(priority=3) 
		public void addEmployeWithOutName()
		{
			                                //    + "  \"empName\": \""+empName+"\",\r\n"
			String jbody ="{\r\n"
					+ "  \"designation\": \"Architect\",\r\n"
					+ "  \"dob\": \"11/05/1985\",\r\n"
					+ "  \"email\": \"deepak@gmail.com\",\r\n"
					
					+ "  \"experience\": 18,\r\n"
					+ "  \"mobileNo\": \"9007970088\",\r\n"
					+ "  \"Employee\": \"TechPyramid\",\r\n"
					+ "  \"role\": \"CTO\",\r\n"
					+ "  \"username\": \""+empName+"\"\r\n"
					+ "}"; 
			given().spec(reqSpecBuild).body(jbody)
			.when().post("/employees")
			.then().log().all().statusCode(409).extract().response();
			/*
			 *  Enable logging of request and response details
			 */
			RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());

	        
		}
		
}
