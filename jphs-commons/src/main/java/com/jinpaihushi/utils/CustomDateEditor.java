package com.jinpaihushi.utils;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.springframework.util.StringUtils;

public class CustomDateEditor extends PropertyEditorSupport{
	
	private boolean allowEmpty;
	
	public CustomDateEditor(){
		this(true);
	}

	public CustomDateEditor(boolean allowEmpty){
		this.allowEmpty = allowEmpty;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		}else{
			super.setValue(DateUtils.parse(text));
		}
	}

	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? DateUtils.format(value) : "");
	}

}
