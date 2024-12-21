
@tag
Feature: Purchase the order from ecommerce website
  I want to select the products from an ecommerce website.
  
  Background:
  Given i landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting order
    Given Logged in with username <username> and password <password>  
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      |   username    |    password     | productName |
      | sb@gamail.com | d!DAKFYAjzRNc9J | ZARA COAT 3 |
    
