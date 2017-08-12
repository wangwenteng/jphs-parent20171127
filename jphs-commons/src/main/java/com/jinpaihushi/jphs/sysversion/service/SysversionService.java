package com.jinpaihushi.jphs.sysversion.service;

import com.jinpaihushi.jphs.sysversion.model.Sysversion;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-08-08 10:46:13
 * @version 1.0
 */
public interface SysversionService extends BaseService<Sysversion> {

	public Sysversion appVersion(Integer type);

}