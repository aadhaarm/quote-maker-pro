package com.lounge3.quotemakerpro.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditEvent extends GwtEvent<EditEventHandler>{
	public static Type<EditEventHandler> TYPE = new Type<EditEventHandler>();
	private final String formName;

	public EditEvent(String formName) {
		this.formName = formName;
	}

	public String getFormName() { return formName; }

	@Override
	public Type<EditEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditEventHandler handler) {
		handler.onFormRowClicked(this);
	}
}