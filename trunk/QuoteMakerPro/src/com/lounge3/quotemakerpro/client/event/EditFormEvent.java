package com.lounge3.quotemakerpro.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditFormEvent extends GwtEvent<EditFormEventHandler>{

	public static Type<EditFormEventHandler> TYPE = new Type<EditFormEventHandler>();
	private String formName;

	
	public EditFormEvent() {
		super();
	}

	public EditFormEvent(String formName) {
		this.formName = formName;
	}

	public String getFormName() { return formName; }

	@Override
	public Type<EditFormEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditFormEventHandler handler) {
		handler.onFormRowClicked(this);
	}
}