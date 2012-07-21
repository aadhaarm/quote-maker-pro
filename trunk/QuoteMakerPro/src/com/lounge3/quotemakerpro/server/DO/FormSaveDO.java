package com.lounge3.quotemakerpro.server.DO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class FormSaveDO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long formSaveId;
	
	@Persistent
	private String formId;
	
	@Persistent
	private String userEmail;
	
	@Persistent
	private Timestamp timeOfLastUpdate;
	
	@Persistent(defaultFetchGroup = "true")
	private List<ProductSaveDO> selectedProducts;

	@Persistent
	private String state;
	
	public FormSaveDO() {
		super();
	}

	public FormSaveDO(String formId, String userEmail,
			Timestamp timeOfLastUpdate,
			List<ProductSaveDO> selectedProducts, String state) {
		super();
		this.formId = formId;
		this.userEmail = userEmail;
		this.timeOfLastUpdate = timeOfLastUpdate;
		this.selectedProducts = selectedProducts;
		this.state = state;
	}

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

	public List<ProductSaveDO> getSelectedProducts() {
		return selectedProducts;
	}

	public void setSelectedProducts(List<ProductSaveDO> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}
}
