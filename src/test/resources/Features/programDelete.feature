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
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario Outline: Create Program and Delete Program
    Given A service with Program BaseUrl
    When POST request is made with Program "<programDescription>","<programStatus>"
    Then Validate random programId created
    And Validate prog status code
    And Validate prog Response time
    And Validate program Content-Type
    When Delete Program by Id
    Then Validate corresponding ProgramId programs deleted
    When Delete Program by program Name
    Then Validate corresponding programs should be deleted

    Examples: 
      | programDescription      | programStatus |
      | Learn PostgreSqlsection | Active        |
    
   
