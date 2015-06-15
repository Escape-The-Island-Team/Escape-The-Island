# encoding: utf-8
Feature:	Use Case 2 - Edit Profile

  Scenario Outline: The logged in user changes his password correctly
   #
    Given 	The user is logged in
    Given 	The user is on the profile page


    When 	The user types in a valid email <email>
    And 	The user types in a valid old password <passwordOld>
    And 	The user types in a valid new password <passwordNew>
    And 	The user repeats a valid new password <passwordNew>
    And 	The user hits the submit button

    Then 	The message is displayed: Your e-mail has been changed. Your password has been changed.

  Examples:
    | email			| passwordOld	| passwordNew	|
    | blub@gmx.de	| 123456		| 1234567		|


  Scenario Outline: The logged in user changes his password incorrectly
  #
    Given 	The user is on the profile page

    When 	The user types in a valid email <email>
    And 	The user types in a valid old password <passwordOld>
    And 	The user types in a valid new password <passwordNew>
    And 	The user repeats a invalid new password <repeatPassword>
    And 	The user hits the submit button

    Then 	The message is displayed: Your e-mail has been changed. To change your password, the new password, and the repeated one must be equal.

  Examples:
    | email			| passwordOld	| passwordNew	| repeatPassword	|
    | blub@gmx.de	| 1234567		| 123486		| 34444311			|