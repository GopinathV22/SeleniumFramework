@tag
Feature: Purchase the order from the Ecommerce website
  
  Background:
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test for Submitting the order
    Given Logged in to the website with Username <username> and Password <password>
    When  Adding the product <productName> to the cart 
    And   Checkout <productName> and submitting the order with <countryName>
    Then  Verify "THANKYOU FOR THE ORDER." message is displayed

    Examples: 
      | username            | password     | productName    | countryName |
      | gopinathv@gmail.com | Gopiecom@123 | ADIDAS ORIGINAL| India       |
      | shetty@gmail.com    | Iamking@000  | IPHONE 13 PRO  | India       |
