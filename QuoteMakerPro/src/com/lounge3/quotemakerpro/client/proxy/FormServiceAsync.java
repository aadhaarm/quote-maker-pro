package com.lounge3.quotemakerpro.client.proxy;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.lounge3.quotemakerpro.shared.LoginInfo;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;
import com.lounge3.quotemakerpro.shared.TO.FormSaveTO;
import com.lounge3.quotemakerpro.shared.TO.FormTO;
import com.lounge3.quotemakerpro.shared.TO.UserTO;

public interface FormServiceAsync {
	void login(String requestUri, AsyncCallback<LoginInfo> callback);

	void addNewElement(ElementTO formSummary, String userEmail, AsyncCallback<Void> callback);

	void addNewElement(ElementTO formSummary, String userEmail, List<String> categoryIDs, AsyncCallback<Void> callback);
	
	void getElements(String emailId, AsyncCallback<List<ElementTO>> callback);

	void getElements(String emailId, String elementType, AsyncCallback<List<ElementTO>> callback);

	void  getElement(String elementName, AsyncCallback<ElementTO> callback);
	
	void addNewUser(UserTO userTO, AsyncCallback<Void> callback);

	void getForm(String formName, AsyncCallback<FormTO> callback);

	void getAssociatedCategories(String productName, AsyncCallback<List<ElementTO>> callback);

	void getAssociatedProducts(String categoryName, AsyncCallback<List<ElementTO>> callback);

	void getForm(String formName, String emailId, AsyncCallback<FormTO> callback);

	void createForm(FormTO formTO, String emailId, AsyncCallback<Void> callback);

	void getforms(String emailId, AsyncCallback<List<FormTO>> callback);

	void saveForm(FormSaveTO formSaveTO, AsyncCallback<Boolean> callback);

	void getSaveForm(String formSaveId, AsyncCallback<FormSaveTO> callback);

	void suscribeUser(UserTO userTO, AsyncCallback<Boolean> callback);

	void getUser(String emailAddress, AsyncCallback<UserTO> callback);
}