package com.jinpaihushi.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("serial")
public class PageInfos<T> extends com.github.pagehelper.PageInfo<T> {

//	private static Pattern PAGE_PATTERN = Pattern.compile("\\bp=\\d*");

	private String url;

	public PageInfos(List<T> list, HttpServletRequest request) {
		super(list);
		setUrl(request);
	}

	public PageInfos(List<T> list, HttpServletRequest request, int navigatePages) {
		super(list, navigatePages);
		setUrl(request);
	}

	private void setUrl(HttpServletRequest request) {
		// 把带参的URL提取出来，如果存在p参数就把p参数替换成p={p},否则加上p={p}
		/*if (StringUtils.isEmpty(request.getQueryString())) {
			url = request.getRequestURL().append("?p={p}").toString();
		} else {
			Matcher m = PAGE_PATTERN.matcher(request.getQueryString());
			StringBuffer sb = request.getRequestURL().append('?');
			boolean find = false;
			while (m.find()) {
				find = true;
				m.appendReplacement(sb, "p={p}");
			}
			if (find) {
				m.appendTail(sb);
			} else {
				sb.append(request.getQueryString()).append("&p={p}");
			}
			url = sb.toString();
		}*/
		url = request.getRequestURL().toString();
	}

	public String getUrl() {
		return url;
	}

}
