package com.corejsf;

import java.io.Serializable;
import java.util.ArrayList;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class NewCustomer implements Serializable {
	
    @Inject
    private Customer customer; 
	
	private static final long serialVersionUID = 1L;
	private String newID;
	private String newFName;
	private String newLName;
	private String newMName;
	private String newAdd1;
	private String newAdd2;
	private String newCity;
	private String newState;
	private String newZip;
	private String newPhone;
	private String newEmail;
	private boolean showInputArea = false;

	public ArrayList<Name> getNames() {
		return customer.getNames();
	}

	public String getNewID() {
		return newID;
	}

	public void setNewID(String newID) {
		this.newID = newID;
	}

	public String getNewFName() {
		return newFName;
	}

	public void setNewFName(String newFName) {
		this.newFName = newFName;
	}

	public String getNewLName() {
		return newLName;
	}

	public void setNewLName(String newLName) {
		this.newLName = newLName;
	}

	public String getNewMName() {
		return newMName;
	}

	public void setNewMName(String newMName) {
		this.newMName = newMName;
	}

	public String getNewAdd1() {
		return newAdd1;
	}

	public void setNewAdd1(String newAdd1) {
		this.newAdd1 = newAdd1;
	}

	public String getNewAdd2() {
		return newAdd2;
	}

	public void setNewAdd2(String newAdd2) {
		this.newAdd2 = newAdd2;
	}

	public String getNewCity() {
		return newCity;
	}

	public void setNewCity(String newCity) {
		this.newCity = newCity;
	}

	public String getNewState() {
		return newState;
	}

	public void setNewState(String newState) {
		this.newState = newState;
	}

	public String getNewZip() {
		return newZip;
	}

	public void setNewZip(String newZip) {
		this.newZip = newZip;
	}

	public String getNewPhone() {
		return newPhone;
	}

	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public boolean isShowInputArea() {
		return showInputArea;
	}

	public void toggleInputArea() {
		this.showInputArea = !this.showInputArea;
	}
	
	public void addNewRow() {
	    Name newName = new Name();
	    newName.setIsNew(true);
	    getNames().add(newName);
        this.showInputArea = true;
	}

	public void addName() {
		Name newName = new Name(newID, newFName, newLName, newMName, newAdd1, newAdd2, newCity, newState, newZip, newPhone, newEmail);
        customer.getNames().add(newName);
	}

	public void saveCustomer() {
	    for (Name name : getNames()) {
	        if (name.getIsNew()) {
	            name.setIsNew(false); 
	        }
	    }

	    this.showInputArea = false;	    
	}
}
