Feature: Tests for LMSAPI
  Description: The purpose of this is to create feature and step definitions for testing API
  
  LMS API URL: https://lms-program-rest-service.herokuapp.com
Background: Batch should be there in database already
  Scenario: Delete Batch by BatchId
    Given  A service with BaseUrl with endpoint
    When DELETE request is made <BatchId>
    Then Validate Status Code
    And Validate Response time
    And Valiadte Status is ok
 Examples: 
      |  BatchId |
      |      1895 |