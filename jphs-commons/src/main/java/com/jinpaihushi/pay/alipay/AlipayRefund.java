package com.jinpaihushi.pay.alipay;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jinpaihushi.pay.alipay.config.AlipayConfig;
import com.jinpaihushi.pay.alipay.util.AlipaySubmit;
import com.jinpaihushi.pay.alipay.util.UtilDate;

public class AlipayRefund extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String trade_no = req.getParameter("trade_no");
        String refund_fee = req.getParameter("refund_fee");
        if (trade_no == null || "".equals(trade_no) || refund_fee == null || "".equals(refund_fee)) {
            System.out.println("参数不能为空");
        }
        //		String detail=req.getParameter("detail");

        //	String trade_no="2016111821001004340219441184";
        //	String refund_fee="0.01";
        String detail = "tuikuan";

        ////////////////////////////////////请求参数//////////////////////////////////////

        //批次号，必填，格式：当天日期[8位]+序列号[3至24位]，如：201603081000001

        String batch_no = UtilDate.cruDateRandom();

        //退款笔数，必填，参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的数量999个）

        String batch_num = "1";

        //退款详细数据，必填，格式（支付宝交易号^退款金额^备注），多笔请用#隔开
        String detail_data = trade_no + "^" + refund_fee + "^" + detail;

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        //sParaTemp.put("seller_user_id", AlipayConfig.seller_user_id);
        sParaTemp.put("batch_no", batch_no);
        sParaTemp.put("refund_date", getDateFormatter());
        //sParaTemp.put("refund_date", "2016-11-18%2009:39:26");
        sParaTemp.put("batch_num", batch_num);
        sParaTemp.put("detail_data", detail_data);

        //建立请求
        String sHtmlText = AlipaySubmit.buildRequestTest(sParaTemp);
        System.out.println("sHtmlText:" + sHtmlText);

    }

    /**
     * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public String getDateFormatter() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }

}
