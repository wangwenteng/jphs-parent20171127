package com.jinpaihushi.utils.velocity;



/**
 * 
 * @author fengrz
 *
 * @param <T>
 */
public abstract class AbstractTemplatePlugin<T> implements TemplatePlugin<T>{

	protected String templateName;
	
	protected String view;
	
	protected AbstractTemplatePlugin(String templateName){
		this.templateName = templateName;
	}

    /**
     * 获取视图
     * @return
     */
    public String getView(){
    	if(view == null){
    		evaluate();
    	}
    	return view;
    }

}
