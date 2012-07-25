package com.lounge3.quotemakerpro.shared.TO;

import java.io.Serializable;


public class ProductSaveTO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private Long productId;

	private Double quotedPrice;
	
	private Long quantity;
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getQuotedPrice() {
		return quotedPrice;
	}

	public void setQuotedPrice(Double quotedPrice) {
		this.quotedPrice = quotedPrice;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductSaveDO [productId="
				+ productId + ", quotedPrice=" + quotedPrice + "]";
	}
}
