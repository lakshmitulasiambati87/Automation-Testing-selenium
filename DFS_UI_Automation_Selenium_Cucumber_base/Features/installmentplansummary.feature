Feature: InstallmentPlanSummary
 
  @functional
  Scenario: Validate page title installment plan summary
    Given User navigated to URL  "https://devicefinance-ha-qlab02.px-npe1103.pks.t-mobile.com/devicefinance-ui/lookup_installment_plan.htm"
    When Enter BAN
    And Enter userid
    And Enter channel
    And Enter level
    And click on lookup button
    Then Page Title should be "Installment Plan Summary"
    
    @functional
    Scenario: Validate BAN and BAN status
    Given User navigated to URL  "https://devicefinance-ha-qlab02.px-npe1103.pks.t-mobile.com/devicefinance-ui/lookup_installment_plan.htm"
    When Enter BAN
    And Enter userid
    And Enter channel
    And Enter level
    And click on lookup button
    Then Verify BAN
    And Verify BAN Status