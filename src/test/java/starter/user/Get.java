package starter.user;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;

public class Get {

    protected static String base_url = "https://reqres.in/api/";

    @Step("I set an endpoint for GET detail user")
    public String setAnEndpointForGet(){
        return base_url + "users/2";
    }

    @Step("I request GET detail user")
    public void requestGetDetailUser(){
        SerenityRest.given()
                .when()
                .get(setAnEndpointForGet());
    }

    @Step("I validate the status code is {int}")
    public void validateStatusCode(int statusCode){
//        restAssuredThat(response -> response.statusCode(statusCode));
        SerenityRest.then().statusCode(statusCode);
    }

    @Step("validate the data detail")
    public void validateDataDetail(){
//        restAssuredThat(response -> response.body("data.id", equalTo(2)));
//        restAssuredThat(response -> response.body("data.email", equalTo("janet.weaver@reqres.in")));
        SerenityRest.then().body("data.id", equalTo(2));
        SerenityRest.then().body("data.email", equalTo("janet.weaver@reqres.in"));
    }
}
