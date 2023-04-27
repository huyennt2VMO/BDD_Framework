Feature: Login to sauce
  As a user, I want to be able to log into the sauce system

  Scenario: Successful login
    Given Login the page
    When Enter username and password
    And Click the Login button
    Then Verify title