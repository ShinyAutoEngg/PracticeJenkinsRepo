package genericBaseCaseUtility;

import java.io.IOException;

import org.glassfish.jersey.internal.routing.RequestSpecificConsumesProducesAcceptor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import generic_DatabaseUtility.DatabaseUtility;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import generic_FileUtility.FileUtility;




@Listeners(genericListenerUtility.ListenerImplementation.class)
public class BaseClassUtility 
{
	public DatabaseUtility dlib;
	public FileUtility flib;

	//protected FileUtility flib=new FileUtility();
	WebDriver driver =null;
	public static RequestSpecification reqSpecBuild;
	public static ResponseSpecification respSpecBuild; //not required -just to know
	
	@BeforeSuite
	public void configBS() throws IOException
	{
		RequestSpecBuilder reqSpec = new RequestSpecBuilder();
		reqSpec.setContentType(ContentType.JSON);
		flib=new FileUtility();
		String baseUri=flib.getDataFromPropertiesFile("BaseURL");
		reqSpec.setBaseUri(baseUri);
		reqSpecBuild = reqSpec.build();
		ResponseSpecBuilder resB = new ResponseSpecBuilder();
		resB.expectContentType(ContentType.JSON);
		//resB.expectHeader("Authorization", "Bearer "+token);  just to know
	    respSpecBuild = resB.build();
		System.out.println("Base Class ===>Before Suite");
	}
	
	@BeforeClass
	public void configBC()
	{
		System.out.println("Base Class ===>Before Class");
	}
	
	@BeforeMethod
	public void configBM()
	{
		System.out.println("Base Class ===>Before Method");
	}
	
	@AfterMethod
	public void configAM()
	{
		System.out.println("Base Class ===>After Method");
	}
	
	@AfterClass
	public void configAC()
	{
		System.out.println("Base Class ===>After Class");
	}
	
	@AfterSuite
	public void configAS()
	{
		System.out.println("Base Class ===>After Suite");
	}
}
