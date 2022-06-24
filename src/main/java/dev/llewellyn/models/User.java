package dev.llewellyn.models;

public class User {
	
	private int uId;
	private String firstName;
	private String lastName;
	private String email;
	private String pass;
	private int availableAmount; //Amount of reimbursement left this year
	private boolean isFinanceManager;
	
	public User() {}
	
	public User(int id, String firstName, String lastName, String email, String pass, int availableAmount,
			boolean isFinanceManager) {
		this.uId = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
		this.availableAmount = availableAmount;
		this.isFinanceManager = isFinanceManager;
	}

	public int getUId() {
		return uId;
	}
	
	public void setId(int uId) {
		this.uId = uId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public int getAvailableAmount() {
		return availableAmount;
	}
	
	public void setAvailableAmount(int availableAmount) {
		this.availableAmount = availableAmount;
	}
	
	public boolean isFinanceManager() {
		return isFinanceManager;
	}
	
	public void setFinanceManager(boolean isFinanceManager) {
		this.isFinanceManager = isFinanceManager;
	}
	
	@Override
	public String toString() {
		return "User [uId=" + uId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", pass="
				+ pass + ", availableAmount=" + availableAmount + ", isFinanceManager=" + isFinanceManager + "]";
	}
}
