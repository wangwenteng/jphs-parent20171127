package com.jinpaihushi.jphs.push.service;

import com.jinpaihushi.model.BaseModel;
import com.jinpaihushi.service.BaseService;

import cn.jpush.api.push.model.PushPayload;

public interface JPushService extends BaseService<BaseModel> {
    /**
     * 发送通知--个推
     * 
     * @param registrationId
     *            设备标识
     * @param alert
     *            推送内容
     */
    void jpushTag(String alert, String tag, String type);

    /**
     * 发送通知--群推
     * 
     * @param registrationId
     *            设备标识
     * @param alert
     *            推送内容
     * @return 
     */
    void jpushAlias(String alert, String alias, String type);

    PushPayload send_NTag(String tag, String alert, String type);

    PushPayload send_NAlias(String alias, String alert, String type);
}
