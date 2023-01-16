#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Batch
  I want to use this template for my feature file

  
  Scenario: Get All Batches
   Given A service with BaseUrl with endpoint
   When GET Request is made
   Then Validate Status Code
   And Valiadte Status is ok
   And Validate Content - Type
   And Validate Response time
   
     
  Scenario Outline: Get Batch by Batch Id
   
   Given A service with BaseUrl with endpoint
   When GET Request is made with "<BatchId>"
   Then Validate Status Code
    And Valiadte Status is ok
    And Validate Content - Type
    And Validate Response time
  
   Examples:
             |BatchId|
             |2214|
   
 Scenario Outline: Get Batch by Batch Name
 
   Given A service with BaseUrl with endpoint
   When GET Requests is made with "<BatchName>"
   Then Validate Status Code
   And Valiadte Status is ok
   And Validate Content - Type
   And Validate Response time
   
     Examples:
             |BatchName|
             |wireless| 
 