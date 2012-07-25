package com.lounge3.quotemakerpro.server.mapper;

import java.util.ArrayList;
import java.util.HashMap;
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

public class FormMapperDOtoTO {

	public static ElementTO getElementTO(ElementDO elementDO) {
		ElementTO elementTO = null;
		if (elementDO != null) {
			elementTO = new ElementTO();
			elementTO.setId(elementDO.getId());
			elementTO.setName(elementDO.getName());
			elementTO.setTitle(elementDO.getTitle());
			elementTO.setDescription(elementDO.getDescription());
			elementTO.setType(elementDO.getType());
			elementTO.setPrice(elementDO.getPrice());
				
			elementTO.setElementQuantityType(elementDO.getElementQuantityType());
			if(elementDO.getMaxQuantity() != null) {
				elementTO.setMaxQuantity(elementDO.getMaxQuantity());
			}
			if(elementDO.getMinQuantity() != null) {
				elementTO.setMinQuantity(elementDO.getMinQuantity());
			}
			elementTO.setQuantityAlgo(elementDO.getQuantityAlgo());
			elementTO.setQuantityUnit(elementDO.getQuantityUnit());		
			elementTO.setMultiOrMan(elementDO.getMultiOrMan());		
		}
		return elementTO;
	}

	public static List<ElementTO> getElementsTO(List<ElementDO> elementsDO) {
		List<ElementTO> elements = null;
		if (elementsDO != null) {
			elements = new ArrayList<ElementTO>();

			ElementTO elementTO = null;

			Iterator<ElementDO> iterator = elementsDO.iterator();
			while (iterator.hasNext()) {
				elementTO = new ElementTO();
				elementTO = getElementTO((ElementDO) iterator.next());
				elements.add(elementTO);
			}
		}
		return elements;
	}

	public static List<ElementTO> getElementsTO(List<ElementDO> elementsDO,
			String elementType) {
		List<ElementTO> elements = null;
		if (elementsDO != null) {
			elements = new ArrayList<ElementTO>();

			ElementTO elementTO = null;

			Iterator<ElementDO> iterator = elementsDO.iterator();
			while (iterator.hasNext()) {
				elementTO = new ElementTO();
				elementTO = getElementTO((ElementDO) iterator.next());
				if (elementTO != null && elementTO.getType() != null
						&& elementTO.getType().equalsIgnoreCase(elementType)) {
					elements.add(elementTO);
				}
			}
		}
		return elements;
	}

	public static UserTO getUserTO(UserDO userDO) {
		UserTO userTO = null;
		if (userDO != null) {
			userTO = new UserTO();
			userTO.setUserId(userDO.getUserId().toString());
			userTO.setUserEmail(userDO.getUserEmail());
			userTO.setPassword(userDO.getPassword());
			userTO.setScreenName(userDO.getScreenName());
			userTO.setSuscribed(userDO.isSuscribed());
//			userTO.setUserSettings(getSettingsTO(userDO.getUserSettings()));
//			userTO.setElementsTO(getElementsTO(userDO.getElementsDO()));
		}
		return userTO;
	}

	public static SettingsTO getSettingsTO(SettingsDO settingsDO) {
		SettingsTO settingsTO = null;
		if (settingsDO != null) {
			settingsTO = new SettingsTO();
			settingsTO.setSettingId(settingsDO.getSettingId().toString());
			settingsTO.setScreenName(settingsDO.getScreenName());
			settingsTO.setSuscribed(settingsDO.isSuscribed());
		}
		return settingsTO;
	}

	public static List<FormTO> getFormTOs(List<FormDO> formDOs) {
		List<FormTO> formTOs = null;
		if(formDOs != null) {
			formTOs = new ArrayList<FormTO>();
			for (Iterator<FormDO> iterator = formDOs.iterator(); iterator.hasNext();) {
				FormDO formDO = (FormDO) iterator.next();
				FormTO formTO = getFormTO(formDO);
				formTOs.add(formTO);
			}
		}
		return formTOs;
	}

