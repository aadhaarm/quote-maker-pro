package com.lounge3.quotemakerpro.shared.TO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

public class FormSaveTO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private Long formSaveId;
	
	private String formId;
	
	private String userEmail;
	
	private Timestamp timeOfLastUpdate;
	
	private Map<String, ProductSaveTO> selectedProducts;

	private String state;
	
	@Override
	public String toString() {
		return "FormSaveDO [formSaveId=" + formSaveId + ", formId=" + formId
				+ ", userEmail=" + userEmail + ", timeOfLastUpdate="
				+ timeOfLastUpdate + ", selectedProducts=" + selectedProducts
				+ ", state=" + state + "]";
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getFormSaveId() {
		return formSaveId;
	}

	public void setFormSaveId(Long formSaveId) {
		this.formSaveId = formSaveId;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Timestamp getTimeOfLastUpdate() {
		return timeOfLastUpdate;
	}

	public void setTimeOfLastUpdate(Timestamp timeOfLastUpdate) {
		this.timeOfLastUpdate = timeOfLastUpdate;
	}

	public Map<String, ProductSaveTO> getSelectedProducts() {
		return selectedProducts;
	}

	public void setSelectedProducts(Map<String, ProductSaveTO> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}
}
