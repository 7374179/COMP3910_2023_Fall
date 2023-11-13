package com.corejsf;

import java.io.Serializable;

import jakarta.enterprise.context.Dependent;

@Dependent
public class Project implements Serializable {
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
