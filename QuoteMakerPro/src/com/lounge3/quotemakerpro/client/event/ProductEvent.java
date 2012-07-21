package com.lounge3.quotemakerpro.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ProductEvent extends GwtEvent<ProductEventHandler>{
  public static Type<ProductEventHandler> TYPE = new Type<ProductEventHandler>();

  @Override
  public Type<ProductEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ProductEventHandler handler) {
    handler.onAddProductClicked(this);
  }
}