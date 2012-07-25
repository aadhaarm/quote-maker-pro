package com.lounge3.quotemakerpro.shared.TO;

import java.io.Serializable;

public class ElementTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private String title;
	
	private String description;
	
	private Double price;

	private String type;
	
	private String elementQuantityType;
	
	private String quantityAlgo;
	
	private Long minQuantity;
	
	private Long maxQuantity;
	
	private String quantityUnit;
	
	private String multiOrMan;

	public ElementTO() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param name
	 * @param title
	 * @param description
	 * @param price
	 * @param type
	 * @param elementQuantityType
	 * @param quantityAlgo
	 * @param minQuantity
	 * @param maxQuantity
	 * @param quantityUnit
	 */
	public ElementTO(Long id, String name, String title, String description,
			Double price, String type, String elementQuantityType,
			String quantityAlgo, long minQuantity, long maxQuantity,
			String quantityUnit) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.description = description;
		this.price = price;
		this.type = type;
		this.elementQuantityType = elementQuantityType;
		this.quantityAlgo = quantityAlgo;
		this.minQuantity = minQuantity;
		this.maxQuantity = maxQuantity;
		this.quantityUnit = quantityUnit;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getElementQuantityType() {
		return elementQuantityType;
	}

	public void setElementQuantityType(String elementQuantityType) {
		this.elementQuantityType = elementQuantityType;
	}

	public String getQuantityAlgo() {
		return quantityAlgo;
	}

	public void setQuantityAlgo(String quantityAlgo) {
		this.quantityAlgo = quantityAlgo;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public Long getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(Long minQuantity) {
		this.minQuantity = minQuantity;
	}

	public Long getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(Long maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public String getMultiOrMan() {
		return multiOrMan;
	}

	public void setMultiOrMan(String multiOrMan) {
		this.multiOrMan = multiOrMan;
	}
}