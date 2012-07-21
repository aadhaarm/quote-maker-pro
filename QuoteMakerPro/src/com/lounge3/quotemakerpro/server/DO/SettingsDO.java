package com.lounge3.quotemakerpro.server.DO;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class SettingsDO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key settingId;
	
	@Persistent
	private boolean suscribed = false;

	@Persistent
	private String screenName;	
	
	public SettingsDO() {
		super();
	}

	public SettingsDO(Key settingId) {
		super();
		this.settingId = settingId;
	}

	public Key getSettingId() {
		return settingId;
	}

	public void setSettingId(Key settingId) {
		this.settingId = settingId;
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