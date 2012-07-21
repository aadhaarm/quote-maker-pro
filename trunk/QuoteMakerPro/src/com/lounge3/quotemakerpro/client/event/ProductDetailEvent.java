package com.lounge3.quotemakerpro.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ProductDetailEvent extends GwtEvent<ProductDetailEventHandler>{
	public static Type<ProductDetailEventHandler> TYPE = new Type<ProductDetailEventHandler>();
	private String productName;

	public ProductDetailEvent(String productName) {
		super();
		this.productName = productName;
	}

	@Override
	public Type<ProductDetailEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ProductDetailEventHandler handler) {
		handler.onShowProductDetail(this);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}