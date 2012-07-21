package com.lounge3.quotemakerpro.shared.TO;

import java.io.Serializable;

public class SettingsTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String settingId;

	private boolean suscribed;

	private String screenName;
	
	public SettingsTO() {
		super();
	}

	public SettingsTO(String settingId) {
		super();
		this.settingId = settingId;
	}

	public String getSettingId() {
		return settingId;
	}

	public void setSettingId(String settingId) {
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