package com.lounge3.quotemakerpro.server.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lounge3.quotemakerpro.server.DO.ElementDO;
import com.lounge3.quotemakerpro.server.DO.FormCategoryDO;
import com.lounge3.quotemakerpro.server.DO.FormDO;
import com.lounge3.quotemakerpro.server.DO.FormProductDO;
import com.lounge3.quotemakerpro.server.DO.FormSaveDO;
import com.lounge3.quotemakerpro.server.DO.ProductSaveDO;
import com.lounge3.quotemakerpro.server.DO.SettingsDO;
import com.lounge3.quotemakerpro.server.DO.UserDO;
import com.lounge3.quotemakerpro.shared.TO.ElementTO;
import com.lounge3.quotemakerpro.shared.TO.FormCategoryTO;
import com.lounge3.quotemakerpro.shared.TO.FormProductTO;
import com.lounge3.quotemakerpro.shared.TO.FormSaveTO;
import com.lounge3.quotemakerpro.shared.TO.FormTO;
import com.lounge3.quotemakerpro.shared.TO.ProductSaveTO;
import com.lounge3.quotemakerpro.shared.TO.SettingsTO;
import com.lounge3.quotemakerpro.shared.TO.UserTO;

public class FormMapperTOtoDO {

	public static ElementDO getElementDO(ElementTO elementTO) {
		ElementDO elementDO = null;
		if (elementTO != null) {
			elementDO = new ElementDO();
			elementDO.setName(elementTO.getName());
			elementDO.setTitle(elementTO.getTitle());
			elementDO.setDescription(elementTO.getDescription());
			elementDO.setType(elementTO.getType());
			elementDO.setPrice(elementTO.getPrice());
			
			elementDO.setElementQuantityType(elementTO.getElementQuantityType());
			elementDO.setMaxQuantity(elementTO.getMaxQuantity());
			elementDO.setMinQuantity(elementTO.getMinQuantity());
			elementDO.setQuantityAlgo(elementTO.getQuantityAlgo());
			elementDO.setQuantityUnit(elementTO.getQuantityUnit());
			elementDO.setMultiOrMan(elementTO.getMultiOrMan());
		}
		return elementDO;
	}

	public static List<ElementDO> getElementsDO(List<ElementTO> formTOs) {
		List<ElementDO> formDOs = null;
		if (formTOs != null) {
			formDOs = new ArrayList<ElementDO>();
			Iterator<ElementTO> it = formTOs.iterator();
			while (it.hasNext()) {
				formDOs.add(getElementDO(it.next()));
			}
		}
		return formDOs;
	}

	public static UserDO getUserDO(UserTO userTO) {
		UserDO userDO = null;
		if (userTO != null) {
			userDO = new UserDO();
			userDO.setUserEmail(userTO.getUserEmail());
			userDO.setPassword(userTO.getPassword());
			userDO.setUserEmail(userTO.getUserEmail());
			userDO.setUserEmail(userTO.getUserEmail());
			userDO.setScreenName(userTO.getScreenName());
			userDO.setSuscribed(userTO.isSuscribed());
//			userDO.setUserSettings(getSettingsDO(userTO.getUserSettings()));
//			userDO.setElements(getElementsDO(userTO.getElementsTO()));
		}
		return userDO;
	}

	public static SettingsDO getSettingsDO(SettingsTO settingsTO) {
		SettingsDO settingsDO = null;
		if (settingsTO != null) {
			settingsDO = new SettingsDO();
			settingsDO.setScreenName(settingsTO.getScreenName());
			settingsDO.setSuscribed(settingsTO.isSuscribed());
		}
		return settingsDO;
	}

	public static FormDO getFormDO(FormTO formTO) {
		FormDO formDO = null;
		if (formTO != null) {
			formDO = new FormDO();
			formDO.setName(formTO.getName());
			formDO.setTitle(formTO.getTitle());
			formDO.setDescription(formTO.getDescription());
			formDO.setCategories(getFormCategoryDO(formTO.getCategories()));
		}
		return formDO;
	}

	public static List<FormCategoryDO> getFormCategoryDO(
			List<FormCategoryTO> categoryTOs) {
		List<FormCategoryDO> categoryDOs = null;
		if (categoryTOs != null) {
			categoryDOs = new ArrayList<FormCategoryDO>();
			for (Iterator<FormCategoryTO> iterator = categoryTOs.iterator(); iterator
					.hasNext();) {
				FormCategoryTO categoryTO = (FormCategoryTO) iterator.next();
				categoryDOs.add(getFormCategoryDO(categoryTO));
			}
		}
		return categoryDOs;
	}

	public static FormCategoryDO getFormCategoryDO(FormCategoryTO categoryTO) {
		FormCategoryDO categoryDO = null;
		if (categoryTO != null) {
			categoryDO = new FormCategoryDO();
			categoryDO.setCategoryId(categoryTO.getCategoryId());
			categoryDO.setProducts(getFormProductDO(categoryTO.getProducts()));
		}
		return categoryDO;
	}

	public static List<FormProductDO> getFormProductDO(
			List<FormProductTO> productTOs) {
		List<FormProductDO> productDOs = null;
		if (productTOs != null) {
			productDOs = new ArrayList<FormProductDO>();
			for (Iterator<FormProductTO> iterator = productTOs.iterator(); iterator
					.hasNext();) {
				FormProductTO productTO = (FormProductTO) iterator.next();
				productDOs.add(getFormProductDO(productTO));
			}
		}
		return productDOs;
	}

	public static FormProductDO getFormProductDO(FormProductTO productTO) {
		FormProductDO productDO = null;
		if (productTO != null) {
			productDO = new FormProductDO();
			productDO.setProductId(productTO.getProductId());
		}
		return productDO;
	}

	public static FormDO copyFormTOtoDO(FormDO formDO, FormTO formTO) {
		if (formDO != null && formTO != null) {
			formDO.setName(formTO.getName());
			formDO.setTitle(formTO.getTitle());
			formDO.setDescription(formTO.getDescription());
			formDO.setCategories(getFormCategoryDO(formTO.getCategories()));
		}
		return formDO;
	}

	public static FormSaveDO getFormSaveDO(FormSaveTO formSaveTO) {
		FormSaveDO formSaveDO = null;
		if (formSaveTO != null) {
			formSaveDO = new FormSaveDO(formSaveTO.getFormId(),
					formSaveTO.getUserEmail(),
					formSaveTO.getTimeOfLastUpdate(),
					FormMapperTOtoDO.getSelectedProductsDO(formSaveTO.getSelectedProducts()), 
					formSaveTO.getState());
		}
		return formSaveDO;
	}

	private static List<ProductSaveDO> getSelectedProductsDO(Map<String, ProductSaveTO> selectedProductsTO) {
		List<ProductSaveDO> selectedProductsDO = null;
		if(selectedProductsTO != null) {
			selectedProductsDO = new ArrayList<ProductSaveDO>();
			for (Map.Entry<String, ProductSaveTO> selectedProductEntry : selectedProductsTO.entrySet()) {
				selectedProductsDO.add(getSelectedProductDO(selectedProductEntry.getValue()));
			}
		}
		return selectedProductsDO;
	}

	public static ProductSaveDO getSelectedProductDO(ProductSaveTO selectedProductTO) {
		ProductSaveDO selectedproductDO = null;
		if(selectedProductTO != null) {
			selectedproductDO = new ProductSaveDO();
			selectedproductDO.setProductId(selectedProductTO.getProductId());
			selectedproductDO.setQuotedPrice(selectedProductTO.getQuotedPrice());
		}
		return selectedproductDO;
	}
}