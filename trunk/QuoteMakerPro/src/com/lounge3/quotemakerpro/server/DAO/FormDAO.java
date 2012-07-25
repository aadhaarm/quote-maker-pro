package com.lounge3.quotemakerpro.server.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.lounge3.quotemakerpro.server.DO.ElementDO;
import com.lounge3.quotemakerpro.server.DO.FormDO;
import com.lounge3.quotemakerpro.server.DO.FormSaveDO;
import com.lounge3.quotemakerpro.server.DO.ProdCatAssociationDO;
import com.lounge3.quotemakerpro.server.DO.UserDO;
import com.lounge3.quotemakerpro.server.mapper.FormMapperDOtoTO;
import com.lounge3.quotemakerpro.server.mapper.FormMapperDotoDO;
import com.lounge3.quotemakerpro.server.mapper.FormMapperTOtoDO;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;
import com.lounge3.quotemakerpro.shared.TO.FormCategoryTO;
import com.lounge3.quotemakerpro.shared.TO.FormProductTO;
import com.lounge3.quotemakerpro.shared.TO.FormTO;
import com.lounge3.quotemakerpro.shared.TO.UserTO;

public class FormDAO {

	public static boolean addNewUser(UserTO userTO) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		boolean returnVal = true;
		try {
			pm.makePersistent(FormMapperTOtoDO.getUserDO(userTO));
		} catch(Exception e) {
			returnVal = false;
			e.printStackTrace();
		} finally {
			pm.close();
		}		
		return returnVal;
	}

	public static UserDO getUser(String emailAddress) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserDO userDO = null;
		try {
			Query query = pm.newQuery(UserDO.class);
			query.setFilter("userEmail == emailParam");
			query.declareParameters("String emailParam");
			@SuppressWarnings("unchecked")
			List<UserDO> result = (List<UserDO>)query.execute(emailAddress);

			if(!result.isEmpty()) {
				Iterator<UserDO> it = result.iterator();
				while(it.hasNext()) {
					userDO = it.next();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}			
		return userDO;
	}

	public static boolean createAssociation(long productId, List<String> categories) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		boolean returnValue = true;
		try {

			Iterator<String> it = categories.iterator();
			while(it.hasNext()) {
				String formDO = it.next();
				ProdCatAssociationDO element = new ProdCatAssociationDO(productId, Long.parseLong(formDO));
				pm.makePersistent(element);
			}
		} catch(Exception e) {
			e.printStackTrace();
			returnValue = false;
		} finally {
			pm.close();
		}			
		return returnValue;
	}

	public static boolean updateElement(ElementTO elementTO, String userEmail) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		boolean retValue = false;
		try {
			Query query = pm.newQuery(ElementDO.class);
			query.setFilter("id == elementId");
			query.declareParameters("Long elementId");
			@SuppressWarnings("unchecked")
			List<ElementDO> result = (List<ElementDO>)query.execute(elementTO.getId());


			if(!result.isEmpty()) {
				Iterator<ElementDO> it = result.iterator();
				while(it.hasNext()) {
					ElementDO elementDO = it.next();
					FormMapperDotoDO.ElementDOtoDO(elementDO, FormMapperTOtoDO.getElementDO(elementTO));
					pm.makePersistent(elementDO);
					retValue = true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}			
		return retValue;
	}


	public static long createElement(ElementTO elementTO, String userEmail) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		long elementId = 0;

		if(elementTO != null && elementTO.getId() != null && elementTO.getId() > 0) {
			updateElement(elementTO, userEmail);
		} else {

			try {
				ElementDO element = FormMapperTOtoDO.getElementDO(elementTO);
				element.setUserEmail(userEmail);
				elementId = pm.makePersistent(element).getId();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}		
		}
		return elementId;
	}

	public static boolean createElement(ElementTO elementTO, String userEmail, List<String> categorieIds) {
		long elementId = 0;

		elementId = createElement(elementTO, userEmail);

		return createAssociation(elementId, categorieIds);
	}

	public static boolean editForm(ElementTO elementTO_new) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		boolean returnValue = true;
		try {
			Query query = pm.newQuery(ElementDO.class);
			query.setFilter("name == nameParam");
			query.declareParameters("String nameParam");

			@SuppressWarnings("unchecked")
			List<ElementDO> result = (List<ElementDO>)query.execute(elementTO_new.getName());

			if(!result.isEmpty()) {
				Iterator<ElementDO> it = result.iterator();
				while(it.hasNext()) {
					ElementDO formDO = it.next();
					try {
						FormMapperDotoDO.ElementDOtoDO(formDO, FormMapperTOtoDO.getElementDO(elementTO_new));
						pm.makePersistent(formDO);
					} catch(Exception e) {
						e.printStackTrace();
						returnValue = false;
					}
				}
			} else {
				returnValue = false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			returnValue = false;
		} finally {
			pm.close();
		}			
		return returnValue;
	}

	public static ElementTO getElement(String elementName) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ElementTO formTO = null;
		try {
			Query query = pm.newQuery(ElementDO.class);
			query.setFilter("name == nameParam");
			query.declareParameters("String nameParam");
			@SuppressWarnings("unchecked")
			List<ElementDO> result = (List<ElementDO>)query.execute(elementName);

			if(!result.isEmpty()) {
				Iterator<ElementDO> it = result.iterator();
				while(it.hasNext()) {
					ElementDO elementDO = it.next();
					formTO = FormMapperDOtoTO.getElementTO(elementDO);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}			
		return formTO;
	}

	public static ElementTO getElement(Long elementId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ElementTO formTO = null;
		try {
			Query query = pm.newQuery(ElementDO.class);
			query.setFilter("id == idParam");
			query.declareParameters("String idParam");
			@SuppressWarnings("unchecked")
			List<ElementDO> result = (List<ElementDO>)query.execute(elementId);

			if(!result.isEmpty()) {
				Iterator<ElementDO> it = result.iterator();
				while(it.hasNext()) {
					ElementDO elementDO = it.next();
					formTO = FormMapperDOtoTO.getElementTO(elementDO);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}			
		return formTO;
	}

	public static List<ElementTO> getElements(String userEmail) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<ElementTO> elements = null;
		try {
			Query query = pm.newQuery(UserDO.class);
			query.setFilter("userEmail == emailId");
			query.declareParameters("String emailId");
			@SuppressWarnings("unchecked")
			List<UserDO> result = (List<UserDO>)query.execute(userEmail);

			if(!result.isEmpty()) {
				Iterator<UserDO> it = result.iterator();
				while(it.hasNext()) {
					UserDO userDO = it.next();
					//					elements = FormMapperDOtoTO.getElementsTO(userDO.getElementsDO());
				}
			} else {
				UserTO userTO = new UserTO();
				userTO.setUserEmail(userEmail);
				addNewUser(userTO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}			
		return elements;
	}

	@SuppressWarnings("unchecked")
	public static List<ElementTO> getElements(String userEmail, String elementType) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<ElementTO> elements = null;
		try {
			Query query = pm.newQuery(ElementDO.class);
			query.setFilter("userEmail == emailId && type == elementType");
			query.declareParameters("String emailId" + ", "  + "String elementType");

			elements = FormMapperDOtoTO.getElementsTO((List<ElementDO>)query.execute(userEmail, elementType));

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}			
		return elements;
	}

	@SuppressWarnings("unchecked")
	public static List<ElementTO> getAssociatedCategories(String elementName) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<ElementTO> elements = null;
		try {
			ElementTO productTO = getElement(elementName);

			if(productTO != null) {

				Query query = pm.newQuery(ProdCatAssociationDO.class);
				query.setFilter("productId == elementId");
				query.declareParameters("String elementId");

				List<ProdCatAssociationDO> associations = (List<ProdCatAssociationDO>)query.execute(productTO.getId());

				if(associations != null) {
					elements = new ArrayList<ElementTO>();
					for (ProdCatAssociationDO prodCatAssociationDO : associations) {
						prodCatAssociationDO.getCategoryId();
						query = pm.newQuery(ElementDO.class);
						query.setFilter("id == elementId");
						query.declareParameters("Long elementId");

						elements.addAll(FormMapperDOtoTO.getElementsTO((List<ElementDO>)query.execute(prodCatAssociationDO.getCategoryId())));
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}			
		return elements;
	}

	@SuppressWarnings("unchecked")
	public static List<ElementTO> getAssociatedProducts(String elementName) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<ElementTO> elements = null;
		try {
			ElementTO productTO = getElement(elementName);

			Query query = pm.newQuery(ProdCatAssociationDO.class);
			query.setFilter("categoryId == elementId");
			query.declareParameters("String elementId");

			List<ProdCatAssociationDO> associations = (List<ProdCatAssociationDO>)query.execute(productTO.getId());

			if(associations != null) {
				elements = new ArrayList<ElementTO>();
				for (ProdCatAssociationDO prodCatAssociationDO : associations) {
					prodCatAssociationDO.getCategoryId();
					query = pm.newQuery(ElementDO.class);
					query.setFilter("id == elementId");
					query.declareParameters("Long elementId");

					elements.addAll(FormMapperDOtoTO.getElementsTO((List<ElementDO>)query.execute(prodCatAssociationDO.getProductId())));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}			
		return elements;
	}

	public static long addForm(FormTO formTO, String userEmail) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		long formId = 0;

		if(formTO != null && formTO.getFormId() != null && formTO.getFormId() > 0) {
			updateForm(formTO, userEmail);
		} else {
			try {
				FormDO form = FormMapperTOtoDO.getFormDO(formTO);
				form.setUserEmail(userEmail);
				pm.makePersistent(form);
				formId = form.getFormId();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}		
		}
		return formId;
	}

	public static boolean updateForm(FormTO formTO, String userEmail) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		boolean retValue = false;
		try {
			Query query = pm.newQuery(FormDO.class);
			query.setFilter("formId == elementId");
			query.declareParameters("Long elementId");
			@SuppressWarnings("unchecked")
			List<FormDO> result = (List<FormDO>)query.execute(formTO.getFormId());


			if(!result.isEmpty()) {
				Iterator<FormDO> it = result.iterator();
				while(it.hasNext()) {
					FormDO formDO = it.next();
					FormMapperTOtoDO.copyFormTOtoDO(formDO, formTO);
					pm.makePersistent(formDO);
					retValue = true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}			
		return retValue;
	}

	public static FormTO getForm(String formName, String emailId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		FormTO formTO = null;
		try {
			Query query = pm.newQuery(FormDO.class);
			query.setFilter("userEmail == emailId && name == formName");
			query.declareParameters("String emailId" + ", " + "String formName");

			@SuppressWarnings("unchecked")
			List<FormDO> result = (List<FormDO>)query.execute(emailId, formName);


			if(!result.isEmpty()) {
				Iterator<FormDO> it = result.iterator();
				while(it.hasNext()) {
					FormDO formDO = it.next();
					formTO = FormMapperDOtoTO.getFormTO(formDO);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}			
		return formTO;
	}

	public static List<FormTO> getForms(String emailId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<FormTO> formTOs = null;
		try {
			Query query = pm.newQuery(FormDO.class);
			query.setFilter("userEmail == emailId");
			query.declareParameters("String emailId");

			@SuppressWarnings("unchecked")
			List<FormDO> formDOs = (List<FormDO>)query.execute(emailId);
			formTOs = FormMapperDOtoTO.getFormTOs(formDOs);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}			
		return formTOs;
	}

	public static FormTO getForm(String formName) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		FormTO formTO = null;
		try {
			Query query = pm.newQuery(FormDO.class);
			query.setFilter("name == formName");
			query.declareParameters("String formName");

			@SuppressWarnings("unchecked")
			List<FormDO> formDOs = (List<FormDO>)query.execute(formName);


			if(!formDOs.isEmpty()) {
				Iterator<FormDO> it = formDOs.iterator();
				while(it.hasNext()) {
					FormDO formDO = it.next();
					formTO = FormMapperDOtoTO.getFormTO(formDO);
					//Get category and product details
					fetchElementDetails(formTO);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}			
		return formTO;
	}

	private static void fetchElementDetails(FormTO formTO) {
		List<FormCategoryTO> categories = formTO.getCategories();
		if(categories != null) {
			for (Iterator<FormCategoryTO> iterator = categories.iterator(); iterator.hasNext();) {
				FormCategoryTO formCategoryTO = (FormCategoryTO) iterator.next();
				if(formCategoryTO != null) {
					//Copy category details
					fetchCategoryDetails(formCategoryTO);
					//get Product details
					List<FormProductTO> products = formCategoryTO.getProducts();
					if(products != null) {
						fetchProductDetails(products);
					}
				}
			}
		}
	}

	private static void fetchCategoryDetails(FormCategoryTO formCategoryTO) {
		ElementTO category = getElement(formCategoryTO.getCategoryId());
		formCategoryTO.setName(category.getName());
		formCategoryTO.setTitle(category.getTitle());
		formCategoryTO.setDescription(category.getDescription());
	}

	private static void fetchProductDetails(List<FormProductTO> products) {
		for (Iterator<FormProductTO> iterator = products.iterator(); iterator.hasNext();) {
			FormProductTO formProductTO = (FormProductTO) iterator.next();
			if(formProductTO != null) {
				ElementTO product = getElement(formProductTO.getProductId());
				if(product != null) {
					formProductTO.setName(product.getName());
					formProductTO.setTitle(product.getTitle());
					formProductTO.setDescription(product.getDescription());
					formProductTO.setPrice(product.getPrice());
					
					formProductTO.setElementQuantityType(product.getElementQuantityType());
					formProductTO.setMaxQuantity(product.getMaxQuantity());
					formProductTO.setMinQuantity(product.getMinQuantity());
					formProductTO.setQuantityAlgo(product.getQuantityAlgo());
					formProductTO.setQuantityUnit(product.getQuantityUnit());
					
					formProductTO.setMultiOrMan(product.getMultiOrMan());
				}
			}
		}
	}

	public static long saveForm(FormSaveDO formSaveDO) {
		long formSaveId = 0;

		if(formSaveDO != null) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				formSaveId = pm.makePersistent(formSaveDO).getFormSaveId();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}		
		}
		return formSaveId;
	}

	public static FormSaveDO getSaveForm(Long formSaveId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		FormSaveDO returnValueFormSaveDO = null;

		try {
			while(returnValueFormSaveDO == null || returnValueFormSaveDO.getSelectedProducts() == null) {
				Query query = pm.newQuery(FormSaveDO.class);
				query.setFilter("formSaveId == idParam");
				query.declareParameters("Long idParam");
				@SuppressWarnings("unchecked")
				List<FormSaveDO> result = (List<FormSaveDO>)query.execute(formSaveId);

				if(!result.isEmpty()) {
					Iterator<FormSaveDO> it = result.iterator();
					while(it.hasNext()) {
						FormSaveDO formSaveDO = it.next();
						if(formSaveDO != null && formSaveDO.getSelectedProducts() != null) {
							returnValueFormSaveDO = formSaveDO;
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}			
		return returnValueFormSaveDO;
	}

	public static FormTO getFormByID(Long formId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		FormTO formTO = null;
		try {
			Query query = pm.newQuery(FormDO.class);
			query.setFilter("formId == formIdParam");
			query.declareParameters("Long formIdParam");

			@SuppressWarnings("unchecked")
			List<FormDO> formDOs = (List<FormDO>)query.execute(formId);

			if(!formDOs.isEmpty()) {
				Iterator<FormDO> it = formDOs.iterator();
				while(it.hasNext()) {
					FormDO formDO = it.next();
					formTO = FormMapperDOtoTO.getFormTO(formDO);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}			
		return formTO;
	}
}