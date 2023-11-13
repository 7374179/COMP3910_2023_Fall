package com.corejsf.model;

import java.io.Serializable;

import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Named;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Suppliers")
@ConversationScoped
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="SupplierID")
	private int supplierID;
	
	@Column(name="SupplierName")
	private String supplierName;
	
	@Column(name="ContactName")
	private String contactName;
	
	@Column(name="ContactTitle")
	private String contactTitle;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="City")
	private String city;
	
	@Column(name="PostalCode")
	private String postalCode;
	
	@Column(name="StateOrProvince")
	private String stateOrProvince;
	
	@Column(name="Country")
	private String country;
	
	@Column(name="PhoneNumber")
	private String phoneNumber;
	
	@Column(name="FaxNumber")
	private String faxNumber;
	
	@Column(name="PaymentTerms")
	private String paymentTerms;
	
	@Column(name="EmailAddress")
	private String emailAddress;
	
	@Column(name="Notes")
	private String notes;
	
	public Supplier() {}


	/**
	 * @return the id
	 */
	public int getId() {
		return supplierID;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int supplierID) {
		this.supplierID = supplierID;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return supplierName;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String supplierName) {
		this.supplierName = supplierName;
	}
	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * @return the contactTitle
	 */
	public String getContactTitle() {
		return contactTitle;
	}
	/**
	 * @param contactTitle the contactTitle to set
	 */
	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the stateOrProvince
	 */
	public String getStateOrProvince() {
		return stateOrProvince;
	}
	/**
	 * @param stateOrProvince the stateOrProvince to set
	 */
	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}
	/**
     * @return the country
     */
    public String getCountry() {
        return country;
    }
    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
     * @return the faxNumber
     */
    public String getFaxNumber() {
        return faxNumber;
    }
    /**
     * @param faxNumber the faxNumber to set
     */
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }
    /**
     * @return the paymentTerms
     */
    public String getPaymentTerms() {
        return paymentTerms;
    }
    /**
     * @param paymentTerms the paymentTerms to set
     */
    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }
    /**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
}

