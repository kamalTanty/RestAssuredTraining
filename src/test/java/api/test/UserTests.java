package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setupData()
	{
		//generate the data
		faker= new Faker();
		userPayload= new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUserName(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority=1)
	public void testPostuser()
	{
	Response response =	UserEndPoints.createUser(userPayload);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		
		Response response =UserEndPoints.readUser(this.userPayload.getUserName());
		
		response.then().log().all();
		
		//Assert.assertEquals(response.getStatusCode(), 200);
	}
}
