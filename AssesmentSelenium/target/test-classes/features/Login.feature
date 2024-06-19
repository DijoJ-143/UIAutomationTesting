@Login
Feature: Login
  As a user i want to login to getKetch

  @negative
  Scenario Outline: Login with InvalidData
    Given User already on home page
    When User input "<phonenumber>" as phonenumber
    Then User get "<error>" as error message

    Examples: Data
      | phonenumber | error                            |
      | asdfgh      | Please enter valid mobile number |
      |         123 | Please enter valid mobile number |
