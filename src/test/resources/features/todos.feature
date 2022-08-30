@Todos
Feature: Todos features

  In order to remember the things I want to do, as a ToDo MVC user,
  I want to manage my todo list

   @smoke
   Scenario: As a user, I should be able to add single todo item
    Given I am on home page
    When I add "Send meeting request" to Todo list
    Then I should see Todo list contains
      |Send meeting request|
    And I should see Todo list summary as "1 item left"

   @regression
   Scenario: As a user, I should be able to add multiple todo items
    Given I am on home page
    When  I add below items to Todo list
      |Send meeting request|
      |Book a flight       |
    Then I should see Todo list contains
      |Send meeting request|
      |Book a flight       |
    And  I should see Todo list summary as "2 items left"
    When I click on active tab
    Then I should see Todo list contains
      |Send meeting request|
      |Book a flight       |
    When I click on completed tab
    Then I should see Todo list is empty
    
   @regression
   Scenario Outline: As a user, I should be able to view only completed items
    Given I am on home page
    When  I add below items to Todo list
      |Send meeting request|
      |Book a flight       |
    Then I should see Todo list contains
      |Send meeting request|
      |Book a flight       |
    When I complete below items from Todo list
      |Send meeting request|
    And  I filter the list to show "<Filter>" tasks
    Then I should see Todo list contains
      | <Item Displayed> |
      
    Examples:
      | Filter    | Item Displayed       |
      | Completed | Send meeting request |
      | Active    | Book a flight        |
      
   @regression
   Scenario: As a user, I should NOT be able to see item in active filter if it is completed
    Given I am on home page
    When  I add below items to Todo list
      |Send meeting request|
      |Book a flight       |
      |Review the PR       |
    Then I should see Todo list contains
      |Send meeting request|
      |Book a flight       |
      |Review the PR       |
    When I complete below items from Todo list
      |Send meeting request|
    And  I filter the list to show "Active" tasks
    Then I should NOT see Todo list contains "Send meeting request" in "Active" filter
    
   @regression
   Scenario: As a user, I should NOT be able to see item in completed filter if it is active
    Given I am on home page
    When  I add below items to Todo list
      |Send meeting request|
      |Book a flight       |
    Then I should see Todo list contains
      |Send meeting request|
      |Book a flight       |
    When I complete below items from Todo list
      |Send meeting request|
    And  I filter the list to show "Completed" tasks
    Then I should NOT see Todo list contains "Book a flight" in "Completed" filter
      
   @regression
   Scenario: As a user, I should be able to clear the completed item
    Given I am on home page
    When  I add below items to Todo list
      |Send meeting request|
      |Book a flight       |
    Then I should see Todo list contains
      |Send meeting request|
      |Book a flight       |
    When I complete below items from Todo list
      | Book a flight      |
    Then I should see Todo list summary as "1 item left"   
    And I should NOT see Todo list contains "Book a flight" in "Active" filter
    When I click on button Clear Completed
    Then I should NOT see Todo list contains "Book a flight" in "Completed" filter
    
   @regression
   Scenario: As a user, I should be able to clear all the items from Todo list
    Given I am on home page
    When  I add below items to Todo list
      |Send meeting request|
      |Book a flight       |
    Then I should see Todo list contains
      |Send meeting request|
      |Book a flight       |
    When I complete all the item from Todo list
    Then I should see Todo list summary as "0 items left"
    When I click on button Clear Completed
    Then I should NOT see Active, Completed and Clear Completed
   

    