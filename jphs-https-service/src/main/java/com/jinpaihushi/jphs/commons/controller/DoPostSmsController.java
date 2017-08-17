package com.jinpaihushi.jphs.commons.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.jinpaihushi.tools.SmsClientSend;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@Controller
public class DoPostSmsController {
    //天下畅通
    @Value("${url}")
    public String url;

    @Value("${userid}")
    public String userid;

    @Value("${account}")
    public String account;

    @Value("${password}")
    public String password;

    //阿里大于
    @Value("${url_}")
    public String url_;

    @Value("${appkey_}")
    public String appkey_;

    @Value("${secret_}")
    public String secret_;

    @Value("${message}")
    public String message;

    public void sendSms(String mobile, String content, String contentCode, String param) {

        //1.天下畅通平台，2.阿里大于
        int messageId = Integer.valueOf(message.trim());
        if (messageId == 1) {
            try {
                String send = SmsClientSend.sendSms(url, "send", userid, account, password, mobile, content);
                System.out.println(new String(send.getBytes("UTF-8")));
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        else if (messageId == 2) {
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
}
