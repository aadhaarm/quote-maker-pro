package com.lounge3.quotemakerpro.client.widgets;

import com.google.gwt.user.client.ui.TextBox;
import com.lounge3.quotemakerpro.shared.Constants;

public class L3TxtBox extends TextBox {

	public boolean validate(String validateAgainst, String errorMessage) {
		if(this.getText() != null) {
			if (this.getText().matches(validateAgainst)) {
				removeStyleName(Constants.STYLE_TEXTBOX_VALIDATION_ERROR_STYLE);
				setTitle("");
				return true;
			} else {
				addStyleName(Constants.STYLE_TEXTBOX_VALIDATION_ERROR_STYLE);
				setTitle(errorMessage);
				return false;
			}
		}
		return false;
	}
	
	public boolean isEmpty(String errorMessage) {
		if(this.getText() != null) {
			if(this.getText().isEmpty()) {
				addStyleName(Constants.STYLE_TEXTBOX_VALIDATION_ERROR_STYLE);
				setTitle(errorMessage);
				return false;
			} else {
				removeStyleName(Constants.STYLE_TEXTBOX_VALIDATION_ERROR_STYLE);
				setTitle("");
				return true;
			}
		}
		return false;
	}
}