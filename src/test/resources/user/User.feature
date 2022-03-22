Feature: User
  As an admin
  I want to get detail user
  So that I can update the user data

  Scenario: GET - As an admin I have to be able to get detail user
    Given I set an endpoint for GET detail user
    When I request GET detail user
    Then I validate the status code is 200
    And validate the data detail

  Scenario: POST - As an admin I have to be able to create new user
    Given I set an endpoint for POST new user
    When I request POST detail user
    Then I validate the status code is 201
    And validate the data detail after create user