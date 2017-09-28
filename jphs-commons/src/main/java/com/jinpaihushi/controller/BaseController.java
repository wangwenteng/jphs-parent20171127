package com.jinpaihushi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingErrorProcessor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.github.pagehelper.PageHelper;
import com.jinpaihushi.config.ApplicationConfig;
import com.jinpaihushi.context.SpringHelper;
import com.jinpaihushi.jphs.system.service.SystemModuleService;
import com.jinpaihushi.model.BaseModel;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.CustomDateEditor;
import com.jinpaihushi.utils.CustomStringEditor;
import com.jinpaihushi.utils.exception.ExceptionHandler;

/**
 * Controller基类，添加了日志处理器，异常处理器,格式处理器,实现了事件发表方法
 * 
 * @author fengrz
 *
 */
public abstract class BaseController<T extends BaseModel> implements ApplicationEventPublisher {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static List<String> urlList = new ArrayList<>();

    @Autowired
    private SystemModuleService systemModuleService;

    @Autowired
    protected ExceptionHandler exceptionHandler;

    @Autowired
    protected ApplicationConfig applicationConfig;

    public BaseController() {
        super();
        logger.info("init:" + this.getClass().getSimpleName());

    }

    protected abstract BaseService<T> getService();

    /**
     * 添加格式处理器
     *
     * @param binder
     */
    @InitBinder
    public void initDataBinder(WebDataBinder binder) {
        // binder.registerCustomEditor(SID.class, new SIDEditor());
        binder.registerCustomEditor(Date.class, new CustomDateEditor());
        binder.registerCustomEditor(String.class, new CustomStringEditor());
        binder.setBindingErrorProcessor(new BindingErrorProcessor() {
            @Override
            public void processMissingFieldError(String missingField, BindingResult bindingResult) {
                logger.debug(missingField);
            }

            @Override
            public void processPropertyAccessException(PropertyAccessException ex, BindingResult bindingResult) {
                // logger.trace(ex);
            }
        });
    }

    /**
     * 返回错误提示信息errorDesc
     * 
     * @param bindingResult
     * @param modelMap
     * @param errorDesc
     */
    protected void setBindingResultError(BindingResult bindingResult, ModelMap modelMap, String errorDesc) {
        ObjectError objectError = new ObjectError("errorInfo", errorDesc);
        bindingResult.addError(objectError);
        modelMap.addAttribute("errors", bindingResult.getAllErrors());
    }

    @PostConstruct // 使用该注解表示在spring容器初始化之后会调用此方法
    public void initMethod() {
        //        if (this.getClass().getName().startsWith("com.jinpaihushi.jphs.withdraw")) {
        //            urlList = GetRequestMappingName.getMethodName(this.getClass());
        //            systemModuleService.initUrlHandler(urlList);
        //        }
    }

    /**
     * 检查参数验证框架的验证结果 如验证不通过，将错误提示信息写入到modelMap
     * 
     * @param bindingResult
     * @param modelMap
     */
    protected boolean checkParamValid(BindingResult bindingResult, ModelMap modelMap) {
        boolean paramError = bindingResult.hasErrors();
        if (paramError) {
            List<String> errs = new ArrayList<String>();
            for (ObjectError oe : bindingResult.getAllErrors()) {
                errs.add(oe.getDefaultMessage());
            }
            String msg = StringUtils.join(errs, ",");
            modelMap.put("code", "100007");
            modelMap.put("message", msg);
        }
        return !paramError;
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        SpringHelper.context.publishEvent(event);
    }

    @Override
    public void publishEvent(Object event) {
        SpringHelper.context.publishEvent(event);
    }

    protected void startPage(Integer pageNum, Integer pageSize) {
        pageSize = pageSize != null && pageSize > 0 ? pageSize : applicationConfig.getDefaultPageSize();
        pageSize = pageSize > 100 ? 100 : pageSize;
        PageHelper.startPage(pageNum != null && pageNum > 0 ? pageNum : 1, pageSize);
    }
}
