package com.lounge3.quotemakerpro.shared;

import java.io.Serializable;

public class LoginInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean loggedIn = false;
	private String loginUrl;
	private String logoutUrl;
	private String screenName;
	private String nickname;
	private String emailAddress;

	public LoginInfo(boolean loggedIn, String loginUrl, String logoutUrl,
			String screenName, String nickname) {
		super();
		this.loggedIn = loggedIn;
		this.loginUrl = loginUrl;
		this.logoutUrl = logoutUrl;
		this.screenName = screenName;
		this.nickname = nickname;
	}
	public LoginInfo() {
		// TODO Auto-generated constructor stub
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public String getLogoutUrl() {
		return logoutUrl;
	}
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String emailAddress) {
		this.screenName = emailAddress;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}