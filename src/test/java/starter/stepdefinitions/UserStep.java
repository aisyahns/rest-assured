package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.user.Get;
import starter.user.Post;
import starter.user.PostGenerateToken;

public class UserStep {

    public String userId, token, username;

    @Steps
    Get get = new Get();

    @Steps
    Post post = new Post();

    @Steps
    PostGenerateToken postGenerateToken = new PostGenerateToken();

    @Given("I set an endpoint for GET detail user")
    public void iSetAnEndpointForGETDetailUser() {
        get.setAnEndpointForGet(this.userId);
    }

    @When("I request GET detail user")
    public void iRequestGETDetailUser() throws Exception{
        get.requestGetDetailUser();
    }

    @And("validate the data detail")
    public void validateTheDataDetail() {
        get.validateDataDetail(this.userId);
    }

    @Given("I set an endpoint for POST new user")
    public void iSetAnEndpointForPOSTNewUser() {
        post.setPostEndpoint();
    }

    @When("I request POST detail user")
    public void iRequestPOSTDetailUser() {
        post.requestPostDetailUser();
        this.username = post.getUsername();
    }

    @And("validate the data detail after create user")
    public void validateTheDataDetailAfterCreateUser() {
        post.validateDataDetail();
    }

    @And("get userId for other request")
    public void getUserIdForOtherRequest() {
        this.userId = post.getUserId();
    }

    @Given("I set an endpoint for POST generate token")
    public void iSetAnEndpointForPOSTGenerateToken() {
        postGenerateToken.setEndpointForGenerate();
    }

    @When("I request POST generate token")
    public void iRequestPOSTGenerateToken() throws Exception{
        postGenerateToken.requestPostGenerateToken();
    }

    @And("validate the data detail after generate token")
    public void validateTheDataDetailAfterGenerateToken() {
        postGenerateToken.validateDataDetailGenerateToken();
    }

    @And("get token for other request")
    public void getTokenForOtherRequest() {
        this.token = postGenerateToken.getToken();
    }

    @When("I request POST detail user with invalid password")
    public void iRequestPOSTDetailUserWithInvalidPassword() {
        post.requestPostInvalid();
    }

    @Then("I validate the status code is {int}")
    public void iValidateTheStatusCodeIs(int arg0) {
        get.validateStatusCode(arg0);
    }

    @And("validate the data detail after failed create user")
    public void validateTheDataDetailAfterFailedCreateUser() {
        post.validateDataDetailFailed();
    }
}
