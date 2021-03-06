Feature: Get the data externally weather site

  @post @all
  Scenario:Verify to create any station in weatherAPI database
    Given WeatherAPI URI
    When I post the rest call creating a new station
    Then the status code is "201"
    And I validate response
    Then I extract ID value from the response

  @get @all
  Scenario:Verify to get already created station details from weatherAPI database
    Given WeatherAPI URI
    When I execute get call for weatherAPI to get single station details
    Then the status code is "200"
  #  And I validate response

  @getAll @all
  Scenario:Verify to get all created stations details from weatherAPI database
    Given WeatherAPI URI
    When I execute get call for weatherAPI to get all station details
    Then the status code is "200"
    And I get all ID details of all Stations

  @put @all
  Scenario:Verify to update any station in weatherAPI database
    Given WeatherAPI URI
    When I execute put call for weatherAPI to update single station details
    Then the status code is "200"
   # And I validate response

  @delete @all
  Scenario:Verify to delete any station in weatherAPI database
    Given WeatherAPI URI
    When I execute delete call for weatherAPI to delete single station details
    Then the status code is "204"
    #And I validate response


  @all
  Scenario:To create a new station and delete the same with the help of Station ID
    Given WeatherAPI URI
    And I Create the station details using mutiple data and delete the same with the help of Station ID
      | externalid        | namestring    |
      | Narasimha Murthy1 | Tuni Station1 |
      | Narasimha Murthy2 | Tuni Station2 |
      | Narasimha Murthy3 | Tuni Station3 |
      | Narasimha Murthy4 | Tuni Station4 |
      | Narasimha Murthy5 | Tuni Station5 |
      | Narasimha Murthy6 | Tuni Station6 |











