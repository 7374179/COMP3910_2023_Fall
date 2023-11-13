package com.corejsf;

import java.io.Serializable;

import com.corejsf.model.Supplier;

import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Named;

@Named
@ConversationScoped
public class EditableSupplier implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Indicates associated product can be edited on a form.*/
    private boolean editable;
    
    /** Holds product to be displayed, edited or deleted.*/
    private Supplier supplier;
    
    /**
     * Convenience constructor.
     * @param product product to be displayed, edited or deleted.
     */
    public EditableSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
    /**
     * Returns true if associated product is editable.
     * @return the editable state
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * Set whether associated product is editable.
     * @param editable the state of editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * @return the product
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * @param product the product to set
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
