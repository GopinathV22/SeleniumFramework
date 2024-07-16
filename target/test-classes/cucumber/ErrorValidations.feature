
@tag
Feature: Error Validations

  @ErrorValidations
  Scenario Outline: Login error validations
    Given I landed on Ecommerce page
    When  Logged in to the website with Username <username> and Password <password>
    Then  Verify "Incorrect email or password." error message is displayed
    
    Examples:
      | username            | password     |
      | gopinathv@gmail.com | Gopiecom@13  |
    
