package com.jinpaihushi.utils;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

public class CustomStringEditor extends PropertyEditorSupport {

	private boolean allowEmpty;

	public CustomStringEditor() {
		this(true);
	}

	public CustomStringEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			setValue(null);
		} else {
			super.setValue(xssEncode(text));
		}
	}

	private String xssEncode(String text) {
		if (StringUtils.isEmpty(text)) {
			return text;
		}
		StringBuilder sb = new StringBuilder(text.length() + 16);
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			switch (c) {
			case '>':
				sb.append('＞');// 全角大于号
				break;
			case '<':
				sb.append('＜');// 全角小于号
				break;
			/*case '\'':
				sb.append('‘');// 全角单引号
				break;
			case '\"':
				sb.append('“');// 全角双引号
				break;*/
			case '&':
				sb.append('＆');// 全角
				break;
			case '\\':
				sb.append('＼');// 全角斜线
				break;
			case '#':
				sb.append('＃');// 全角井号
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	@Override
	public String getAsText() {
		String value = (String) getValue();
		return (value != null ? xssEncode(value) : "");
	}

}
