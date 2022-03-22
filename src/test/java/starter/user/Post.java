package starter.user;

import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;

public class Post {

    private static String base_url = "https://reqres.in/api/";

    @Step("I set an endpoint for POST new user")
    public String setPostEndpoint(){
        return base_url + "users";
    }

    @Step("I request POST detail user")
    public void requestPostDetailUser(){
        JSONObject requestData = new JSONObject();
        requestData.put("name", "morpheus");
        requestData.put("job", "leader");

        given()
                .header("Content-Type", "application/json")
                .body(requestData.toJSONString())
                .when().post(setPostEndpoint());
    }

    @Step("validate the data detail after create user")
    public void validateDataDetail(){
        then().body("name", equalTo("morpheus"));
        then().body("job", equalTo("leader"));
    }
}
