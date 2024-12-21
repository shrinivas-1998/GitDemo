
@tag
Feature: Error validation 
  Check whether the gievn username and password is correct or not

  @ErrorValidation
  Scenario Outline: checking username and password 
    Given i landed on Ecommerce Page
    When Logged in with username <username> and password <password> 
    Then "Incorrect email or password." message is displayed 
    
   Examples: 
      |   username    |    password  |
      | sb@gamail.com | d!DAKFYAjzRN |
    
