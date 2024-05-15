package StepDefinition;

import Framework.WrapperResponseGetterSetter;
import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Year;
import java.util.*;
import java.util.Map;
import org.testng.asserts.SoftAssert;


import static io.restassured.RestAssured.given;

public class MyStepdefs {
    static String baseUri = "https://api.restful-api.dev/objects";
     public static Logger LOGGER = LoggerFactory.getLogger(MyStepdefs.class);
    SoftAssert softAssert = new SoftAssert();

    @Given("^Hit the api (.+) with a (.+) to add an object with the required (.+) and size (.+) and (.+)")
    public void hitTheApiApi_nameWithANameToAddAnObjectWithTheRequiredData(String api_name, String objectName, String price, String size, String data) {
        LOGGER.info("Starting validation for " + api_name + "to add the object name " + objectName);
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", objectName);
        JSONObject obj = new JSONObject(data);
        payload.put("data", obj);
        if(objectName!=null){
            Gson gson = new Gson();
            String jsonPayload = gson.toJson(payload);
            RestAssured.baseURI= baseUri;
            Response res = given().contentType(ContentType.JSON).body(jsonPayload)
                    .post(baseUri);
            Assert.assertTrue(res.getStatusCode()==200,"The status code of the API should be 200");
            JSONObject respObject = new JSONObject(res.getBody().asString());
            WrapperResponseGetterSetter.setsTestData("id",respObject.get("id"));
        }
    }

    @Then("^validate the api (.+) contains the already added data (.+)")
    public void validateTheApiApi_nameContainsTheAlreadyAddedDataName(String api_name, String objectName) {
        RestAssured.baseURI= baseUri;
        Map<String,String> queryparam = new HashMap<>();
        queryparam.put("id",WrapperResponseGetterSetter.getsTestData("id").toString());
        Response res = given().queryParams(queryparam)
                .get();
        Assert.assertTrue(res.getStatusCode()==200,"The status code of the API should be 200");
        JSONArray respArray = new JSONArray(res.getBody().asString());
//        for(Object obj : respArray){
//            System.out.println(obj);
//        }
        Assert.assertTrue(respArray.length()>0,"The array should not be empty");
        LOGGER.info("validation successful. Entry with id " + WrapperResponseGetterSetter.getsTestData("id").toString() + " successfully added");

    }

    @Then("^delete the object with the id")
    public void deleteTheObjectWithTheId() {
        RestAssured.baseURI= baseUri + "/" + WrapperResponseGetterSetter.getsTestData("id");
        Response res = given().delete();
        Assert.assertTrue(res.getStatusCode()==200,"The status code of the API should be 200");
    }

    @Then("^Validate the (.+) should not have the ID present")
    public void validateTheApi_name_getShouldNotHaveTheIDPresent(String api_name) {
        LOGGER.info("Starting validation for " + api_name + "to check the deleted object id");
        Map<String,String> queryparam = new HashMap<>();
        queryparam.put("id",WrapperResponseGetterSetter.getsTestData("id").toString());
        Response res = given().queryParams(queryparam)
                .get();
        Assert.assertTrue(res.getStatusCode()==404,"The status code of the API should be 200");
        LOGGER.info("The object has been successfully deleted");
    }
}