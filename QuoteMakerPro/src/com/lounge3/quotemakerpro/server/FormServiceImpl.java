package com.lounge3.quotemakerpro.server;

import java.util.List;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.lounge3.quotemakerpro.client.proxy.FormService;
import com.lounge3.quotemakerpro.server.DAO.FormDAO;
import com.lounge3.quotemakerpro.server.DO.UserDO;
import com.lounge3.quotemakerpro.server.mapper.FormMapperDOtoTO;
import com.lounge3.quotemakerpro.server.mapper.FormMapperTOtoDO;
import com.lounge3.quotemakerpro.shared.LoginInfo;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;
import com.lounge3.quotemakerpro.shared.TO.FormSaveTO;
import com.lounge3.quotemakerpro.shared.TO.FormTO;
import com.lounge3.quotemakerpro.shared.TO.UserTO;

public class FormServiceImpl extends RemoteServiceServlet implements FormService {

	private static final long serialVersionUID = 1L;

	@Override
	public void addNewElement(ElementTO elementTO, String userEmail) {
		FormDAO.createElement(elementTO, userEmail);
	}

	@Override
	public void addNewElement(ElementTO formSummary, String userEmail,
			List<String> categoryIDs) {
		FormDAO.createElement(formSummary, userEmail, categoryIDs);
	}

	@Override
	public List<ElementTO> getElements(String emailId) {
		return FormDAO.getElements(emailId);
	}

	@Override
	public List<ElementTO> getElements(String emailId, String elementType) {
		return FormDAO.getElements(emailId, elementType);
	}

	@Override
	public void addNewUser(UserTO userTO) {
		FormDAO.addNewUser(userTO);
	}

	@Override
	public LoginInfo login(String requestUri) {
		UserService userService = UserServiceFactory.getUserService();
		LoginInfo loginInfo = new LoginInfo();
		if(userService != null) {
			User user = userService.getCurrentUser();

			if (user != null) {
				loginInfo.setLoggedIn(true);
				loginInfo.setEmailAddress(user.getEmail());
				loginInfo.setNickname(user.getNickname());
				
				UserDO userDO = FormDAO.getUser(user.getEmail());
				if(userDO != null) {
					loginInfo.setScreenName(userDO.getScreenName());
				}
			} else {
				loginInfo.setLoggedIn(false);
			}
			loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
			loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
		}
		return loginInfo;
	}

	@Override
	public FormTO getForm(String formName) {
		return FormDAO.getForm(formName);
	}

	@Override
	public List<ElementTO> getAssociatedCategories(String elementId) {
		return FormDAO.getAssociatedCategories(elementId);
	}

	@Override
	public ElementTO getElement(String elementName) {
		return FormDAO.getElement(elementName);
	}

	@Override
	public List<ElementTO> getAssociatedProducts(String categoryName) {
		return FormDAO.getAssociatedProducts(categoryName);
	}

	@Override
	public FormTO getForm(String formName, String emailId) {
		return FormDAO.getForm(formName, emailId);
	}

	@Override
	public void createForm(FormTO formTO, String emailId) {
		FormDAO.addForm(formTO, emailId);	
	}

	@Override
	public List<FormTO> getforms(String emailId) {
		return FormDAO.getForms(emailId);
	}

	@Override
	public boolean saveForm(FormSaveTO formSaveTO) {
		Long formSaveId = FormDAO.saveForm(FormMapperTOtoDO.getFormSaveDO(formSaveTO));
		if(formSaveId != null) {
			FormTO formTO = FormDAO.getFormByID(Long.parseLong(formSaveTO.getFormId()));
			if(formTO != null) {
				MailManager.sendFormSaveEmail(formSaveTO.getUserEmail(), formSaveId, formTO.getName());
			}
			return true;
		}
		return false;
	}

	@Override
	public FormSaveTO getSaveForm(String formSaveId) {
		return FormMapperDOtoTO.getFormSaveTO(FormDAO.getSaveForm(Long.parseLong(formSaveId)));
	}


	@Override
 	public boolean suscribeUser(UserTO userTO) {
		return FormDAO.addNewUser(userTO);
	}

	@Override
	public UserTO getUser(String emailAddress) {
		return FormMapperDOtoTO.getUserTO(FormDAO.getUser(emailAddress));
	}
}