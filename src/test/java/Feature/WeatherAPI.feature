Feature: Get the data externally weather site

  @post
  Scenario:Verify to create any station in weatherAPI database
    Given WeatherAPI URI
    When I post the rest call creating a new station
    Then the status code is "201"
    And I validate response
    Then I extract ID value from the response

  @get
  Scenario:Verify to get already created station details from weatherAPI database
    Given WeatherAPI URI
    When I execute get call for weatherAPI to get single station details
    Then the status code is "200"
    And I validate response

  @getAll
  Scenario:Verify to get all created stations details from weatherAPI database
    Given WeatherAPI URI
    When I execute get call for weatherAPI to get all station details
    Then the status code is "200"
    And I validate response

  @put
  Scenario:Verify to update any station in weatherAPI database
    Given WeatherAPI URI
    When I execute put call for weatherAPI to update single station details
    Then the status code is "201"
    And I validate response

  @delete
  Scenario:Verify to delete any station in weatherAPI database
    Given WeatherAPI URI
    When I execute delete call for weatherAPI to delete single station details
    Then the status code is "201"
    And I validate response