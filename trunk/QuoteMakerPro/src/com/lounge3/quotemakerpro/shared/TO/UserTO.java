package com.lounge3.quotemakerpro.shared.TO;

import java.io.Serializable;
import java.util.List;

public class UserTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String userId;
	
	private String userEmail;
	
	private String password;
	
	private List<ElementTO> elements;
	
	private boolean suscribed;

	private String screenName;
	
//	private SettingsTO userSettings;

	public UserTO() {
		super();
	}

	public UserTO(String userId, String userEmail, String password,
			List<ElementTO> elements, SettingsTO userSettings) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.password = password;
		this.elements = elements;
//		this.userSettings = userSettings;
	}

//	public SettingsTO getUserSettings() {
//		return userSettings;
//	}
//
//	public void setUserSettings(SettingsTO userSettings) {
//		this.userSettings = userSettings;
//	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<ElementTO> getElementsTO() {
		return elements;
	}

	public void setElementsTO(List<ElementTO> elements) {
		this.elements = elements;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSuscribed() {
		return suscribed;
	}

	public void setSuscribed(boolean suscribed) {
		this.suscribed = suscribed;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	} 
}