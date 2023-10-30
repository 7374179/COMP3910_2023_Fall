package com.corejsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class EdTableData implements Serializable {
	
    @Inject
    private Customer customer;

    
	private static final long serialVersionUID = 1L;
	private ArrayList<Name> names = new ArrayList<Name>(Arrays.asList(
		new Name("101","Rajiv","Pauli","","213 Erstwild Court","Apt 30","Sunnyvale","CA","94086","408-789-8075","rp@rpuli.com"),
		new Name("102","Carole","Sandler","S","785 Geary St","","San Francisco","CA","94117","415-822-1289","cs@sandler.com"),
		new Name("103","Philip","Currie","","654 Poplar","Apt 50","Wahsington","CA","94303","415-328-4543","pc@pCurrie.com"),
		new Name("104","Tony","Higgins","H","East Shopping Cntr.","422 Bay Road","Altanta","GA","94026","415-368-1100","th@tHiggins.com"),
		new Name("105", "Rio","Vector","","1899 La Loma Drive","","Los Altos","CA","94022","415-776-3249","rv@rvictor.com")
		));
	
	public ArrayList<Name> getNames() {
		return customer.getNames();
	}
	
	public String save() {
		for(Name name : getNames()) name.setEditable(false);
		return null;
	}
	
	


}
