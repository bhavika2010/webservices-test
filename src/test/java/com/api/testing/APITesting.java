package com.api.testing;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.testing.pojo.CreateEmployeePOJO;
import com.api.testing.pojo.CreateEmployeeResponseDataPOJO;
import com.api.testing.pojo.CreateEmployeeResponsePOJO;
import com.api.testing.pojo.EmployeeResponsePOJO;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class APITesting {

	private static final String BASE_URI = "https://dummy.restapiexample.com/api/v1";
	public static Logger logger = LogManager.getLogger(APITesting.class);

	public static Integer id = 0;

	@Test
	public static void testGetEmployees() {

		RequestSpecification req = RestAssured.given();
		Response res = req.when().get(BASE_URI + "/employees");

		res.then().statusCode(200);
		EmployeeResponsePOJO pojo = res.as(EmployeeResponsePOJO.class);

		Assert.assertEquals(pojo.getStatus(), "success");

		logger.info("Employees count : " + pojo.getData().size());
	}

	@Test
	public static void testCreateEmployee() {

		CreateEmployeePOJO requestData = new CreateEmployeePOJO();
		requestData.setAge("23");
		requestData.setName("test");
		requestData.setSalary("100");

		RequestSpecification req = RestAssured.given().contentType("application/json").body(requestData);
		Response res = req.when().post(BASE_URI + "/create");

		res.then().statusCode(200);
		CreateEmployeeResponsePOJO pojo = res.as(CreateEmployeeResponsePOJO.class);
		CreateEmployeeResponseDataPOJO data = pojo.getData();
		Assert.assertEquals(pojo.getStatus(), "success");
		Assert.assertEquals(data.getAge(), requestData.getAge());
		Assert.assertEquals(data.getName(), requestData.getName());
		Assert.assertEquals(data.getSalary(), requestData.getSalary());

		id = data.getId();
		logger.info("Employee ID : " + id);

	}

	@Test
	public static void testDeletEmployee() {
		RequestSpecification req = RestAssured.given();
		Response res = req.when().delete(BASE_URI + "/delete/" + id);

		res.getBody().prettyPrint();

		res.then().statusCode(200).body("status", equalTo("success")).body("data", equalTo(id.toString()));

	}

	@Test
	public static void testDeletEmployeeWithError() {
		Integer id = 0;
		RequestSpecification req = RestAssured.given();
		Response res = req.when().delete(BASE_URI + "/delete/" + id);

		res.then().statusCode(400).body("status", equalTo("error"));
		String message = res.getBody().jsonPath().getString("message");

		logger.info("Message : " + message);

	}
	
	@Test
	public static void testGetEmployeeDetails() {

		RequestSpecification req = RestAssured.given();
		Response res = req.when().get(BASE_URI + "/employee/2");

		res.then().statusCode(200).contentType("application/json");
		
		res.getBody().prettyPrint();
		
		JsonPath path =res.getBody().jsonPath();
		String status =path.getString("status");
		String name =path.getString("data.employee_name");
		int sal  = path.getInt("data.employee_salary");
		int age  = path.getInt("data.employee_age");
		
		Assert.assertEquals(status, "success");
		Assert.assertEquals(name, "Garrett Winters");
		Assert.assertEquals(sal, 170750);
		Assert.assertEquals(age, 63);
	}

}