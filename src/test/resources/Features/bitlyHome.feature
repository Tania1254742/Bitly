
Feature: Validating Bitly Shorten Link Functionality
  
  @regressionTest
  Scenario: POSITIVE SCENARIO1 - User has ability to Shorten the Link without logged in
    Given User go to bitly.com URL
    When User is in the page
    Then User enter the actual URL in Shorten your link box
    And  click Shorten button
    Then User can see the shorten link created
    And validate the link created


  Scenario: POSITIVE SCENARIO2 - User has ability to create differnet Shorten link for same Actual URL
    Given User go to bitly.com URL
    When User is in the page
    Then User enter the same actual URL in Shorten your link box
    And  click Shorten button
    Then User can see the second shorten link created
    And validate the link created should not equal to previous created link
  

  Scenario: NEGATIVE SCENARIO1 - User has ability to see the Invalid Url Error message
    Given User go to bitly.com URL
    When User is in the page
    Then User enter the invalid URL in Shorten your link box
    And  click Shorten button
    Then Validate the user can see the invalid Url error message
    
    

    
    
    
