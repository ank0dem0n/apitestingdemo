Feature: validating place api

@Deleteplaceapi
Scenario Outline: verify if place is being succesfully added by addplace api
Given add_place payload "<name>" "<language>" "<address>"
When user call "add_placeApi" with "post" http request
Then then api call got success with status code "200"
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place id created maps to "<name>" and "get_placeApi"

Examples:
		|name|language|address|
		|abc|english|67 side layout, cohen 09|
#		|dfdf|french|jdfdf|
#		|989|swiss| cohen 09|
#		|ghs|eng|67 sut, cohen 09|
#		|abc|russian|ohen 09|
		