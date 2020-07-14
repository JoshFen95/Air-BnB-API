Feature: deleteFilm


#Add game successfully:
  Scenario:
    Given I am on the delete "film" page
    When I enter id as "TBD"
    Then Item should be deleted with title of "BOOKTEST"