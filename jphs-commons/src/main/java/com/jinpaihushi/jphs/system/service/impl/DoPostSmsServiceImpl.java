package com.jinpaihushi.jphs.system.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.system.service.DoPostSmsService;
import com.jinpaihushi.model.BaseModel;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@Service("doPostSmsService")
public class DoPostSmsServiceImpl extends BaseServiceImpl<BaseModel> implements DoPostSmsService {
    @Override
    protected BaseDao<BaseModel> getDao() {
        // TODO Auto-generated method stub
        return null;
    }

    //阿里大于
    @Value("${url_}")
    public String url_;

    @Value("${appkey_}")
    public String appkey_;

    @Value("${secret_}")
    public String secret_;

    @Override
    public void sendSms(String mobile, String contentCode, String param) {
        try {
            TaobaoClient client = new DefaultTaobaoClient(url_, appkey_, secret_);
            AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
            req.setExtend("123456");
            req.setSmsType("normal");
            req.setSmsFreeSignName("金牌护士");//短信签名，传入的短信签名必须是在阿里大于“管理中心-验证码/短信通知/推广短信-配置短信签名
            req.setSmsParamString(param);
            req.setRecNum(mobile);//短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔
            req.setSmsTemplateCode(contentCode);//短信模板ID，传入的模板必须是在阿里大于“管理中心-短信模板管理”中的可用模板。
            AlibabaAliqinFcSmsNumSendResponse rsp;
            rsp = client.execute(req);
            System.out.println(rsp.getBody());
        }
        catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
