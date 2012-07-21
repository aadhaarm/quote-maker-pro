package com.lounge3.quotemakerpro.shared.TO;


public class ProdCatAssociationTO {
	private Long id;
		
	private Long productId;
	
	private Long categoryId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public ProdCatAssociationTO() {
		super();
	}

	public ProdCatAssociationTO(Long id, Long productId, Long categoryId) {
		super();
		this.id = id;
		this.productId = productId;
		this.categoryId = categoryId;
	}
}