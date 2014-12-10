@foo
Feature: Register

Scenario:creating a new account
  #Try to create a new account
    When The user types in a valid 'nickname'
    And The user types in a valid 'email'
    And The user types in a valid 'password'
    And The user repeats a valid 'repeat'
    And The user accepts our 'termsOfUse'
    And The user hits the submit button
    Then A new account gets created
    And All the data is stored in our database


  Scenario: Guest types in the email incorrectly
    #Try if Guest types in the email incorrectly
    When The user types in his valid nickname
    And The user types in an invalid e-mail-address
    And The user types in his valid password
    And The user repeats his valid password
    And The user accepts our terms of use
    And The user hits the submit button
    Then An error message that tells the user that the email is invalid gets displayed
    And Nothing is stored in our database
    And No account gets created

    Scenario: Guest repeats the password incorrectly
      #Try Guest repeats the password incorrectly
      When The user types in his valid nickname
      And The user types in an valid e-mail-address
      And The user types in his valid password
      And The user doesn’t repeat the password correctly
      And The user accepts our terms of use
      And The user hits the submit button
      Then An error message gets displayed to the user that his repeated password is invalid
      And Nothing is stored in our database
      And No account gets created


  Scenario: The logged in user changes his password correctly
      #
      Given The logged in user clicks on the button “Edit” in his profile
      And his email-address gets displayed

      When The user types in his valid old password
      And The user types in his valid new password
      And The user repeats his valid new password
      And The user hits the submit button


