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


  @function
  Scenario: Customer returns the financed device
Given User navigated to URL  "https://devicefinance-ha-qlab02.px-npe1103.pks.t-mobile.com/devicefinance-ui/lookup_installment_plan.htm"
When Enter BAN
And Enter userid
And Enter channel
And Enter level
And click on lookup button
And click on planid
And check status of equipment
And click on Return checkbox
And click on Return Selected Items 
And enter Email and verify email
And click on Submit in pop up
And Accept the alert 
Then agreement status in agreements_tmo table should be "Return-Complete"
And FAT should have "Return Complete" activity with -ve outstanding balance in balance_activity column

@functiona
  Scenario: Customer returns the financed device
Given User navigated to URL  "https://devicefinance-ha-qlab02.px-npe1103.pks.t-mobile.com/devicefinance-ui/lookup_installment_plan.htm"
When Enter BAN with multiline loan
And Enter userid
And Enter channel
And Enter level
And click on lookup button
And click on planid
And check status of equipment
And click on Return checkbox
And click on Return Selected Items 
And enter Email and verify email
And click on Submit in pop up
And Accept the alert 
Then agreement status in agreements_tmo table should be "Active-Partial"
And FAT should have "Return Complete" activity with -ve outstanding balance in balance_activity column

@functiona
  Scenario: Customer returns the financed device after balance payment
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
And navigate back to summary screen
And check status of equipment
And click on Return checkbox
And click on Return Selected Items 
And enter Email and verify email
And click on Submit in pop up
And Accept the alert 
Then agreement status in agreements_tmo table should be "Return-Complete"
And FAT should have "Return Complete" activity with -ve outstanding balance in balance_activity column

@functional
  Scenario: Customer returns the financed device after balance payment
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
And navigate back to summary screen
And check status of equipment
And click on Return checkbox
And click on Return Selected Items 
And enter Email and verify email
And click on Submit in pop up
And Accept the alert 
Then agreement status in agreements_tmo table should be "Return-Complete"
And FAT should have "Return Complete" activity with -ve outstanding balance in balance_activity column


  