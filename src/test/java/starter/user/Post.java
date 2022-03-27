package starter.user;

import Utils.General;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;

public class Post {

    General general = new General();
    String username;

    private static String base_url = "https://demoqa.com/Account/v1/";

    @Step("I set an endpoint for POST new user")
    public String setPostEndpoint(){
        return base_url + "User";
    }

    @Step("I set an endpoint for POST new user")
    public String getUsername(){
        return this.username;
    }

    @Step("I request POST detail user")
    public void requestPostDetailUser(){
        JSONObject requestData = new JSONObject();
        this.username = general.randomUsername();
        requestData.put("userName", this.username);
        requestData.put("password", "Password1234!");

        SerenityRest.given().header("Content-Type", "application/json").body(requestData.toJSONString());
        SerenityRest.when().post(setPostEndpoint());

        try (FileWriter file = new FileWriter("src//test//resources//filejson//username.json")) {
            file.write(username);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("validate the data detail after create user")
    public void validateDataDetail(){
        SerenityRest.then().body("username", equalTo(this.username));
//        SerenityRest.then().body(equalTo(true));
    }

    @Step("Get userId from the response")
    public String getUserId(){
        Response response = SerenityRest.lastResponse();
        String userId = response.body().path("userID");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//userId.json")) {
            file.write(userId);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(userId);
        return userId;
    }

    @Step("I request POST detail user with invalid password")
    public void requestPostInvalid(){
        JSONObject requestData = new JSONObject();
        requestData.put("userName", "aisyahns980");
        requestData.put("password", "password");

        given().header("Content-Type", "application/json")
                .body(requestData.toJSONString())
                .when().post(setPostEndpoint());
    }

    @Step("validate the data detail after failed create user")
    public void validateDataDetailFailed(){
        then().body("code", equalTo("1300"));
    }
}
