package com.lounge3.quotemakerpro.client.view;

import java.util.Iterator;
import java.util.List;

import sun.awt.HorizBagLayout;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.lounge3.quotemakerpro.client.presenter.FormPresenter;
import com.lounge3.quotemakerpro.shared.TO.FormTO;

public class FormView extends Composite implements FormPresenter.Display {

	private final FlexTable formTable;
	private VerticalPanel contentPanel;

	public FormView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);

		formTable = new FlexTable();
		formTable.setWidget(0, 0, new Label("Form-Name"));
		formTable.setWidget(0, 1, new Label("Title"));
		formTable.setWidget(0, 2, new Label("Description"));

		contentPanel = new VerticalPanel();
		contentPanel.add(formTable);

		contentTableDecorator.add(contentPanel);
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public void populateForms(List<FormTO> formTOs) {
		if(formTOs != null){
			int index = 1;
			for (Iterator<FormTO> iterator = formTOs.iterator(); iterator.hasNext();) {
				FormTO formTO = (FormTO) iterator.next();
				Anchor formLink = new Anchor(formTO.getName());
				formLink.setHref("#" + formTO.getName());
				formTable.setWidget(index, 0, formLink);
				formTable.setWidget(index, 1, new Label(formTO.getTitle()));
				formTable.setWidget(index, 2, new Label(formTO.getDescription()));
				index++;
			}
		}
	}
}