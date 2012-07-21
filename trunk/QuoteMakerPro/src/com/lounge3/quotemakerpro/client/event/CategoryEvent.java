package com.lounge3.quotemakerpro.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class CategoryEvent extends GwtEvent<CategoryEventHandler>{
  public static Type<CategoryEventHandler> TYPE = new Type<CategoryEventHandler>();

  @Override
  public Type<CategoryEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CategoryEventHandler handler) {
    handler.onAddFormClicked(this);
  }
}