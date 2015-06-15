# encoding: utf-8
Feature:	Use Case 1 - Register

  Scenario Outline: creating a new account successfully
   #Try to create a new account
    Given 	The user is on the register page

    When 	The user types in a valid nickname <nickname>
    And 	The user types in a valid email <email>
    And 	The user types in a valid password <password>
    And 	The user repeats a valid password <password>
    And 	The user accepts our Terms Of Use
    And 	The user hits the submit button

    Then 	The message is displayed: Successfully registered! Now try to log-in.

  Examples:
    | nickname		| email			    | password	|
    | DefaultUser	| default@gmx.de	| 123456	|


  Feature: Access Statistics - Use Case

  Scenario: Access statistics successfully

    Given   The user is on the register page

    When  User clicks on statistics button

    Then  the user gets to the statistics page
    And   the best character <bestchar> is displayed

  Examples:
  | bestchar      |
  | Bob           |
