package com.qa.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import com.qa.base.TestBase;
import com.qa.util.Log4j;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class AutomateApiUsingRestassured extends TestBase {

	public AutomateApiUsingRestassured() throws IOException {
		super();

	}

	TestBase testBase;
	String url;
	String ListallCustomerEndpoint;
	String CustomerFound1Endpoint;
	String CustomerFound2Endpoint;
	String CustomerFound3Endpoint;
	String CustomerFound4Endpoint;
	String CustomerFound5Endpoint;
	String CustomerFound6Endpoint;
	String CustomerNotFoundEndpoint;
	String BaseUrl;
	String APIUrl;
	String APIUrl1;
	String APIUrl2;
	String APIUrl3;
	String APIUrl4;
	String APIUrl5;
	String APIUrl6;
	String APIUrl7;
	String url1;
	String url2;
	String url3;
	String url4;
	String url5;
	String url6;
	String url7;

	@BeforeMethod
	public void setup() throws IOException {

		Log4j.logupdate();
		Log4j.testStart();

		testBase = new TestBase();

		BaseUrl = prop.getProperty("URL");
		APIUrl = prop.getProperty("ListallCustomerEndpoint");
		url = BaseUrl + APIUrl;

		APIUrl1 = prop.getProperty("CustomerFound1Endpoint");
		url1 = BaseUrl + APIUrl1;

		APIUrl2 = prop.getProperty("CustomerFound2Endpoint");
		url2 = BaseUrl + APIUrl2;

		APIUrl3 = prop.getProperty("CustomerFound3Endpoint");
		url3 = BaseUrl + APIUrl3;

		APIUrl4 = prop.getProperty("CustomerFound4Endpoint");
		url4 = BaseUrl + APIUrl4;

		APIUrl5 = prop.getProperty("CustomerFound5Endpoint");
		url5 = BaseUrl + APIUrl5;

		APIUrl6 = prop.getProperty("CustomerFound6Endpoint");
		url6 = BaseUrl + APIUrl6;

		APIUrl7 = prop.getProperty("CustomerNotFoundEndpoint");
		url7 = BaseUrl + APIUrl7;

	}

	@Test(priority = 1)
	public void ListsAllCustomerDetails() throws JsonMappingException, JsonProcessingException {
		Log4j.testStart();
		try {
			RestAssured.baseURI = url;
			RequestSpecification httprequest = RestAssured.given();
			Response response = httprequest.get();

			String contentType = response.header("Content-Type");
			System.out.println("Content-Type is : " + contentType);

			String serverType = response.header("Date");
			System.out.println("DATE is : " + serverType);

			String acceptLanguage = response.header("Transfer-Encoding");
			System.out.println("Transfer-Encoding is : " + acceptLanguage);

			ResponseBody<?> body = response.getBody();
			String bodyAsString = body.asString();
			System.out.println(bodyAsString);

			String json = bodyAsString;
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(json);

			JsonNode arr = jsonNode.findValue("data");
			for (JsonNode node : arr) {
				node.get("id");
				node.get("email");
				node.get("first_name");
				node.get("last_name");
				System.out.println("node is " + "  " + node);
				Assert.assertEquals(bodyAsString.contains("id"), true, "Response body contains id");
				Assert.assertEquals(bodyAsString.contains("email"), true, "Response body contains email");
				Assert.assertEquals(bodyAsString.contains("first_name"), true, "Response body contains first_name");
				Assert.assertEquals(bodyAsString.contains("last_name"), true, "Response body contains last_name");

			}
			Log4j.info("Successfully Retrieved the LIST_ALL_CUSTOMERS Response");
			Reporter.log("Successfully Retrieved the LIST_ALL_CUSTOMERS Response");
			Log4j.testEnd();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void CustomerDetailsFound1() throws JsonMappingException, JsonProcessingException {
		Log4j.testStart();
		try {
			RestAssured.baseURI = url1;
			RequestSpecification httprequest = RestAssured.given();
			Response response = httprequest.get();

			int statusCode = response.getStatusCode();
			System.out.println("The Statuscode is" + " " + statusCode);
			Assert.assertEquals(statusCode, 200, "Correct status code returned");

			String contentType = response.header("Content-Type");
			System.out.println("Content-Type is : " + contentType);

			String serverType = response.header("Date");
			System.out.println("DATE is : " + serverType);

			String acceptLanguage = response.header("Transfer-Encoding");
			System.out.println("Transfer-Encoding is : " + acceptLanguage);

			ResponseBody<?> body = response.getBody();
			String bodyAsString = body.asString();
			System.out.println(bodyAsString);

			String json = bodyAsString;
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(json);

			String status = jsonNode.findValue("status").asText();
			System.out.println("The Status is" + "  " + status);
			Assert.assertEquals(status, "success", "Correct status  received in the Response");

			String code = jsonNode.findValue("code").asText();
			System.out.println("The Status is" + "  " + code);
			Assert.assertEquals(code, "0", "Correct code  received in the Response");

			String customerID = jsonNode.findValue("customerID").asText();
			System.out.println("The customerID is" + "  " + customerID);
			Assert.assertEquals(customerID, "1111", "Correct customerID  received in the Response");

			String email = jsonNode.findValue("email").asText();
			System.out.println("The emailId is" + "  " + email);
			Assert.assertEquals(email, "testerA@abc.com", "Correct email received in the Response");

			String first_name = jsonNode.findValue("first_name").asText();
			System.out.println("The first_name is" + "  " + first_name);
			Assert.assertEquals(first_name, "testerBFirst", "Correct firstname  received in the Response");

			String last_name = jsonNode.findValue("last_name").asText();
			System.out.println("The last_name is" + "  " + last_name);
			Assert.assertEquals(last_name, "testerBLast", "Correct lastname  received in the Response");
			Log4j.info("Successfully Retrieved the CUSTOMERDETAILS-1 Response");
			Reporter.log("Successfully Retrieved the CUSTOMERDETAILS-1 Response");
			Log4j.testEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void CustomerDetailsFound2() throws JsonMappingException, JsonProcessingException {
		Log4j.testStart();
		try {
			RestAssured.baseURI = url2;
			RequestSpecification httprequest = RestAssured.given();
			Response response = httprequest.get();

			int statusCode = response.getStatusCode();
			System.out.println("The Statuscode is" + " " + statusCode);
			Assert.assertEquals(statusCode, 200, "Correct status code returned");

			String contentType = response.header("Content-Type");
			System.out.println("Content-Type is : " + contentType);

			String serverType = response.header("Date");
			System.out.println("DATE is : " + serverType);

			String acceptLanguage = response.header("Transfer-Encoding");
			System.out.println("Transfer-Encoding is : " + acceptLanguage);

			ResponseBody<?> body = response.getBody();
			String bodyAsString = body.asString();
			System.out.println(bodyAsString);

			String json = bodyAsString;
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(json);

			String status = jsonNode.findValue("status").asText();
			System.out.println("The Status is" + "  " + status);
			Assert.assertEquals(status, "success", "Correct status  received in the Response");

			String code = jsonNode.findValue("code").asText();
			System.out.println("The Status is" + "  " + code);
			Assert.assertEquals(code, "0", "Correct code  received in the Response");

			String customerID = jsonNode.findValue("customerID").asText();
			System.out.println("The customerID is" + "  " + customerID);
			Assert.assertEquals(customerID, "2222", "Correct customerID  received in the Response");

			String email = jsonNode.findValue("email").asText();
			System.out.println("The emailId is" + "  " + email);
			Assert.assertEquals(email, "testerB@abc.com", "Correct email received in the Response");

			String first_name = jsonNode.findValue("first_name").asText();
			System.out.println("The first_name is" + "  " + first_name);
			Assert.assertEquals(first_name, "testerBFirst", "Correct firstname  received in the Response");

			String last_name = jsonNode.findValue("last_name").asText();
			System.out.println("The last_name is" + "  " + last_name);
			Assert.assertEquals(last_name, "testerBLast", "Correct lastname  received in the Response");
			Log4j.info("Successfully Retrieved the CUSTOMERDETAILS-2 Response");
			Reporter.log("Successfully Retrieved the CUSTOMERDETAILS-2 Response");
			Log4j.testEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 4)
	public void CustomerDetailsFound3() throws JsonMappingException, JsonProcessingException {
		Log4j.testStart();
		try {
			RestAssured.baseURI = url3;
			RequestSpecification httprequest = RestAssured.given();
			Response response = httprequest.get();

			int statusCode = response.getStatusCode();
			System.out.println("The Statuscode is" + " " + statusCode);
			Assert.assertEquals(statusCode, 200, "Correct status code returned");

			String contentType = response.header("Content-Type");
			System.out.println("Content-Type is : " + contentType);

			String serverType = response.header("Date");
			System.out.println("DATE is : " + serverType);

			String acceptLanguage = response.header("Transfer-Encoding");
			System.out.println("Transfer-Encoding is : " + acceptLanguage);

			ResponseBody<?> body = response.getBody();
			String bodyAsString = body.asString();
			System.out.println(bodyAsString);

			String json = bodyAsString;
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(json);

			String status = jsonNode.findValue("status").asText();
			System.out.println("The Status is" + "  " + status);
			Assert.assertEquals(status, "success", "Correct status  received in the Response");

			String code = jsonNode.findValue("code").asText();
			System.out.println("The Status is" + "  " + code);
			Assert.assertEquals(code, "0", "Correct code  received in the Response");

			String customerID = jsonNode.findValue("customerID").asText();
			System.out.println("The customerID is" + "  " + customerID);
			Assert.assertEquals(customerID, "3333", "Correct customerID  received in the Response");

			String email = jsonNode.findValue("email").asText();
			System.out.println("The emailId is" + "  " + email);
			Assert.assertEquals(email, "testerC@abc.com", "Correct email received in the Response");

			String first_name = jsonNode.findValue("first_name").asText();
			System.out.println("The first_name is" + "  " + first_name);
			Assert.assertEquals(first_name, "testerCFirst", "Correct firstname  received in the Response");

			String last_name = jsonNode.findValue("last_name").asText();
			System.out.println("The last_name is" + "  " + last_name);
			Assert.assertEquals(last_name, "testerCLast", "Correct lastname  received in the Response");
			Log4j.info("Successfully Retrieved the CUSTOMERDETAILS-3 Response");
			Reporter.log("Successfully Retrieved the CUSTOMERDETAILS-3 Response");
			Log4j.testEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 5)
	public void CustomerDetailsFound4() throws JsonMappingException, JsonProcessingException {
		Log4j.testStart();
		try {
			RestAssured.baseURI = url4;
			RequestSpecification httprequest = RestAssured.given();
			Response response = httprequest.get();

			int statusCode = response.getStatusCode();
			System.out.println("The Statuscode is" + " " + statusCode);
			Assert.assertEquals(statusCode, 200, "Correct status code returned");

			String contentType = response.header("Content-Type");
			System.out.println("Content-Type is : " + contentType);

			String serverType = response.header("Date");
			System.out.println("DATE is : " + serverType);

			String acceptLanguage = response.header("Transfer-Encoding");
			System.out.println("Transfer-Encoding is : " + acceptLanguage);

			ResponseBody<?> body = response.getBody();
			String bodyAsString = body.asString();
			System.out.println(bodyAsString);

			String json = bodyAsString;
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(json);

			String status = jsonNode.findValue("status").asText();
			System.out.println("The Status is" + "  " + status);
			Assert.assertEquals(status, "success", "Correct status  received in the Response");

			String code = jsonNode.findValue("code").asText();
			System.out.println("The Status is" + "  " + code);
			Assert.assertEquals(code, "0", "Correct code  received in the Response");

			String customerID = jsonNode.findValue("customerID").asText();
			System.out.println("The customerID is" + "  " + customerID);
			Assert.assertEquals(customerID, "3333", "Correct customerID  received in the Response");

			String email = jsonNode.findValue("email").asText();
			System.out.println("The emailId is" + "  " + email);
			Assert.assertEquals(email, "testerD@abc.com", "Correct email received in the Response");

			String first_name = jsonNode.findValue("first_name").asText();
			System.out.println("The first_name is" + "  " + first_name);
			Assert.assertEquals(first_name, "testerDFirst", "Correct firstname  received in the Response");

			String last_name = jsonNode.findValue("last_name").asText();
			System.out.println("The last_name is" + "  " + last_name);
			Assert.assertEquals(last_name, "testerDLast", "Correct lastname  received in the Response");
			Log4j.info("Successfully Retrieved the CUSTOMERDETAILS-4 Response");
			Reporter.log("Successfully Retrieved the CUSTOMERDETAILS-4 Response");
			Log4j.testEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 6)
	public void CustomerDetailsFound5() throws JsonMappingException, JsonProcessingException {
		Log4j.testStart();
		try {
			RestAssured.baseURI = url5;
			RequestSpecification httprequest = RestAssured.given();
			Response response = httprequest.get();

			int statusCode = response.getStatusCode();
			System.out.println("The Statuscode is" + " " + statusCode);
			Assert.assertEquals(statusCode, 200, "Correct status code returned");

			String contentType = response.header("Content-Type");
			System.out.println("Content-Type is : " + contentType);

			String serverType = response.header("Date");
			System.out.println("DATE is : " + serverType);

			String acceptLanguage = response.header("Transfer-Encoding");
			System.out.println("Transfer-Encoding is : " + acceptLanguage);

			ResponseBody<?> body = response.getBody();
			String bodyAsString = body.asString();
			System.out.println(bodyAsString);

			String json = bodyAsString;
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(json);

			String status = jsonNode.findValue("status").asText();
			System.out.println("The Status is" + "  " + status);
			Assert.assertEquals(status, "success", "Correct status  received in the Response");

			String code = jsonNode.findValue("code").asText();
			System.out.println("The Status is" + "  " + code);
			Assert.assertEquals(code, "0", "Correct code  received in the Response");

			String customerID = jsonNode.findValue("customerID").asText();
			System.out.println("The customerID is" + "  " + customerID);
			Assert.assertEquals(customerID, "5555", "Correct customerID  received in the Response");

			String email = jsonNode.findValue("email").asText();
			System.out.println("The emailId is" + "  " + email);
			Assert.assertEquals(email, "testerE@abc.com", "Correct email received in the Response");

			String first_name = jsonNode.findValue("first_name").asText();
			System.out.println("The first_name is" + "  " + first_name);
			Assert.assertEquals(first_name, "testerEFirst", "Correct firstname  received in the Response");

			String last_name = jsonNode.findValue("last_name").asText();
			System.out.println("The last_name is" + "  " + last_name);
			Assert.assertEquals(last_name, "testerELast", "Correct lastname  received in the Response");
			Log4j.info("Successfully Retrieved the CUSTOMERDETAILS-5 Response");
			Reporter.log("Successfully Retrieved the CUSTOMERDETAILS-5 Response");
			Log4j.testEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 7)
	public void CustomerDetailsFound6() throws JsonMappingException, JsonProcessingException {
		Log4j.testStart();
		try {
			RestAssured.baseURI = url6;
			RequestSpecification httprequest = RestAssured.given();
			Response response = httprequest.get();

			int statusCode = response.getStatusCode();
			System.out.println("The Statuscode is" + " " + statusCode);
			Assert.assertEquals(statusCode, 200, "Correct status code returned");

			String contentType = response.header("Content-Type");
			System.out.println("Content-Type is : " + contentType);

			String serverType = response.header("Date");
			System.out.println("DATE is : " + serverType);

			String acceptLanguage = response.header("Transfer-Encoding");
			System.out.println("Transfer-Encoding is : " + acceptLanguage);

			ResponseBody<?> body = response.getBody();
			String bodyAsString = body.asString();
			System.out.println(bodyAsString);

			String json = bodyAsString;
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(json);

			String status = jsonNode.findValue("status").asText();
			System.out.println("The Status is" + "  " + status);
			Assert.assertEquals(status, "success", "Correct status  received in the Response");

			String code = jsonNode.findValue("code").asText();
			System.out.println("The Status is" + "  " + code);
			Assert.assertEquals(code, "0", "Correct code  received in the Response");

			String customerID = jsonNode.findValue("customerID").asText();
			System.out.println("The customerID is" + "  " + customerID);
			Assert.assertEquals(customerID, "6666", "Correct customerID  received in the Response");

			String email = jsonNode.findValue("email").asText();
			System.out.println("The emailId is" + "  " + email);
			Assert.assertEquals(email, "testerF@abc.com", "Correct email received in the Response");

			String first_name = jsonNode.findValue("first_name").asText();
			System.out.println("The first_name is" + "  " + first_name);
			Assert.assertEquals(first_name, "testerFFirst", "Correct firstname  received in the Response");

			String last_name = jsonNode.findValue("last_name").asText();
			System.out.println("The last_name is" + "  " + last_name);
			Assert.assertEquals(last_name, "testerFLast", "Correct lastname  received in the Response");
			Log4j.info("Successfully Retrieved the CUSTOMERDETAILS-6 Response");
			Reporter.log("Successfully Retrieved the CUSTOMERDETAILS-6 Response");
			Log4j.testEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 8)
	public void CustomerDetailsNotFound() throws JsonMappingException, JsonProcessingException {
		Log4j.testStart();
		try {
			RestAssured.baseURI = url7;
			RequestSpecification httprequest = RestAssured.given();
			Response response = httprequest.get();

			int statusCode = response.getStatusCode();
			System.out.println("The Statuscode is" + " " + statusCode);
			Assert.assertEquals(statusCode, 404, "404 Not Found");

			String contentType = response.header("Content-Type");
			System.out.println("Content-Type is : " + contentType);

			String serverType = response.header("Date");
			System.out.println("DATE is : " + serverType);

			String acceptLanguage = response.header("Transfer-Encoding");
			System.out.println("Transfer-Encoding is : " + acceptLanguage);

			ResponseBody<?> body = response.getBody();
			String bodyAsString = body.asString();
			System.out.println(bodyAsString);

			String json = bodyAsString;
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(json);

			String status = jsonNode.findValue("status").asText();
			System.out.println("The Status is" + "  " + status);
			Assert.assertEquals(status, "fail", "Correct status  received in the Response");

			String code = jsonNode.findValue("code").asText();
			System.out.println("The code is" + "  " + code);
			Assert.assertEquals(code, "1", "Correct code  received in the Response");

			String message = jsonNode.findValue("message").asText();
			System.out.println("The message is" + "  " + code);
			Assert.assertEquals(message, "Customer details not found.", "Details Not Found");
			Log4j.info("Successfully checked the ERROR Scenario CustomerDetailsNotFound");
			Reporter.log("Successfully checked the ERROR Scenario CustomerDetailsNotFound");
			Log4j.testEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
