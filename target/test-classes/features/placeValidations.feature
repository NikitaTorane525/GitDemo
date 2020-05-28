Feature: Validating place API's 
@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceApI 
	Given Add Place Payload "<name>" "<language>" "<address>" 
	When user calls "addPlaceAPI" with "post" http request
	Then the Api call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP" 
	And verify place_Id created maps to "<name>" using "getPlaceAPI" 
	
	Examples:
		|name	|language 	|address 		|
		|ben 	|English 	|abc street 	|
@DeletePlace		
Scenario: Verify if Delete Place functionality is working
	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "post" http request
	Then the Api call got success with status code 200
	And "status" in response body is "OK"
	