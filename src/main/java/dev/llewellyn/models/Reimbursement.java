package dev.llewellyn.models;

public class Reimbursement {

	private int rId;
	private int userId;
	private String status;
	private String description;
	private int rCost;
	private String datetime;
	private String rType;
	private String gradeType;
	private String passingGrade;
	private String gradeReceived;
	private boolean presentationSubmitted;
	
	public Reimbursement() {}

	public Reimbursement(int rId, int userId, String status, String description, int rCost, String datetime,
			String rType, String gradeType, String passingGrade, String gradeReceived, boolean presentationSubmitted) {
		super();
		this.rId = rId;
		this.userId = userId;
		this.status = status;
		this.description = description;
		this.rCost = rCost;
		this.datetime = datetime;
		this.rType = rType;
		this.gradeType = gradeType;
		this.passingGrade = passingGrade;
		this.gradeReceived = gradeReceived;
		this.presentationSubmitted = presentationSubmitted;
	}

	public int getRId() {
		return rId;
	}

	public void setRId(int rId) {
		this.rId = rId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRCost() {
		return rCost;
	}

	public void setRCost(int rCost) {
		this.rCost = rCost;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getRType() {
		return rType;
	}

	public void setRType(String rType) {
		this.rType = rType;
	}

	public String getGradeType() {
		return gradeType;
	}

	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}

	public String getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(String passingGrade) {
		this.passingGrade = passingGrade;
	}

	public String getGradeReceived() {
		return gradeReceived;
	}

	public void setGradeReceived(String gradeReceived) {
		this.gradeReceived = gradeReceived;
	}

	public boolean isPresentationSubmitted() {
		return presentationSubmitted;
	}

	public void setPresentationSubmitted(boolean presentationSubmitted) {
		this.presentationSubmitted = presentationSubmitted;
	}

	@Override
	public String toString() {
		return "Reimbursement [rId=" + rId + ", userId=" + userId + ", status=" + status + ", description="
				+ description + ", rCost=" + rCost + ", datetime=" + datetime + ", rType=" + rType + ", gradeType="
				+ gradeType + ", passingGrade=" + passingGrade + ", gradeReceived=" + gradeReceived
				+ ", presentationSubmitted=" + presentationSubmitted + "]";
	}
}