	public static FormTO getFormTO(FormDO formDO) {
		FormTO formTO = null;
		if (formDO != null) {
			formTO = new FormTO();
			formTO.setFormId(formDO.getFormId());
			formTO.setName(formDO.getName());
			formTO.setTitle(formDO.getTitle());
			formTO.setUserEmail(formDO.getUserEmail());
			formTO.setDescription(formDO.getDescription());
			formTO.setCategories(getFormCategoryTOs(formDO.getCategories()));
		}
		return formTO;
	}

	public static List<FormCategoryTO> getFormCategoryTOs(
			List<FormCategoryDO> formCategoryDOs) {
		List<FormCategoryTO> formCategoryTOs = null;
		if (formCategoryDOs != null) {
			formCategoryTOs = new ArrayList<FormCategoryTO>();
			for (Iterator<FormCategoryDO> iterator = formCategoryDOs.iterator(); iterator
					.hasNext();) {
				FormCategoryDO formCategoryDO = (FormCategoryDO) iterator
						.next();
				FormCategoryTO formCategoryTO = new FormCategoryTO();
				formCategoryTO = getFormCategoryTO(formCategoryDO);
				formCategoryTOs.add(formCategoryTO);

			}
		}
		return formCategoryTOs;
	}

	public static FormCategoryTO getFormCategoryTO(FormCategoryDO formCategoryDO) {
		FormCategoryTO formCategoryTO = null;
		if (formCategoryDO != null) {
			formCategoryTO = new FormCategoryTO();
			formCategoryTO.setCategoryId(formCategoryDO.getCategoryId());
			formCategoryTO.setProducts(getFormProductTOs(formCategoryDO
					.getProducts()));
		}
		return formCategoryTO;
	}

	public static List<FormProductTO> getFormProductTOs(
			List<FormProductDO> formProductDOs) {

		List<FormProductTO> formProductTOs = null;
		if (formProductDOs != null) {
			formProductTOs = new ArrayList<FormProductTO>();
			for (Iterator<FormProductDO> iterator = formProductDOs.iterator(); iterator
					.hasNext();) {
				FormProductDO formProductDO = (FormProductDO) iterator.next();
				FormProductTO formProductTO = new FormProductTO();
				formProductTO = getFormProductTO(formProductDO);
				formProductTOs.add(formProductTO);
			}
		}
		return formProductTOs;
	}

	public static FormProductTO getFormProductTO(FormProductDO formProductDO) {
		FormProductTO formProductTO = null;
		if (formProductDO != null) {
			formProductTO = new FormProductTO();
			formProductTO.setProductId(formProductDO.getProductId());
		}
		return formProductTO;
	}

	public static FormSaveTO getFormSaveTO(FormSaveDO saveForm) {
		FormSaveTO formSaveTO = null;
		if(saveForm != null) {
			formSaveTO = new FormSaveTO();
			formSaveTO.setFormId(saveForm.getFormId());
			formSaveTO.setState(saveForm.getState());
			formSaveTO.setSelectedProducts(FormMapperDOtoTO.getSelectedProductsMapTO(saveForm.getSelectedProducts()));
			formSaveTO.setTimeOfLastUpdate(saveForm.getTimeOfLastUpdate());
			formSaveTO.setFormId(saveForm.getFormId());

		}
		return formSaveTO;
	}

	private static Map<String, ProductSaveTO> getSelectedProductsMapTO(List<ProductSaveDO> selectedProducts) {
		Map<String, ProductSaveTO> selectedProductsMapTO = null;
		if(selectedProducts != null) {
			selectedProductsMapTO =  new HashMap<String, ProductSaveTO>();
			for (Iterator<ProductSaveDO> iterator = selectedProducts.iterator(); iterator.hasNext();) {
				ProductSaveDO productSaveDO = (ProductSaveDO) iterator.next();
				selectedProductsMapTO.put(productSaveDO.getProductId().toString(), getProductSaveTO(productSaveDO));
			}
			
		}
		return selectedProductsMapTO;
	}

	private static ProductSaveTO getProductSaveTO(ProductSaveDO productSaveDO) {
		ProductSaveTO productSaveTO = null;
		if(productSaveDO != null) {
			productSaveTO = new ProductSaveTO();
			productSaveTO.setProductId(productSaveDO.getProductId());
			productSaveTO.setQuotedPrice(productSaveDO.getQuotedPrice());
		}
		return productSaveTO;
	}
}