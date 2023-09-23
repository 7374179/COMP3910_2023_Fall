package com.corejsf;

import jakarta.enterprise.context.Dependent;

@Dependent
public class Project {
	private String name;
	private String title;
	
	public Project() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
