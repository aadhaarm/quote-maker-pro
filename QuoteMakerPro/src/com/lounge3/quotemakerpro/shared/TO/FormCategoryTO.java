package com.lounge3.quotemakerpro.shared.TO;

import java.io.Serializable;
import java.util.List;

public class FormCategoryTO implements Serializable {
	
	public FormCategoryTO() {
		super();
	}

	public FormCategoryTO(Long categoryId) {
		super();
		this.categoryId = categoryId;
	}

	private static final long serialVersionUID = 1L;
	
	private String formCategoryId;
	
	private Long categoryId;
	
	private String name;
	
	private String title;
	
	private String description;

	private List<FormProductTO> products;

	public String getFormCategoryId() {
		return formCategoryId;
	}

	public void setFormCategoryId(String formCategoryId) {
		this.formCategoryId = formCategoryId;
	}

	public List<FormProductTO> getProducts() {
		return products;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setProducts(List<FormProductTO> products) {
		this.products = products;
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
}
