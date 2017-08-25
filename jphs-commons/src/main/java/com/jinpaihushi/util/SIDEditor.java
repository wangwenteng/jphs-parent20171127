package com.jinpaihushi.util;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.jinpaihushi.model.SID;

public class SIDEditor extends PropertyEditorSupport {

	private boolean allowEmpty;

	public SIDEditor() {
		this(true);
	}

	public SIDEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		} else {
			super.setValue(new SID(text));
		}
	}

	@Override
	public String getAsText() {
		SID sid = (SID) getValue();
		return (sid != null ? sid.getValue() : "");
	}

}
