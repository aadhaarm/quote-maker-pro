package com.lounge3.quotemakerpro.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.presenter.NSHomePresenter;

public class NSHomeView extends Composite implements NSHomePresenter.Display {

	private Anchor loginLink;

	public void setLoginLink(String loginLink) {
		this.loginLink.setHref(loginLink);
	}

	private final FlexTable contentTable;

	public NSHomeView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
//		contentTableDecorator.setWidth("100%");
//		contentTableDecorator.setWidth("80em");

		contentTable = new FlexTable();
//		contentTable.setWidth("100%");
//		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListContainer");
//		contentTable.getCellFormatter().setWidth(0, 0, "100%");
//		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0, DockPanel.ALIGN_TOP);

		// Create the menu
		HorizontalPanel hPanel = new HorizontalPanel();
//		hPanel.setBorderWidth(0);
//		hPanel.setSpacing(0);
//		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);

		loginLink = new Anchor("Login");
		hPanel.add(loginLink);

//		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListMenu");

		contentTable.setWidget(0, 0, hPanel);

		contentTableDecorator.add(contentTable);
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getLoginLink() {
		return loginLink;
	}
}