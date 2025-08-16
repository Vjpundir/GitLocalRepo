Feature: Validating AddPlaceAPI's


@AddPlaceAPI
Scenario Outline: Verify if Place is being successfully added using AddPlace API's
      Given Add Place Payload with "<name>" "<address>" "<language>"
      When  User calls "AddPlaceAPI" with "Post" http request
      Then  the API call is success with status code 200
      And   "status" in response body is "OK"
      And   "scope" in response body is "APP"
      And verify place_Id created maps to "<name>" using "GetPlaceAPI"
     
Examples:
         |name  |address|language|
         |vijay |London |English |
#        |Sandy |USA    |Hindi   |


@DeletePlaceAPI
Scenario: Verify if Delete place functionality is working fine
      Given DeletePlace Payload
      When  User calls "DeletePlaceAPI" with "Post" http request
      Then  the API call is success with status code 200
      And   "status" in response body is "OK" 