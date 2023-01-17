Feature: Tests for LMSAPI
  Description: The purpose of this is to create feature and step definitions for testing API
  
  LMS API URL: https://lms-program-rest-service.herokuapp.com

  Scenario: Get All Programs
    Given A service with BaseUrl/endpoint
    When GET Request is made
    Then Validate status code 200
    And validate schema
    And Validate resopnse Time 
    And validate Status Line

  Scenario Outline: Get Program by Id
    Given A service BaseUrl with endpoint
    When i made GET Request with <ProgramID>
    Then Validate Corresponding response status code 200
 Examples: 
      | ProgramID |
      |      6717 |
       |     1567 |