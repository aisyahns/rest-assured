package starter.user;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;

public class PostGenerateToken {

    private String username;
    private static String base_url = "https://be-qa.alta.id/api";

    @Step("I set an endpoint for POST generate token")
    public String setEndpointForGenerate(){
        return base_url + "/orders";
    }

    @Step("Build request data")
    public List<JSONObject> bodyData(){
        List<JSONObject> body = new ArrayList<>();

        JSONObject data = new JSONObject();
        data.put("product_id", 428);
        data.put("quantity", 2);

        body.add(data);
        return body;
    }

    @Step("Build request data")
    public JSONObject bodyDataProduct(){
        JSONObject body = new JSONObject();
        List<Integer> data = new ArrayList<>();

        body.put("name", "Tensi darah");
        body.put("price", 9800);

        data.add(3);
        data.add(6);

        body.put("categories", data);
        return body;
    }


    @Step("I request POST generate token")
    public void requestPostGenerateToken() throws Exception{
        String token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//token.json"), StandardCharsets.UTF_8);

        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(bodyData())
                .when().post(setEndpointForGenerate());
    }

    @Step("validate the data detail after generate token")
    public void validateDataDetailGenerateToken(){
        then().body("result", equalTo("User authorized successfully."));
    }

    @Step("get token for other request")
    public String getToken(){
        Response response = SerenityRest.lastResponse();
        String token = response.body().path("token");
        System.out.println(token);
        try (FileWriter file = new FileWriter("src//test//resources//filejson//token.json")) {
            file.write(token);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }
}
