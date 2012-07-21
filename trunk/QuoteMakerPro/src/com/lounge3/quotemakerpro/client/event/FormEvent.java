package com.lounge3.quotemakerpro.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class FormEvent extends GwtEvent<FormEventHandler>{
  public static Type<FormEventHandler> TYPE = new Type<FormEventHandler>();

  @Override
  public Type<FormEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(FormEventHandler handler) {
    handler.onAddFormClicked(this);
  }
}