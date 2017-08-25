package com.jinpaihushi.jphs.custom.service;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.custom.model.CustomService;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-07-13 14:40:35
 * @version 1.0
 */
public interface CustomServiceService extends BaseService<CustomService> {

	Page<CustomService> getList(CustomService customService);

}