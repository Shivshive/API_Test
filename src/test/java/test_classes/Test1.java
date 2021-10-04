package test_classes;

import org.testng.annotations.Test;
import java.io.File;
import java.util.HashMap;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;
import pojo.UserDetails;

public class Test1 {
	
	RequestSpecification postRequestSpec;
	
	public static UserDetails createuser() {
		Faker faker = new Faker();
		String streetName = faker.address().streetName();
		String number = faker.address().buildingNumber();
		String city = faker.address().city();
		String country = faker.address().country();
				
		UserDetails user =  new UserDetails(streetName,number,city,country);
		
//		System.out.println(user.toString());
		
		return user;
		
	}
	
	@BeforeSuite
	public void beforeSuite() {
		
		RestAssured.baseURI = "http://localhost:3000";
		RestAssured.authentication = RestAssured.basic("", "");
		postRequestSpec = new  RequestSpecBuilder()
											.setContentType("application/json")
											.setBody(createuser())
											.build();
	}
	
	@Test
	public void StatusCodeTest() {
		
		RestAssured.given(postRequestSpec).when().post("/create").then().assertThat().statusCode(200).log().body();
		
	}
	
	@Test
	public void schemaValidation() {
		
		ResponseBodyExtractionOptions res_body = RestAssured.given(postRequestSpec).when().post("/create").then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/schema.json"))).log().body().extract().body();

		JsonPath jPath = res_body.jsonPath();
		UserDetails user = jPath.getObject("request_body", UserDetails.class);

		System.out.println(user.toString());

	}

}
