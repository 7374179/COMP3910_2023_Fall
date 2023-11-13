package com.corejsf.access;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.corejsf.EditableSupplier;
import com.corejsf.model.Supplier;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Dependent
@Stateless
public class SupplierManager implements Serializable {
	private static final long serialVersionUID = 1L;
	 @PersistenceContext(unitName="inventory-jpa") EntityManager em;

	public Supplier find(int id) {
		return em.find(Supplier.class, id);
	}

	public void persist(Supplier supplier) {
		em.persist(supplier);
	}

	public void merge(Supplier supplier) {
		em.merge(supplier);
	}

	public void remove(Supplier supplier) {
	    if (supplier != null) {
	        Supplier s = em.find(Supplier.class, supplier.getId()); 
	        if (s != null) {
	            em.remove(s);
	        }
	    }
	}

	public Supplier[] getAll() {
        TypedQuery<Supplier> query = em.createQuery("select s from Supplier s",
                Supplier.class); 
        java.util.List<Supplier> suppliers = query.getResultList();
        Supplier[] suparray = new Supplier[suppliers.size()];
        for (int i=0; i < suparray.length; i++) {
            suparray[i] = suppliers.get(i);
        }
        return suparray;
    }
	
	public void add(Supplier supplier) {
        try {
            em.persist(supplier);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    public List<EditableSupplier> getAllAsEditable() {
        Supplier[] suppliers = getAll();
        List<EditableSupplier> editableSuppliers = new ArrayList<>();
        for (Supplier s : suppliers) {
            editableSuppliers.add(new EditableSupplier(s));
        }
        return editableSuppliers;
    }
	
	public int getMaxSupplierId() {
        Integer maxId = em.createQuery("SELECT MAX(s.supplierID) FROM Supplier s", Integer.class)
                .getSingleResult();
        return maxId != null ? maxId : 0;
	}
	
	public List<Supplier> findByName(String name) {
	    TypedQuery<Supplier> query = em.createQuery(
	        "SELECT s FROM Supplier s WHERE s.supplierName LIKE :name", Supplier.class);
	    query.setParameter("name", "%" + name + "%");
	    return query.getResultList();
	}
}
