package com.corejsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.corejsf.access.SupplierManager;
import com.corejsf.model.Supplier;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
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
	
	@PostConstruct
	  public void init() {
	    if (conversation.isTransient()) {
	      conversation.begin();
	    }
	  }

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
		Supplier[] suppliers = supplierManager.getAll();
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
	public void deleteRow(EditableSupplier editableSupplier) {
	    Supplier s = editableSupplier.getSupplier();
	    if (s != null) {
	        supplierManager.remove(s);
	        if (filteredSuppliers != null) {
	            filteredSuppliers.removeIf(es -> es.getSupplier().getId() == s.getId());
	        }
	        if (list != null) {
	            list.removeIf(es -> es.getSupplier().getId() == s.getId());
	        }

	        refreshList();
	    }
	}
	
	public String save() {
	    List<EditableSupplier> suppliersToSave;
	    if (filteredSuppliers != null && !filteredSuppliers.isEmpty()) {
	        suppliersToSave = filteredSuppliers; 
	    } else {
	        suppliersToSave = list;
	    }

	    for (EditableSupplier e : suppliersToSave) {
	        if (e.isEditable()) {
	            Supplier supplierToSave = e.getSupplier();
	            if (supplierManager.find(supplierToSave.getId()) != null) {
	                supplierManager.merge(supplierToSave);
	            } else {
	                supplierManager.persist(supplierToSave);
	            }
	            e.setEditable(false); 
	        }
	    }
	    
	    refreshList();
	    
	    if (!conversation.isTransient()) {
	        conversation.end();
	    }

	    return "list?faces-redirect=true";
	}



    
    public String add() {
        Supplier newSupplier = new Supplier();
        int newId = supplierManager.getMaxSupplierId() + 1;
        System.out.println("Generated SupplierID: " + newId);
        newSupplier.setId(newId);
        
        this.newSupplier = newSupplier;
        
        return "add?faces-redirect=true";
    }

	
    private String searchKeyword; 
    private List<EditableSupplier> filteredSuppliers; 

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public List<EditableSupplier> getFilteredSuppliers() {
        return filteredSuppliers;
    }
    
    public void findSuppliersByName() {
        List<Supplier> result = supplierManager.findByName(searchKeyword);
        this.filteredSuppliers = new ArrayList<>();
        for (Supplier supplier : result) {
            this.filteredSuppliers.add(new EditableSupplier(supplier));
        }
    }
    
    private Supplier newSupplier;

    public Supplier getNewSupplier() {
        if (newSupplier == null) {
            newSupplier = new Supplier();
        }
        return newSupplier;
    }

    public void setNewSupplier(Supplier newSupplier) {
        this.newSupplier = newSupplier;
    }

    public String saveNewSupplier() {
        supplierManager.persist(newSupplier);
        refreshList(); 
        return "editSupplier.xhtml"; 
    }
}
