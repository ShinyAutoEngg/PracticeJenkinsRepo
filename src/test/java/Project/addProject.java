package Project;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.Random;

import genericBaseCaseUtility.BaseClassUtility;
import generic_DatabaseUtility.DatabaseUtility;
import generic_FileUtility.FileUtility;
import io.restassured.response.Response;

public class addProject extends BaseClassUtility
{
	
	String projName="";

	@Test
	public void addSingleProjectWithCreated() throws IOException
	{
		Random ran = new Random();
		int ranNum=ran.nextInt();
		projName="Apocalypse_"+ranNum;
		
		String jobj="{\r\n"
				+ "  \"createdBy\": \"ShinyIngrid\",\r\n"
				+ "  \"projectName\": \""+projName+"\",\r\n"
				+ "  \"status\": \"Created\",\r\n"
				+ "  \"teamSize\": 0\r\n"    
				+ "}";
		//teamsize has to be 0 per process 
		Response resp=given().spec(reqSpecBuild).body(jobj).when().post("/addProject");
		resp.then().log().all();
		String actProjName=resp.jsonPath().getString("projectName");
		String actStatus=resp.jsonPath().getString("status");
		Assert.assertEquals(actProjName, projName);
		Assert.assertEquals(actStatus, "Created");
		resp.then().assertThat().statusCode(201).time(Matchers.lessThan(3000L));
		
		String DBURL=flib.getDataFromPropertiesFile("DBURL");
		String USERNAME=flib.getDataFromPropertiesFile("DB_Username");
		String PASSWORD=flib.getDataFromPropertiesFile("DB_Password");
		//dlib.verifyDataInDB(DBURL, USERNAME, PASSWORD);
	}
}
