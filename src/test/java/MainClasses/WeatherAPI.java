package MainClasses;

import Files.PayLoad;
import Files.Resources;
import TestRunner.MainClass;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class WeatherAPI extends MainClass {


    private Response response;
    private ValidatableResponse response_statusCode;
    RequestSpecification httpRequest = RestAssured.given();
    String responseBody;
    private String ID_value;
    private JsonPath jp = null;
    private int actual_StatusCode;


    @Given("^WeatherAPI URI$")
    public void weatherapiURI() {
        // RestAssured.baseURI = props.getProperty("BASE");
        RestAssured.baseURI = "http://api.openweathermap.org";
        System.out.println("BaseURI is loaded");
    }

    @When("^I post the rest call creating a new station$")
    public void iPostTheRestCallCreatingANewStation() throws Throwable {
        // getData();
        response = given().header("Content-Type", "application/json")
                .queryParam("appid", "a474909b9bd0b5205ac147da58080c0f")
                .body(PayLoad.getWeatherData("externid", "name")).when().post(Resources.getResource());

        actual_StatusCode = response.getStatusCode();
        System.out.println("Response statusCode1 is" + actual_StatusCode);

        System.out.println("response in pretty format : " + response.prettyPrint());


    }


    @Then("^the status code is \"([^\"]*)\"$")
    public void theStatusCodeIs(int expected_StatusCode) throws Throwable {
        actual_StatusCode = response.getStatusCode();
        System.out.println("Response statusCode1 is" + actual_StatusCode);
        Assert.assertEquals(actual_StatusCode /*actual value*/, expected_StatusCode /*expected value*/, "Correct status code returned");
    }

    @And("^I validate response$")
    public void iValidateResponse() {


        System.out.println("response in pretty format : " + response.prettyPrint());
        System.out.println("response in string format : " + response.asString());

        responseBody = response.asString();
        jp = new JsonPath(responseBody);
        System.out.println("responseBody is " + responseBody);
        ID_value = jp.getString("ID");
        System.out.println("id value is " + ID_value);

       /* System.out.println(response.getBody());
        System.out.println(response.getContentType());
        System.out.println(response.getHeader(ID));
        System.out.println(response.getHeader("updated_at"));
        System.out.println(response.getHeaders());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());*/


        // assertEquals(201, response.getStatusCode());
        //  assertThat(jp.get("name"), containsString("Tuni"));
    }

    @When("^I execute get call for weatherAPI to get single station details$")
    public void iExecuteGetCallForWeatherAPIToGetSingleStationDetails() throws Throwable {

        iPostTheRestCallCreatingANewStation();
        iExtractIDValueFromTheResponse();

        response = given().header("Content_Type", "application/json").
                queryParam("appid", "a474909b9bd0b5205ac147da58080c0f").when().get(Resources.getResource() + "/" + ID_value);

        System.out.println("response of get is " + response.prettyPrint());
    }


    @When("^I execute get call for weatherAPI to get all station details$")
    public void iExecuteGetCallForWeatherAPIToGetAllStationDetails() {
        response = given().header("Content_Type", "application/json").
                queryParam("appid", "a474909b9bd0b5205ac147da58080c0f").when().get(Resources.getResource());

        System.out.println("response of get is " + response);
    }

    @When("^I execute put call for weatherAPI to update single station details$")
    public void iExecutePutCallForWeatherAPIToUpdateSingleStationDetails() throws Throwable {
        iPostTheRestCallCreatingANewStation();
        iExtractIDValueFromTheResponse();

        response = given().header("Content-Type", "application/json")
                .queryParam("appid", "a474909b9bd0b5205ac147da58080c0f")
                .body(PayLoad.getWeatherData("Murthy100", "Tuni Urban Station")).when().put(Resources.getResource() + "/" + ID_value);

        System.out.println("response of get is " + response.prettyPrint());
    }

    @When("^I execute delete call for weatherAPI to delete single station details$")
    public void iExecuteDeleteCallForWeatherAPIToDeleteSingleStationDetails() throws Throwable {

        iPostTheRestCallCreatingANewStation();
        iExtractIDValueFromTheResponse();

        response = given().header("Content-Type", "application/json")
                .queryParam("appid", "a474909b9bd0b5205ac147da58080c0f")
                .body(PayLoad.getWeatherData("Murthy100", "Tuni Urban Station")).when().delete(Resources.getResource() + "/" + ID_value);

    }

    @Then("^I extract ID value from the response$")
    public void iExtractIDValueFromTheResponse() {
        responseBody = response.asString();
        jp = new JsonPath(responseBody);
        ID_value = jp.getString("ID");

        System.out.println("ID value from response is : " + ID_value);
    }

    @And("^I get all ID details of all Stations$")
    public void iGetAllIDDetailsOfAllStations() {

        String ids = response.jsonPath().getString("id");
        System.out.println("All IDs of this appid are" + ids);
    }


    @And("^I Create the station details using mutiple data and delete the same with the help of Station ID$")
    public void iCreateTheStationDetailsUsingMutipleDataAndDeleteTheSamewithTheHelpOfStationID(DataTable dt) {
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);

        for (int i = 0; i < list.size(); i++) {
            String exterid = list.get(i).get("externalid");
            System.out.println("external_id is " + exterid);
            String name = list.get(i).get("namestring");
            System.out.println("name is " + name);
            response = given().header("Content-Type", "application/json")
                    .queryParam("appid", "a474909b9bd0b5205ac147da58080c0f")
                    .body(PayLoad.getWeatherData(exterid, name)).when().post(Resources.getResource());
            System.out.println(response.asString());

            responseBody = response.asString();
            jp = new JsonPath(responseBody);
            ID_value = jp.getString("ID");

            System.out.println("ID value from response is : " + ID_value);

            response = given().header("Content-Type", "application/json")
                    .queryParam("appid", "a474909b9bd0b5205ac147da58080c0f")
                    .body(PayLoad.getWeatherData(exterid, name)).when().delete(Resources.getResource() + "/" + ID_value);

            responseBody = response.asString();
            System.out.println("Station details for the id Value " + ID_value + "  Is Successfully Deleted");


        }

    }
}

