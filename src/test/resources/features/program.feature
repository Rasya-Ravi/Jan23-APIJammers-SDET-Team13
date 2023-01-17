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