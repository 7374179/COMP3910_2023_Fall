package com.corejsf;

import java.io.Serializable;

public class Name implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ID;
	private String FName;
	private String LName;
	private String MName;
	private String Add1;
	private String Add2;
	private String City;
	private String State;
	private String Zip;
	private String Phone;
	private String Email;
    private boolean isNew;
	private boolean editable;
	
	public Name() {}
	
	public Name(String ID, String FName, String LName, String MName, String Add1, String Add2, String City, String State, String Zip, String Phone, String Email) {
		this.ID = ID;
		this.FName = FName;
		this.LName = LName;
		this.MName = MName;
		this.Add1 = Add1;
		this.Add2 = Add2;
		this.City = City;
		this.State = State;
		this.Zip = Zip;
		this.Phone = Phone;
		this.Email = Email;		
	}

	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	
	public String getFName() {
		return FName;
	}
	
	public void setFName(String fName) {
		FName = fName;
	}
	
	public String getLName() {
		return LName;
	}
	
	public void setLName(String lName) {
		LName = lName;
	}
	
	public String getMName() {
		return MName;
	}
	
	public void setMName(String mName) {
		MName = mName;
	}
	
	public String getAdd1() {
		return Add1;
	}
	
	public void setAdd1(String add1) {
		Add1 = add1;
	}
	
	public String getAdd2() {
		return Add2;
	}
	
	public void setAdd2(String add2) {
		Add2 = add2;
	}
	
	public String getCity() {
		return City;
	}
	
	public void setCity(String city) {
		City = city;
	}
	
	public String getState() {
		return State;
	}
	
	public void setState(String state) {
		State = state;
	}
	
	public String getZip() {
		return Zip;
	}
	
	public void setZip(String zip) {
		Zip = zip;
	}
	
	public String getPhone() {
		return Phone;
	}
	
	public void setPhone(String phone) {
		Phone = phone;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	
    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }
    
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
}
