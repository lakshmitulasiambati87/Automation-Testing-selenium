#Author: your.email@your.domain.com
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


Feature: Make Payment


  @functional
  Scenario: Validate partial makepayment for an active Plan with Single Loan
Given User navigated to URL  "https://devicefinance-ha-qlab02.px-npe1103.pks.t-mobile.com/devicefinance-ui/lookup_installment_plan.htm"
When Enter BAN
And Enter userid
And Enter channel
And Enter level
And click on lookup button
And click on planid
And click on Makepayment
And enter partial amount
And enter Email for Notifications
And enter verify Email Adress
And click on Submit
Then Page should display "Payment has been made successfully" 
And Status should be Active-Additional-Payment-Applied 
And Current Remaining Balance should be updated 
And New Remaining Balance should be updated
And Page Details should be match with DB tables.

  @functional
  Scenario: Validate partial makepayment for an active Plan with Single Loan
Given User navigated to URL  "https://devicefinance-ha-qlab02.px-npe1103.pks.t-mobile.com/devicefinance-ui/lookup_installment_plan.htm"
When Enter BAN
And Enter userid
And Enter channel
And Enter level
And click on lookup button
And click on planid
And click on Makepayment
And enter partial amount
And enter Email for Notifications
And enter verify Email Adress
And click on Submit
Then Page should display "Payment has been made successfully" 
And Status should be Active-Additional-Payment-Applied 
And Current Remaining Balance should be updated 
And New Remaining Balance should be updated
And Page Details should be match with DB tables.

@functional
Scenario: Validate payoff loan for an active Plan with Single Loan
Given User navigated to URL  "https://devicefinance-ha-qlab02.px-npe1103.pks.t-mobile.com/devicefinance-ui/lookup_installment_plan.htm"
When Enter BAN
And Enter userid
And Enter channel
And Enter level
And click on lookup button
And click on planid
And click on Makepayment
And enter remaining amount
And enter Email for Notifications
And enter verify Email Adress
And click on Submit
Then Page should display "Payment has been made successfully" 
And Status should be Active-Additional-Payment-Applied 
#And Current Remaining Balance should be updated 
#And New Remaining Balance should be updated
And Page Details should be match with DB tables.

@functional
Scenario: Validate makepayment for an active Plan with Single Loan paying amount equals to Monthly charge Amount
Given User navigated to URL  "https://devicefinance-ha-qlab02.px-npe1103.pks.t-mobile.com/devicefinance-ui/lookup_installment_plan.htm"
When Enter BAN
And Enter userid
And Enter channel
And Enter level
And click on lookup button
And click on planid
And fetch the monthly charge amount
And click on Makepayment
And enter monthly charge amount
And enter Email for Notifications
And enter verify Email Adress
And click on Submit
Then Page should display "Payment has been made successfully" 
And Status should be Active-Additional-Payment-Applied 
And Current Remaining Balance should be updated 
And New Remaining Balance should be updated 
And Current Remaining Number of Installments should be reduced by one 
And Page Details should be match with DB tables.
And agreement status should be Active-Additional-Payment-Applied
  
  @functional
Scenario: Validate makepayment for an active Plan having mutiline Loan Payoff single Loan
Given User navigated to URL  "https://devicefinance-ha-qlab02.px-npe1103.pks.t-mobile.com/devicefinance-ui/lookup_installment_plan.htm"
When Enter BAN with multiline loan
And Enter userid
And Enter channel
And Enter level
And click on lookup button
And click on planid
And fetch the monthly charge amount
And click on Makepayment
And enter remaining amount
And enter Email for Notifications
And enter verify Email Adress
And click on Submit
Then Page should display "Payment has been made successfully" 
And Status should be Active-Additional-Payment-Applied 
#And Current Remaining Balance should be updated 
#And New Remaining Balance should be updated
And Page Details should be match with DB tables.

@functional
Scenario: Validate payoff for an active Plan having mutiline Loan Payoff for both loans.
Given User navigated to URL  "https://devicefinance-ha-qlab02.px-npe1103.pks.t-mobile.com/devicefinance-ui/lookup_installment_plan.htm"
When Enter BAN with multiline loan
And Enter userid
And Enter channel
And Enter level
And click on lookup button
And click on planid
And fetch the monthly charge amount
And click on Makepayment
And enter remaining amount
And enter Email for Notifications
And enter verify Email Adress
And click on Submit
Then Page should display "Payment has been made successfully" 
And Status should be Active-Additional-Payment-Applied 
#And Current Remaining Balance should be updated 
#And New Remaining Balance should be updated
And Page Details should be match with DB tables.

@functional
Scenario: Validate payoff for an Loan with Status Jump Return pending
Given User navigated to URL  "https://devicefinance-ha-qlab02.px-npe1103.pks.t-mobile.com/devicefinance-ui/lookup_installment_plan.htm"
When Enter Jump Return Pending BAN
And Enter userid
And Enter channel
And Enter level
And click on lookup button
And click on planid
And click on Makepayment
And enter remaining amount
And enter Email for Notifications
And enter verify Email Adress
And click on Submit
Then Page should display "Payment has been made successfully" 
And Status should be Active-Additional-Payment-Applied 
#And Current Remaining Balance should be updated 
#And New Remaining Balance should be updated
And Page Details should be match with DB tables.  

  
  
  
  
  
  @tag2
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
