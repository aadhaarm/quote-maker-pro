package com.lounge3.quotemakerpro.shared.TO;

import java.io.Serializable;
import java.util.List;

public class FormTO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private Long formId;
	
	private String userEmail;
	
	private String name;
	
	private String title;
	
	private String description;

	private List<FormCategoryTO> categories;

	public Long getFormId() {
		return formId;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<FormCategoryTO> getCategories() {
		return categories;
	}

	public void setCategories(List<FormCategoryTO> categories) {
		this.categories = categories;
	}
}