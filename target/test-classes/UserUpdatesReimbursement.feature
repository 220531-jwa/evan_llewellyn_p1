Feature: User Updates Reimbursement

	Background: 
		Given a user is on the login page and already has a reimbursement request submitted
		
	Scenario Outline: 
		When a user types in their "<email>" and "<password>" and then clicks the login button
		And they click on the edit button for one of their reimbursement requests
		And they fill out the "<gReceived>" or "<pSubmitted>" field
		And they click on the update button
		Then the user should be on the home page with their edits made to either "<gReceived>" or "<pSubmitted>"
		
		Examples:
		|     email    | password   | gReceived | pSubmitted |
		| mj@gmail.com | basketball | B					| false			 |
		| ts@gmail.com | iron       |	Pass			| false			 |
		|	dc@gmail.com | comedian   | 					| true			 |