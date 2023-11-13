package com.corejsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ConversationScoped
public class SupplierListForm implements Serializable {
	private static final long serialVersionUID = 1L;

	/** Manager from Supplier objects. */
	@Inject
	private SupplierManager supplierManager;

	@Inject
	Conversation conversation;

	List<EditableSupplier> list;
	
	private EditableSupplier newSupplier;

	/**
	 * Returns array of EditableSuppliers that match the selected category.
	 * 
	 * @return list of editable Suppliers
	 */
	public List<EditableSupplier> getList() {
		if (list == null) {
			refreshList();
		}
		return list;
	}

	private void refreshList() {
		System.out.println("Refreshing supplier list...");
		Supplier[] suppliers = supplierManager.getAll();
		System.out.println("Total suppliers: " + suppliers.length);

		list = new ArrayList<EditableSupplier>();
		for (Supplier supplier : suppliers) {
			list.add(new EditableSupplier(supplier));
		}

	}

	public void setList(List<EditableSupplier> ep) {
		list = ep;
	}

	/**
	 * Delete the Supplier and return to the same page.
	 * 
	 * @param e the Supplier to be deleted
	 * @return Navigation string
	 */
	public String deleteRow(Supplier s) {
		if (s != null) {
			supplierManager.remove(s);

			if (list != null) {
				list.removeIf(sup -> sup.getSupplier().getId() == s.getId());
			}
		}
		return null;
	}
	

	public String save() {
	    if (list == null) {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No suppliers to save."));
	        return null; // Or any other appropriate response or error handling
	    }
	    
	    for (EditableSupplier e : list) {
	        if (e.isEditable()) {
	            // Existing save logic
	            if (supplierManager.find(e.getSupplier().getName()) != null) { // Check if supplier exists in DB
	                // Existing save logic
	            } 
	            e.setEditable(false);
	        }
	    }
	    refreshList();
	    if (!conversation.isTransient()) {
	        conversation.end();
	    }
	    return "editSupplier?faces-redirect=true";
	}

	public String add() {
	    Supplier newSupplier = new Supplier();
	    int newId = supplierManager.getMaxSupplierId() + 1;
	    System.out.println("Generated SupplierID: " + newId); // 로깅
	    
	    newSupplier.setId(newId);
	    newSupplier.setName("ccc");
	    newSupplier.setContactName("aaa");
	    newSupplier.setContactTitle("aaa");
	    newSupplier.setAddress("aaa");
	    newSupplier.setCity("aaa");
	    newSupplier.setPostalCode("aaa");
	    newSupplier.setStateOrProvince("aaa");
	    newSupplier.setCountry("aaa");
	    newSupplier.setPhoneNumber("111");
	    newSupplier.setFaxNumber("121");
	    newSupplier.setPaymentTerms("111");
	    newSupplier.setEmailAddress("111");
	    newSupplier.setNotes("");
	    
	    supplierManager.persist(newSupplier);
	    
	    if (list == null) {
	        list = new ArrayList<>();
	    }
	    list.add(new EditableSupplier(newSupplier));

	    return "add";
	}
	
    private String searchKeyword; 
    
    private List<EditableSupplier> filteredSuppliers; // EditableSupplier 리스트로 변경

    public List<EditableSupplier> getFilteredSuppliers() {
        return filteredSuppliers;
    }


    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }


    public void findSuppliersByName() {
        Supplier result = supplierManager.find(searchKeyword);
        this.filteredSuppliers = new ArrayList<>();
        if (result != null) {
            this.filteredSuppliers.add(new EditableSupplier(result)); // EditableSupplier 객체로 추가
        }
    }

	
}
