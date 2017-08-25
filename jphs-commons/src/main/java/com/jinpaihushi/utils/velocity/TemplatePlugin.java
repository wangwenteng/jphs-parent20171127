package com.jinpaihushi.utils.velocity;

/**
 * 
 * @author fengrz
 *
 * @param <T>
 */
public interface TemplatePlugin<T> {

    /**
     * 设置上下文
     * @param context
     */
    void setContext(T context);

    /**
     * 执行模板
     */
    void evaluate();

    /**
     * 获取视图
     * @return
     */
    String getView();

}
