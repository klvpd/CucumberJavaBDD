#Author: chandu.kalavapudi@gmail.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Customers

  Background: Common steps for all the scenarios
    Given User Launch browser
    When User opens URL "https://admin-demo.nopcommerce.com/admin"
    And User enters valid user id "admin@yourstore.com" and valid password "admin"
    When User clicks login button
    Then User can view Dashboard
@Sanity
  Scenario: Add a new customer
    When User click on customers menu
    And click on customer menu item
    And click on Add new button
    Then User can view Add new customer page
    When User enters customer info
    And click on Save button
    Then User can view confirmation message "The new customer has been added successfully."
    And close browser
@Regression
  Scenario: Search customer using email id
    When User click on customers menu
    And click on customer menu item
    When Enter customer email "brenda_lindgren@nopCommerce.com"
    And Click on search button
    Then User should find customer details with identifier "brenda_lindgren@nopCommerce.com" in search table
    And close browser

@Data-driven
  Scenario Outline: Search customer
    When User click on customers menu
    And click on customer menu item
    When Enter customer email "<email>"
    And Enter firstname "<firstname>"
    And Enter lastname "<lastname>"
    And Click on search button
    Then User should find customer details with identifier "<email>" or "<firstname>" or "<lastname>" in search table
    And close browser

    Examples: 
      | email                           | firstname | lastname |
      | brenda_lindgren@nopCommerce.com |           |          |
      |                                 | Victoria  |          |
      |                                 |           | pan      |
      | brenda_lindgren@nopCommerce.com | Victoria  | pan      |
