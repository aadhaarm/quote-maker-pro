package com.lounge3.quotemakerpro.client.processor;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.lounge3.quotemakerpro.client.util.ClientUtils;
import com.lounge3.quotemakerpro.shared.TO.FormProductTO;
import com.lounge3.quotemakerpro.shared.TO.ProductSaveTO;

public class FormProcessor {

	Map<String, ProductSaveTO> selectedProducts;
	double total;
	
	public FormProcessor(Map<String, ProductSaveTO> selectedProducts) {
		super();
		this.selectedProducts = selectedProducts;
		total = 0;
	}
	
	public void addProduct(FormProductTO formProductTO, Long quantity) {
		selectedProducts.put(formProductTO.getProductId().toString(), ClientUtils.getProductSaveTO(formProductTO, quantity));
	}
	
	public void removeProduct(FormProductTO formProductTO) {
		selectedProducts.remove(Long.toString(formProductTO.getProductId()));
	}
	
	public void updateProductQuantity(FormProductTO formProductTO, Long quantity) {
		selectedProducts.remove(formProductTO.getProductId());
		selectedProducts.put(formProductTO.getProductId().toString(), ClientUtils.getProductSaveTO(formProductTO, quantity));
	}
	
	public double updateTotal() {
		double total = 0;
		Collection<ProductSaveTO> a =  selectedProducts.values();
		for (Iterator<ProductSaveTO> iterator = a.iterator(); iterator.hasNext();) {
			ProductSaveTO productSaveTO = (ProductSaveTO) iterator.next();
			total += productSaveTO.getQuotedPrice()*productSaveTO.getQuantity();
		}
		return total;
	}
	
	public boolean isSelected(FormProductTO formProductTO) {
		if(selectedProducts.containsKey(formProductTO.getProductId().toString())) {
			return true;
		} else {
			return false;
		}
	}
}