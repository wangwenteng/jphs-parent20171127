package com.jinpaihushi.context;

import org.springframework.context.ApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: garmbrood
 * Time: 2009-4-7 16:32:56
 * Company: 天极传媒集团
 * Descripion:
 */
public class SpringContext {
    public static ApplicationContext context ;

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context) {
        SpringContext.context = context;
    }
}
