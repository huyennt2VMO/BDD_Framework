Feature: Login
  As a user
  I want to login
  So that I can access my account

  Scenario Outline : User can login with valid credentials
    Given I am on the login page
    When User logged in with email "<userName>" and password "<password>"
    Then I should see the dashboard page
    Examples:
      | userName | password |
      | secret_sauce | standard_user |
