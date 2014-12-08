@foo
Feature: Edit Profile


 Scenario: The logged in user changes his password incorrectly
    #
    Given The logged in user clicks on the button “Edit” in his profile
    And his email-address gets displayed
    When The user types in his invalid old password
    And The user types in his valid new password
    And The user repeats his valid new password
    And The user hits the submit button
    Then The user gets an error message that his old password is incorrect
    And Nothing gets changed in our database
    Then The email in the database is manipulated, even if it hasn’t been changed, for this user
    And The password in the database is manipulated for this user
    And The user gets a confirmation message on his browser window displayed


    Scenario: The logged in user changes his email without a new password correctly
      #
      Given The logged in user clicks on the button “Edit” in his profile
      And his email-address gets displayed
      When The user types in his valid new email-address
      And The user types in valid his old password
      And The user hits the submit button
      Then The email in the database is manipulated, even if it has not been changed, for this user
      And The user gets a confirmation message on his browser window displayed



    Scenario: The logged in user changes his email without a new password incorrectly
      #
      Given The logged in user clicks on the button “Edit” in his profile
      And his email-address gets displayed

      When The user types in his invalid new email-address
      And The user types in his valid old password
      And The user hits the submit button

      Then The user gets an error message that his new email-address is incorrect
      And Nothing gets changed in our database