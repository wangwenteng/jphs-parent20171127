package com.jinpaihushi.taglib;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.ConditionalTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.utils.UrlUtils;

public class HasUrlTag extends ConditionalTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2806127862383686111L;

	private Logger logger = Logger.getLogger(this.getClass());

	private String url = "";	
	
	//private String isRight = "";
	
	protected boolean condition() throws JspTagException {
		
		
		HttpSession session = pageContext.getSession();
		if (session == null) {
			logger.debug("未发现session");
			return false;
		}
		SystemUser systemUser =  (SystemUser)session.getAttribute("session_user");
		if(systemUser!=null){
			List<String> urlList = (List<String>) session.getAttribute("session_url");
			if(urlList== null){
				return false;
			}
			if (StringUtils.isNotEmpty(url) ) {
				return UrlUtils.isUrl(urlList, url);
			}
		}
		return false;
		
	}

	

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}



	/*public String getIsRight() {
		return isRight;
	}

	public void setIsRight(String isRight) {
		this.isRight = isRight;
	}*/
	
}
