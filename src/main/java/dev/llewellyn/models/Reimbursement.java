package dev.llewellyn.models;

import java.sql.Date;
import java.sql.Time;

public class Reimbursement {

	private int rId;
	private int userId;
	private String status;
	private String description;
	private double rCost;
	private String rLocation;
	private Date startDate;
	private Date endDate;
	private Time startTime;
	private Time endTime;
	private String rType;
	private String gradeFormat;
	private String passingGrade;
	private String gradeReceived;
	private boolean presentationSubmitted;
	private double reimbursementAmount;
	
	public Reimbursement() {}

	public Reimbursement(int rId, int userId, String status, String description, double rCost, String rLocation,
			Date startDate, Date endDate, Time startTime, Time endTime, String rType, String gradeFormat,
			String passingGrade, String gradeReceived, boolean presentationSubmitted, double reimbursementAmount) {
		super();
		this.rId = rId;
		this.userId = userId;
		this.status = status;
		this.description = description;
		this.rCost = rCost;
		this.rLocation = rLocation;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.rType = rType;
		this.gradeFormat = gradeFormat;
		this.passingGrade = passingGrade;
		this.gradeReceived = gradeReceived;
		this.presentationSubmitted = presentationSubmitted;
		this.reimbursementAmount = reimbursementAmount;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
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

	public double getrCost() {
		return rCost;
	}

	public void setrCost(double rCost) {
		this.rCost = rCost;
	}

	public String getrLocation() {
		return rLocation;
	}

	public void setrLocation(String rLocation) {
		this.rLocation = rLocation;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getrType() {
		return rType;
	}

	public void setrType(String rType) {
		this.rType = rType;
	}

	public String getGradeFormat() {
		return gradeFormat;
	}

	public void setGradeFormat(String gradeFormat) {
		this.gradeFormat = gradeFormat;
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

	public double getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	@Override
	public String toString() {
		return "Reimbursement [rId=" + rId + ", userId=" + userId + ", status=" + status + ", description="
				+ description + ", rCost=" + rCost + ", rLocation=" + rLocation + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", startTime=" + startTime + ", endTime=" + endTime + ", rType=" + rType
				+ ", gradeFormat=" + gradeFormat + ", passingGrade=" + passingGrade + ", gradeReceived=" + gradeReceived
				+ ", presentationSubmitted=" + presentationSubmitted + ", reimbursementAmount=" + reimbursementAmount
				+ "]";
	}
}