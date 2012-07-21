package com.lounge3.quotemakerpro.shared.TO;

import java.io.Serializable;

public class FormProductTO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private String formProductId;
	
	private Long productId;
	
	private String name;
	
	private String title;
	
	private String description;
	
	private Double price;

	public String getFormProductId() {
		return formProductId;
	}

	public void setFormProductId(String formProductId) {
		this.formProductId = formProductId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public FormProductTO() {
		super();
	}

	public FormProductTO(Long productId) {
		super();
		this.productId = productId;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
