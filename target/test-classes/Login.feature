Feature: User Login

	Background: 
		Given a user is on the login page
		
	Scenario: a user can login using their credentials
		When a user types in their "<email>" and "<password>" and clicks the login button
		Then the user should be on the home page
		
		Examples:
		|     email    | password |
		| el@gmail.com | word     |
		| df@gmail.com | trainer  |
		|	dc@gmail.com | comedian |