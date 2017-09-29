Feature:  Check all listed vehicles against DVLA

  Scenario Outline: Check one vehicles
    When I navigate to "https://www.gov.uk/get-vehicle-information-from-dvla"
    And click 'Start Now'
    And enter vehicle registration number "<registration>"
    And click 'Continue'
    Then make should be "<make>"
    And  color should be "<color>"
    Examples:
      | registration | make          | color  |
      | SK07NZX      | MERCEDES      | BLACK  |
      | LY62JLX      | MERCEDES-BENZ | SILVER |