package com.lounge3.quotemakerpro.server.DO;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class FormDO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long formId;
	
	@Persistent
	private String userEmail;
	
	@Persistent
	private String name;
	
	@Persistent
	private String title;
	
	@Persistent
	private String description;

	@Persistent
	private List<FormCategoryDO> categories;

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

	public List<FormCategoryDO> getCategories() {
		return categories;
	}

	public void setCategories(List<FormCategoryDO> categories) {
		this.categories = categories;
	}
}
