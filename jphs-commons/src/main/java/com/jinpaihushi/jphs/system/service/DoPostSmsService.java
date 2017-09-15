package com.jinpaihushi.jphs.system.service;

import com.jinpaihushi.model.BaseModel;
import com.jinpaihushi.service.BaseService;

public interface DoPostSmsService extends BaseService<BaseModel> {
    void sendSms(String mobile, String contentCode, String param);
}
