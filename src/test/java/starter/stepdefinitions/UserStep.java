package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.user.Get;
import starter.user.Post;

public class UserStep {

    @Steps
    Get get;

    @Steps
    Post post;

    @Given("I set an endpoint for GET detail user")
    public void iSetAnEndpointForGETDetailUser() {
        get.setAnEndpointForGet();
    }

    @When("I request GET detail user")
    public void iRequestGETDetailUser() {
        get.requestGetDetailUser();
    }

    @Then("I validate the status code is 200")
    public void iValidateTheStatusCodeIs200() {
        get.validateStatusCode(200);
    }

    @And("validate the data detail")
    public void validateTheDataDetail() {
        get.validateDataDetail();
    }

    @Given("I set an endpoint for POST new user")
    public void iSetAnEndpointForPOSTNewUser() {
        post.setPostEndpoint();
    }

    @When("I request POST detail user")
    public void iRequestPOSTDetailUser() {
        post.requestPostDetailUser();
    }

    @Then("I validate the status code is 201")
    public void iValidateTheStatusCodeIs201() {
        get.validateStatusCode(201);
    }

    @And("validate the data detail after create user")
    public void validateTheDataDetailAfterCreateUser() {
        post.validateDataDetail();
    }
}
