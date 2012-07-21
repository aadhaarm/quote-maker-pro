package com.lounge3.quotemakerpro.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UpdatedCategoryEvent extends GwtEvent<UpdatedCategoryEventHandler>{
  public static Type<UpdatedCategoryEventHandler> TYPE = new Type<UpdatedCategoryEventHandler>();

  @Override
  public Type<UpdatedCategoryEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(UpdatedCategoryEventHandler handler) {
    handler.onFormUpdationClicked(this);
  }
}