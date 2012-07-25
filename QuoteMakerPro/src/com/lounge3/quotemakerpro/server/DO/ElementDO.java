package com.lounge3.quotemakerpro.server.DO;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class ElementDO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String userEmail;
	
	@Persistent
	private String name;
	
	@Persistent
	private String title;
	
	@Persistent
	private String description;
	
	@Persistent
	private Double price;

	@Persistent
	private String type;
	
	@Persistent
	private String elementQuantityType;
	
	@Persistent
	private String quantityAlgo;
	
	@Persistent
	private String multiOrMan;
		
	@Persistent
	private Long minQuantity;
	
	@Persistent
	private Long maxQuantity;
	
	@Persistent
	private String quantityUnit;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ElementDO() {
		super();
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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

	public String getQuantityUnit() {
		return quantityUnit;
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