package com.lounge3.quotemakerpro.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.presenter.StatusBarPresenter;

public class StatusBarView extends Composite implements StatusBarPresenter.Display {

	private Anchor ancLoginLink;
	private String strLoginLink;
	private boolean boolLoginStatus;
	private Label lblUserName;	

	public StatusBarView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);

		// LoginUser & Logout
		HorizontalPanel statusHPanel = new HorizontalPanel();
		lblUserName = new Label("User name");
		statusHPanel.add(lblUserName);
		ancLoginLink = new Anchor("Logout");
		ancLoginLink.setHref(strLoginLink);
		statusHPanel.add(ancLoginLink);
		
		contentTableDecorator.add(statusHPanel);
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getLoginLink() {
		return ancLoginLink;
	}

	@Override
	public void setLoginLink(String strLoginLink) {
		
		this.strLoginLink = strLoginLink;
		
		ancLoginLink.setHref(strLoginLink);

		if(boolLoginStatus) {
			ancLoginLink.setText("Logout");
		} else {
			ancLoginLink.setText("Login");
		}
	}

	@Override
	public void setUserName(String userName) {
		this.lblUserName.setText(userName);
	}

	@Override
	public void setIsLoggedIn(boolean loginStatus) {
		this.boolLoginStatus = loginStatus;
	}
}