Feature: Lookup
 
  @functional
  Scenario: Validate page title installment plan summary
    Given User navigated to URL  "https://devicefinance-ha-qlab02.px-npe1103.pks.t-mobile.com/devicefinance-ui/lookup_installment_plan.htm"
    When Enter BAN
    And Enter userid
    And Enter channel
    And Enter level
    And click on lookup button
    Then Page Title should be "Installment Plan Summary"
 @functional  @ErrorScenarios
  Scenario: Validate lookupPage_Error Channel and Userid Required
    Given User navigated to URL  "https://devicefinance-ha-qlab02.px-npe1103.pks.t-mobile.com/devicefinance-ui/lookup_installment_plan.htm"
    When Enter BAN
    Then I click lookup userid required error message should be displayed
    And Channel required error message should be displayed