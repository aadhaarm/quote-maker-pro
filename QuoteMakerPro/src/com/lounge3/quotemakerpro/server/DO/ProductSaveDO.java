package com.lounge3.quotemakerpro.server.DO;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ProductSaveDO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key productSaveId;
	
	@Persistent
	private Long productId;

	@Persistent
	private Double quotedPrice;
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Key getProductSaveId() {
		return productSaveId;
	}

	public void setProductSaveId(Key productSaveId) {
		this.productSaveId = productSaveId;
	}

	public Double getQuotedPrice() {
		return quotedPrice;
	}

	public void setQuotedPrice(Double quotedPrice) {
		this.quotedPrice = quotedPrice;
	}

	@Override
	public String toString() {
		return "ProductSaveDO [productSaveId=" + productSaveId + ", productId="
				+ productId + ", quotedPrice=" + quotedPrice + "]";
	}
}