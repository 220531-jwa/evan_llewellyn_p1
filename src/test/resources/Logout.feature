Feature: User Logout
		
	Scenario: a user can logout by clicking the logout button on the home page
		Given a user is already logged in and on the home page
		When a user clicks the logout button
		Then the user should be on the login page