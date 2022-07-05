Feature: Invalid Credentials at Login

	Background: 
		Given a user is on the home page	

	Scenario Outline: a user attempts to login with invalid credentials
		When a user types in a invalid "<email>" and/or "<password>" and clicks the login button
		Then the user should get an alert
		And after clicking ok
		And still be on the login page
		
		Examples:
		| email          | password |
		| evan@gmail.com | word     |
		| df@gmail.com   | 1234     |
		|	bb@gmail.com   | DNE      |