@foo
Feature: Create character

  Scenario: Create Bob as character
    Given there are several character for choosing
    When you click on 'Bob' as character
    When you click on the button 'Start Game' on the left side
    Then the game starts

  Scenario: Create Alice as character
    Given there are several character for choosing
    When you click on 'Alice' as character
    When you click on the button 'Start Game' on the left side
    Then the game starts

  Scenario: User does not chosen
    Given there are several character for choosing
    When you not click on any character
    When you click on the button 'Start Game' on the left side
    Then the game does not  start