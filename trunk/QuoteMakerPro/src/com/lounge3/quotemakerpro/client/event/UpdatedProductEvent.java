package com.lounge3.quotemakerpro.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UpdatedProductEvent extends GwtEvent<UpdatedProductEventHandler>{
  public static Type<UpdatedProductEventHandler> TYPE = new Type<UpdatedProductEventHandler>();

  @Override
  public Type<UpdatedProductEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(UpdatedProductEventHandler handler) {
    handler.onProductUpdate(this);
  }
}