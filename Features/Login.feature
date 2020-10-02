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
Feature: Validate AutomationPractice Login functionality

  #Scenario Outline: User should be able to login with valid user id and valid password
  #Given User Launch "<browser>" browser
  #When User opens URL "https://admin-demo.nopcommerce.com/admin"
  #And User enters valid user id "<email>" and valid password "<password>"
  #When User clicks login button
  #Then Page title should be "Dashboard / nopCommerce administration"
  #When User click on logout button
  #Then Page title should be "Your store. Login"
  #And close browser
  #
  #Examples:
  #|	browser	|	email	|	password	|
  #|	firefox	|	admin@yourstore.com	|	admin	|
  #|	edge	|	admin@mystore.com	|	admin	|
  #|firefox	|	admin@yourstore.com	|	notadmin	|
  #|	edge	|	admin@mystore.com	|	notadmin	|
  @Sanity @Regression
  Scenario: User should be able to login with valid user id and valid password
    Given User Launch browser
    When User opens URL "https://admin-demo.nopcommerce.com/admin"
    And User enters valid user id "admin@yourstore.com" and valid password "admin"
    When User clicks login button
    Then Page title should be "Dashboard / nopCommerce administration"
    When User click on logout button
    Then Page title should be "Your store. Login"
    And close browser
