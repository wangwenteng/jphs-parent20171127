package com.jinpaihushi.pay.wechatpay;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.jinpaihushi.pay.wechatpay.utils.ClientCustomSSL;
import com.jinpaihushi.pay.wechatpay.utils.RequestHandler;
import com.jinpaihushi.pay.wechatpay.utils.TenpayUtil;

public class WeiXinPayRefund extends HttpServlet {
    // 参考地址：http://blog.csdn.net/wangxuewei111/article/details/44021035
    //http://blog.csdn.net/u011160656/article/details/41946873

    //正式用
    private static String appid = "wx82592fbdce14467c";

    private static String appsecret = "7d5e5301dbc7f3c2797f89c74aafb21a";

    private static String partner = "1305979801";

    // 这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
    private static String partnerkey = "bffdd264a74e879d50146be9c34ce7e2";

    // openId 是微信用户针对公众号的标识，授权的部分这里不解释
    private static String openId = "";

    // 微信支付成功后通知地址 必须要求80端口并且地址不能带参数
    private static String notifyurl = "http://www.baidu.com";
    //private static String notifyurl = "http://182.92.98.51/foot/weixinv3notify.do";

    private Logger log = Logger.getLogger(WeiXinPayRefund.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String out_refund_no = TenpayUtil.getNonceStr();// 退款单号
        String out_trade_no = request.getParameter("out_trade_no");// 订单号
        String total_fee = request.getParameter("total_fee");// 总金额
        String refund_fee = request.getParameter("refund_fee");// 退款金额
        String nonce_str = TenpayUtil.getNonceStr();// 随机字符串
        //String appid = "";
        //String appsecret = "";
        String mch_id = partner;
        String op_user_id = partner;//就是MCHID
        //String partnerkey = "";//商户平台上的那个KEY
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("out_refund_no", out_refund_no);
        packageParams.put("total_fee", total_fee);
        packageParams.put("refund_fee", refund_fee);
        packageParams.put("op_user_id", op_user_id);

        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(appid, appsecret, partnerkey);

        String sign = reqHandler.createSign(packageParams);
        String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>" + mch_id + "</mch_id>" + "<nonce_str>"
                + nonce_str + "</nonce_str>" + "<sign><![CDATA[" + sign + "]]></sign>" + "<out_trade_no>" + out_trade_no
                + "</out_trade_no>" + "<out_refund_no>" + out_refund_no + "</out_refund_no>" + "<total_fee>" + total_fee
                + "</total_fee>" + "<refund_fee>" + refund_fee + "</refund_fee>" + "<op_user_id>" + op_user_id
                + "</op_user_id>" + "</xml>";
        String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
        try {
            String s = ClientCustomSSL.doRefund(createOrderURL, xml);
            System.out.println(s);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}