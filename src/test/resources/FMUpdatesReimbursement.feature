Feature: Finance Manager Updates Reimbursement

	Background: 
		Given a finance manager is on the login page and has a reimbursement requests to update
		
	Scenario Outline: 
		When a finance manager types in their "<email>" and "<password>" and then clicks the login button
		And they click on the edit button for one of the reimbursement requests
		And they update the request to "<status>"
		And they click on the update button to finish editing
		Then the finance manager should be on the home page with their edits made to "<status>"
		
		Examples:
		|     email    | password   | status    |
		| df@gmail.com | trainer    | Pending		|
		| df@gmail.com | trainer    |	Rejected	|
		|	sh@gmail.com | host       | Approved	|