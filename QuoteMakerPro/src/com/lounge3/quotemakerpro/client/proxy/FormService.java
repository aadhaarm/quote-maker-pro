package com.lounge3.quotemakerpro.client.proxy;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.lounge3.quotemakerpro.shared.LoginInfo;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;
import com.lounge3.quotemakerpro.shared.TO.FormSaveTO;
import com.lounge3.quotemakerpro.shared.TO.FormTO;
import com.lounge3.quotemakerpro.shared.TO.UserTO;

@RemoteServiceRelativePath("form")
public interface FormService extends RemoteService{
	
	public void addNewElement(ElementTO elementTO, String userEmail);

	public void addNewElement(ElementTO elementTO, String userEmail, List<String> categoryIDs);
	
	public List<ElementTO> getElements(String emailId);
	
	public List<ElementTO> getElements(String emailId, String elementType);

	public ElementTO getElement(String elementName);
	
	public FormTO getForm(String formName);
	
	public LoginInfo login(String requestUri);
	
	public void addNewUser(UserTO userTO);

	public List<ElementTO> getAssociatedCategories(String productName);

	public List<ElementTO> getAssociatedProducts(String categoryName);

	public FormTO getForm(String formName, String emailId);
	
	public void createForm(FormTO formTO, String emailId);
	
	public List<FormTO> getforms(String emailId);

	public boolean saveForm(FormSaveTO formSaveTO);

	public FormSaveTO getSaveForm(String formSaveId);

	public boolean suscribeUser(UserTO userTO);

	public UserTO getUser(String emailAddress);
}