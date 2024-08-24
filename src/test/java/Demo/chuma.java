package Demo;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import static org.hamcrest.Matchers.*;


import io.restassured.response.Response;

public class chuma 
{
	
	
	@Test
	public void trial()
	{
		Response resp = given()
		.when().get("https://reqres.in/api/users");
		resp.then().log().all();
		resp.then().assertThat().statusCode(200).time(lessThan(3000L));
		String url=resp.jsonPath().getString("support.url");
		int page=resp.jsonPath().getInt("page");
		System.out.println("url :"+url);
		System.out.println("page :"+page);
		String id=JsonPath.read(resp.asString(), "data[*].[?(@.email=='janet.weaver@reqres.in')].id").toString();
		System.out.println("ID of Janet :"+id);
		System.out.println(resp.getStatusLine());
		System.out.println(resp.getContentType());
		System.out.println(resp.getHeader("Server"));
		System.out.println(resp.getTime());
		System.out.println(resp.getBody().jsonPath().getInt("total"));
		String fn=resp.path("data[2].first_name");
		System.out.println(fn);
		
		
	}
}
