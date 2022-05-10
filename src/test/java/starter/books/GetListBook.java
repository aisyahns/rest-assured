package starter.books;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static net.serenitybdd.rest.RestRequests.given;
import static net.serenitybdd.rest.SerenityRest.then;

import static org.hamcrest.Matchers.equalTo;
public class GetListBook {

    String base_url = "https://be-qa.alta.id/api/";

    public String getEndpoint(){
        return base_url + "products/{idProduct}";
    }

    public void requestGetListBook() throws Exception{
        String idProduct = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//idProduct.json"), StandardCharsets.UTF_8);
        given()
                .pathParam("idProduct", idProduct).when().get(getEndpoint());
    }

    public void verifyStatusCode(int code){
        then().statusCode(equalTo(code));
    }

    public void getIsbn() {
        Response response = SerenityRest.lastResponse();
        Integer isbn = response.body().path("books[0].isbn");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//isbn.json")) {
            file.write(isbn);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
