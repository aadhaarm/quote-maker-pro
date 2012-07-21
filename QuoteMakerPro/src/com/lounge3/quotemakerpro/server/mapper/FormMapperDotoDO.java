package com.lounge3.quotemakerpro.server.mapper;

import com.lounge3.quotemakerpro.server.DO.ElementDO;

public class FormMapperDotoDO {
	public static boolean ElementDOtoDO(ElementDO elementDO, ElementDO elementDO_new) {
		boolean returnVal = true;
		try {
			if(elementDO != null && elementDO_new != null) {
				elementDO.setName(elementDO_new.getName());
				elementDO.setDescription(elementDO_new.getDescription());
				elementDO.setTitle(elementDO_new.getTitle());
				elementDO.setType(elementDO_new.getType());
				elementDO.setPrice(elementDO_new.getPrice());
			}
		} catch(Exception e) {
			e.printStackTrace();
			returnVal = false;
		}
		return returnVal;
	}
}