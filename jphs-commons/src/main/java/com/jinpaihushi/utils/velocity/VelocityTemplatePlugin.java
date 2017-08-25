package com.jinpaihushi.utils.velocity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vm.TemplateHelper;

/**
 * 
 * @author fengrz
 *
 */
public class VelocityTemplatePlugin extends AbstractTemplatePlugin<Context> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private static VelocityEngine velocityEngine;
	static{
		velocityEngine = new VelocityEngine();
	}
	
	protected Context context;

	public VelocityTemplatePlugin(String templateName) {
		super(templateName);
	}

	@Override
	public void evaluate() {
		InputStream input = TemplateHelper.getTemplateInputStream(templateName);
		StringWriter writer = new StringWriter();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		try {
			velocityEngine.evaluate(context, writer, this.getClass().getName(), reader);
			this.view = writer.toString();
		} catch (ParseErrorException e) {
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			e.printStackTrace();
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} finally{
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}
	
	public void setContext(Map<String, ?> map) {
		this.context = new VelocityContext(map);
	}

}
