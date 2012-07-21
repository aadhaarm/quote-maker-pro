package com.lounge3.quotemakerpro.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddProductEvent extends GwtEvent<AddProductEventHandler>{
  public static Type<AddProductEventHandler> TYPE = new Type<AddProductEventHandler>();

  @Override
  public Type<AddProductEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddProductEventHandler handler) {
    handler.onAddProductClicked(this);
  }
}