package com.jinpaihushi.jphs.product.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.price.model.PriceNurse;
import com.jinpaihushi.jphs.price.service.PricePartService;
import com.jinpaihushi.jphs.price.service.PriceNurseService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

/**
 * 
 * @author yangsong
 * @date 2017-08-16 15:07:51
 * @version 1.0
 */
@Controller
@RequestMapping(name = "PriceNurse", path = "/price/nurse")
public class PriceNurseController extends BaseController<PriceNurse> {

    @Autowired
    private PriceNurseService priceNurseService;
	@Autowired
	private PricePartService pricePartService;

    @Override
    protected BaseService<PriceNurse> getService() {
        return priceNurseService;
    }

    @ResponseBody
    @RequestMapping(name = "添加发布服务", path = "/insertPriceNurse.json")
    public byte[] insertPriceNurse(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            String pricePartIds, String prices, String creatorId, String goodsId) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("price.nurse.insertPriceNurse.json");
            }
            if (StringUtils.isEmpty(pricePartIds)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            if (StringUtils.isEmpty(prices)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            if (StringUtils.isEmpty(creatorId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }

            String[] pricePartIdArr = pricePartIds.split(",");
            String[] priceArr = prices.split(",");

            if (pricePartIdArr.length != priceArr.length) {
                return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
            }

            PriceNurse pn = new PriceNurse();
            pn.setCreatorId(creatorId);
            pn.setGoodsId(goodsId);

          //  boolean flag = priceNurseService.deleteByUserAndGoods(pn);

         /*   if (!flag) {
				  
            }*/

            PriceNurse priceNurse = new PriceNurse();
            String result = null;
            priceNurse.setGoodsId(goodsId);
            for (int i = 0; i < pricePartIdArr.length; i++) {
                priceNurse.setId(UUID.randomUUID().toString());
                priceNurse.setPricePartId(pricePartIdArr[i]);
                priceNurse.setPrice(Double.parseDouble(priceArr[i]));
                priceNurse.setStatus(0);
                priceNurse.setCreatorId(creatorId);

				PriceNurse priceNurseModel = priceNurseService.getModel(creatorId,pricePartIdArr[i]);
				System.out.println(priceNurseModel != null);
				//System.out.println(priceNurseModel != "");

				if(priceNurseModel != null){
					boolean b = priceNurseService.updateModel(priceNurse);
					 
				}else{
					result = priceNurseService.insert(priceNurse);
					if (result.length() <= 0) {
						return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
					}
				}


                 
                
            }
            return JSONUtil.toJSONResult(1, "操作成功！", "1");
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("price.nurse.insertPriceNurse.json ", e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "删除发布服务", path = "/deletePriceNurse.json")
    public byte[] deletePriceNurse(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            PriceNurse priceNurse) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("price.nurse.deletePriceNurse.json");
            }
            if (StringUtils.isEmpty(priceNurse.getCreatorId())) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            if (StringUtils.isEmpty(priceNurse.getGoodsId())) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }

            boolean flag = priceNurseService.updatePriceNurse(priceNurse);

            return JSONUtil.toJSONResult(1, "操作成功！", "1");
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("price.nurse.deletePriceNurse.json ", e);
        }
        return null;
    }

}
