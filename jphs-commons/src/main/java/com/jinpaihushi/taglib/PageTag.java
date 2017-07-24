package com.jinpaihushi.taglib;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.velocity.VelocityContext;

import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.velocity.VelocityTemplatePlugin;

@SuppressWarnings("serial")
public class PageTag extends TagSupport {

	private PageInfos<?> pageInfos;

	private String vmType = "page";

	@Override
	public int doEndTag() throws JspException {
		if (pageInfos == null || pageInfos.getNavigatepageNums() == null) {
			return EVAL_PAGE;
		}
		try {
			Writer writer = pageContext.getOut();
			VelocityTemplatePlugin template = new VelocityTemplatePlugin(vmType);
			VelocityContext context = new VelocityContext();
			context.put("pageInfo", pageInfos);
			template.setContext(context);
			template.evaluate();
			writer.write(template.getView());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public PageInfos<?> getPageInfos() {
		return pageInfos;
	}

	public void setPageInfos(PageInfos<?> pageInfos) {
		this.pageInfos = pageInfos;
	}

	public String getVmType() {
		return vmType;
	}

	public void setVmType(String vmType) {
		this.vmType = vmType;
	}

}
