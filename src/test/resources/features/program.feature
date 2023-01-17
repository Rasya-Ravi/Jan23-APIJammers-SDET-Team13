<<<<<<< HEAD
Feature: Program
  I want to use this template for my feature file

@CreateAndUpdateProgram
Scenario Outline: Creating a new program(POST) and updating the ProgramName using PUT method
Given A service with "<BaseUrl>"
When POST request is made with  "<programName>","<programDescription>","<programStatus>" and "<endpoint>"
Then fetch the programId from the POST response 
And validate the POST response
And Validate Response time
And update the program by programId using PUT method

Examples:
  |BaseUrl										|progId   |endpoint	   |programName						 |programDescription|programStatus|
  |https://lms-backend-service.herokuapp.com/lms|programId|/saveprogram|Jan23-APIJammers-SDET-13_|API Testing		|Active		  |
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
#@tag
#Feature: Title of your feature
#  I want to use this template for my feature file

  @tag1
  Scenario Outline: Create Program and Delete Program
  Given A service with Program BaseUrl 
  When POST request is made with Program "<programDescription>","<programStatus>"
  Then Validate random programId created
  And Validate prog status code
  And Validate prog Response time
  And Validate Content-Type
  When Delete Program by Id
  Then Validate corresponding ProgramId deleted
  
  Examples:
   | programDescription |programStatus|
   | Learn PostgreSqlsection |Active|
 
=======
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
  And Validate Content-Type
  When Delete Program by Id
  Then Validate corresponding ProgramId deleted
  
  Examples:
   | programDescription |programStatus|
   | Learn PostgreSqlsection |Active|
  
>>>>>>> branch 'branch_Saritha' of https://github.com/Rasya-Ravi/Jan23-APIJammers-SDET-Team13.git
