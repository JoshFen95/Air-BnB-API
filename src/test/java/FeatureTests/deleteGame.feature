Feature: deleteGame


#Add game successfully:
  Scenario:
    Given I am on the delete "game" page
    When I enter id as "5f08571e73bb5c6854202652"
    Then Item should be deleted with title of "Halo CE"

