package com.lounge3.quotemakerpro.server.DO;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class FormCategoryDO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key formCategoryId;
	
	@Persistent
	private Long categoryId;

	@Persistent
	private List<FormProductDO> products;

	public Key getFormCategoryId() {
		return formCategoryId;
	}

	public void setFormCategoryId(Key formCategoryId) {
		this.formCategoryId = formCategoryId;
	}

	public List<FormProductDO> getProducts() {
		return products;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setProducts(List<FormProductDO> products) {
		this.products = products;
	}
}
