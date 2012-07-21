package com.lounge3.quotemakerpro.server.DO;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class UserDO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key userId;
	
	@Persistent
	private String userEmail;
	
	@Persistent	
	private String password;
	
//	@Persistent(mappedBy = "userEmail")
//	private List<ElementDO> elements;

//	@Persistent
//	private SettingsDO userSettings;

	@Persistent
	private boolean suscribed = false;

	@Persistent
	private String screenName;	
	
	public UserDO() {
		super();
	}
	
	public UserDO(Key userId, String userEmail, String password,
			List<ElementDO> elements, SettingsDO userSettings) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.password = password;
//		this.elements = elements;
	}

//	public SettingsDO getUserSettings() {
//		return userSettings;
//	}
//
//	public void setUserSettings(SettingsDO userSettings) {
//		this.userSettings = userSettings;
//	}

	public Key getUserId() {
		return userId;
	}

	public void setUserId(Key userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

//	public List<ElementDO> getElementsDO() {
//		return elements;
//	}
//
//	public void setElements(List<ElementDO> elements) {
//		this.elements = elements;
//	}

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