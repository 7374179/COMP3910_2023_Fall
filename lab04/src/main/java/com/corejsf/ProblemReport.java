package com.corejsf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;

@Named("problemReport")
@SessionScoped
public class ProblemReport implements Serializable {

	@Inject
	private Project project;

	public Project getProject() {
		return project;
	}

	public String getProjectName() {
		return project.getName();
	}

	public String getProjectTitle() {
		return project.getTitle();
	}

	private String heading;
	private Integer number; // Number
	private String originator; // Originator
	private String ma; // Modules Affected
	private String description; // Description
	private String solution; // Recommend Solution
	private String priority; // Priority
	private String severity; // Severity
	private String dateAssigned; // Date Assigned
	private String dateFixed; // Date Fixed
	private String date;
	private boolean isScope;

	// to use 'in scope' check box
	public boolean isScope() {
		return isScope;
	}

	public void setScope(boolean isScope) {
		this.isScope = isScope;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getOriginator() {
		return originator;
	}

	public void setOriginator(String originator) {
		this.originator = originator;
	}

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getDateAssigned() {
		return dateAssigned;
	}

	public void setDateAssigned(String dateAssigned) {
		this.dateAssigned = dateAssigned;
	}

	public String getDateFixed() {
		return dateFixed;
	}

	public void setDateFixed(String dateFixed) {
		this.dateFixed = dateFixed;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String initializeNewPro() {
		this.heading = null;
		this.number = null;
		this.originator = null;
		this.ma = null;
		this.description = null;
		this.solution = null;
		this.priority = null;
		this.severity = null;
		this.dateAssigned = null;
		this.dateFixed = null;
		this.date = null;
		this.isScope = false;

		return "enterProblem";
	}
}
