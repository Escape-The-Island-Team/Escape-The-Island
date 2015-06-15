# encoding: utf-8
Feature:	Use Case 3 - Create Character

  Scenario: The logged in user create his character Bob

    Given 	user is on the characterSelection page
    When 	user clicks on Bob as character <characterBob>
    And 	user clicks on the Start Game <buttonStart>

    Then 	the game starts on loadingGame page


  Scenario: The logged in user create his character Alice

    Given 	user is on the characterSelection page
    When 	user clicks on Alice as character <characterAlice>
    And 	user clicks on the Start Game <buttonStart>

    Then 	the game starts on loadingGame page

