Feature: deleteBook


#Add game successfully:
  Scenario:
    Given I am on the delete "book" page
    When I enter id as "5f05bd65648a236fbee4cb32"
    Then Item should be deleted with title of "BOOKTEST"