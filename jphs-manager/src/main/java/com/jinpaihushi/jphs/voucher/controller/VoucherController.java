package com.jinpaihushi.jphs.voucher.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.activity.service.VoucherUseService;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.goods.service.GoodsService;
import com.jinpaihushi.jphs.platform.model.TreeNode;
import com.jinpaihushi.jphs.platform.service.PlatformService;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.product.service.ProductService;
import com.jinpaihushi.jphs.site.model.Site;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.jphs.voucher.model.Voucher;
import com.jinpaihushi.jphs.voucher.model.VoucherRepertory;
import com.jinpaihushi.jphs.voucher.service.VoucherRepertoryService;
import com.jinpaihushi.jphs.voucher.service.VoucherService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-07-14 14:01:47
 * @version 1.0
 */
@Controller
@RequestMapping(name = "优惠券", path = "/voucher")
public class VoucherController extends BaseController<Voucher> {

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private VoucherRepertoryService voucherRepertoryService;

    @Autowired
    private VoucherUseService voucherUseService;

    @Autowired
    private ProductService productService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private PlatformService platformService;

    @Autowired
    private UserService userService;

    @Override
    protected BaseService<Voucher> getService() {
        return voucherService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Voucher voucher, Integer p, Integer n) {

        //Page<Voucher> list = voucherService.query(voucher);

        Product productModel = new Product();
        productModel.setStatus(1);
        Page<Product> productList = productService.query(productModel);

        Goods goodsModel = new Goods();

        if (voucher != null && voucher.getGoodsId() != null) {
            goodsModel.setProductId(voucher.getProductId());
            goodsModel.setStatus(1);
            Page<Goods> goodsList = goodsService.query(goodsModel);
            modelMap.put("goodsList", goodsList);
        }
        else {
            modelMap.put("goodsList", null);
        }

        startPage(p, n);
        Page<Voucher> list = voucherService.getList(voucher);
        for (int i = 0; i < list.size(); i++) {
            String productName = "";
            String goodsName = "";

            String productIds = list.get(i).getProductId();
            String goodsIds = list.get(i).getGoodsId();

            if (productIds != null) {
                String[] productId = productIds.split(",");
                for (int j = 0; j < productId.length; j++) {
                    Product product = productService.loadById(productId[j]);
                    if (product != null) {
                        productName += "," + product.getTitle();
                    }
                }
                list.get(i).setProductName(productName.substring(1));
            }

            if (goodsIds != null) {
                String[] goodsId = goodsIds.split(",");
                for (int j = 0; j < goodsId.length; j++) {
                    Goods goods = goodsService.loadById(goodsId[j]);
                    if (goods != null) {
                        goodsName += "," + goods.getTitle();
                    }
                }
                list.get(i).setGoodsName(goodsName.substring(1));
            }
        }

        PageInfos<Voucher> pageInfo = new PageInfos<Voucher>(list, req);
        modelMap.put("list", list);
        modelMap.put("productList", productList);

        modelMap.put("pageInfo", pageInfo);
        return "activity/voucher/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        Voucher voucher = voucherService.loadById(id);
        modelMap.put("voucher", voucher);
        return "activity/voucher/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {

        /*Page<Product> list = productService.query(new Product());
        modelMap.put("list", list);*/
        JSONObject message = new JSONObject();
        List<TreeNode> list = platformService.getGoodsList(null);
        List<Site> site = platformService.getSelectSite(null);
        message.put("treeData", list);
        modelMap.put("data", message);
        modelMap.put("site", site);
        return "activity/voucher/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id,
            Integer p, Integer n) {

        Voucher voucherModel = new Voucher();
        VoucherRepertory voucherRepertory = new VoucherRepertory();
        VoucherUse voucherUse = new VoucherUse();
        voucherModel.setId(id);
        Voucher voucher = voucherService.getList(voucherModel).get(0);

        String productName = "";
        String productIds = voucher.getProductId();
        if (productIds != null) {
            String[] productId = productIds.split(",");
            for (int i = 0; i < productId.length; i++) {
                Product product = productService.loadById(productId[i]);
                if (product != null) {
                    productName += "," + product.getTitle();
                }
            }
            voucher.setProductName(productName.substring(1));
        }

        String goodsName = "";
        String goodsIds = voucher.getGoodsId();
        if (goodsIds != null) {
            String[] goodsId = goodsIds.split(",");
            for (int j = 0; j < goodsId.length; j++) {
                Goods goods = goodsService.loadById(goodsId[j]);
                if (goods != null) {
                    goodsName += "," + goods.getTitle();
                }
            }
            voucher.setGoodsName(goodsName.substring(1));
        }

        voucherRepertory.setVoucherId(voucher.getId());
        Integer count = voucherRepertoryService.count(voucherRepertory);
        Page<VoucherRepertory> voucherRepertorylist = voucherRepertoryService.query(voucherRepertory);
        startPage(p, n);
        Page<VoucherUse> list = voucherUseService.getDetailList(id);

        PageInfos<VoucherUse> pageInfo = new PageInfos<VoucherUse>(list, req);
        modelMap.put("voucher", voucher);
        modelMap.put("count", count);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "activity/voucher/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Voucher voucher, Integer count, Double CAmount, Double DAmount) {

        if (voucher.getType() != null) {
            String id = UUID.randomUUID().toString();
            SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");
            voucher.setId(id);

            voucher.setCreateTime(new Date());
            voucher.setCreatorId(systemUser.getId());
            voucher.setCreatorName(systemUser.getName());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String batchNo = sdf.format(new Date());

            //优惠券批次号生成规则
            if (voucher.getType() == 1) {
                //现金券批次号生成规则
                voucher.setBatchNo("XJS" + batchNo);
            }
            else if (voucher.getType() == 2) {
                //满减券批次号生成规则
                voucher.setBatchNo("MJS" + batchNo);
            }
            else if (voucher.getType() == 3) {
                //折扣券批次号生成规则
                voucher.setBatchNo("ZKS" + batchNo);
            }
            String result = voucherService.insert(voucher);

            VoucherRepertory voucherRepertory = new VoucherRepertory();
            for (int i = 0; i < count; i++) {

                if (voucher.getType() == 1) {
                    voucherRepertory.setAmount(voucher.getAmount());
                }
                else if (voucher.getType() == 2) {
                    voucherRepertory.setAmount(CAmount);
                }
                else if (voucher.getType() == 3) {
                    voucherRepertory.setAmount(DAmount);
                }

                voucherRepertory.setId(UUID.randomUUID().toString());
                voucherRepertory.setVoucherId(id);

                voucherRepertory.setNo(voucher.getBatchNo() + i);

                voucherRepertory.setConditionAmount(voucher.getConditionAmount());
                voucherRepertory.setDiscountAmount(voucher.getDiscountAmount());
				voucherRepertory.setCode(getStringRandom(8));
                voucherRepertoryService.insert(voucherRepertory);
            }

            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/voucher/err.jhtml";
            }
        }
        return "redirect:/voucher/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = voucherService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/voucher/err.jhtml";
        }

        return "redirect:/voucher/index.jhtml";
    }

    @RequestMapping(name = "查询商品", path = "/getGoodsList.jhtml")
    @ResponseBody
    public JSONObject getGoodsList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        JSONObject message = new JSONObject();
        Goods goods = new Goods();
        goods.setProductId(id);
        goods.setStatus(1);
        Page<Goods> goodsList = goodsService.query(goods);
        message.put("goodsList", goodsList);

        return message;
    }

    @RequestMapping(name = "查询用户", path = "/getUserList.jhtml")
    public ModelMap getGoodsList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            User user, Integer p, Integer n) {
        user.setStatus(0);
        user.setType(1);

        Page<User> userList = userService.query(user);

        modelMap.put("userList", userList);

        return modelMap;
    }

    @RequestMapping(name = "添加优惠券使用", path = "/addVoucherUser.jhtml")
    @ResponseBody
    public JSONObject addVoucherUser(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            ModelMap modelMap, VoucherUse voucherUse) {

        SystemUser systemUser = (SystemUser) hs.getAttribute("session_user");

        VoucherRepertory voucherRepertory = voucherRepertoryService.loadById(voucherUse.getVoucherRepertoryId());
        //查询优惠券信息

        Voucher voucher = voucherService.loadById(voucherRepertory.getVoucherId());
        //用户信息
        User user = userService.loadById(voucherUse.getCreatorId());

        voucherUse.setId(UUID.randomUUID().toString());
        voucherUse.setPhone(user.getPhone());

        voucherUse.setAmount(voucherRepertory.getAmount());

        voucherUse.setStartTime(voucher.getStartTime());
        voucherUse.setEndTime(voucher.getEndTime());
        voucherUse.setCreatorName(user.getName());
        voucherUse.setCreateTime(new Date());
        voucherUse.setStatus(0);
        voucherUse.setGrantName(systemUser.getName());
        JSONObject message = new JSONObject();
        String result = voucherUseService.insert(voucherUse);

        voucherRepertory.setStatus(1);
        voucherRepertoryService.update(voucherRepertory);

        message.put("result", voucher.getId());

        return message;
    }

    @RequestMapping(name = "跳转到添加页", path = "/addUser.jhtml")
    public String addUser(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            VoucherRepertory voucherRepertory, User user, Integer p, Integer n) {

        user.setStatus(1);
        user.setType(1);
        user.setId("");
        startPage(p, n);
        Page<User> userList = userService.query(user);
        PageInfos<User> pageInfo = new PageInfos<User>(userList, req);
        modelMap.put("userList", userList);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("user", user);
        modelMap.put("voucherRepertoryId", voucherRepertory.getId());
        modelMap.put("voucherId", voucherRepertory.getVoucherId());
        return "activity/voucher/detail/addUser";
    }


	private String getStringRandom(int length) {

	String val = "";
	Random random = new Random();

	//参数length，表示生成几位随机数
	for(int i = 0; i < length; i++) {

	String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
	//输出字母还是数字
	if( "char".equalsIgnoreCase(charOrNum) ) {
	//输出是大写字母还是小写字母
	int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
	val += (char)(random.nextInt(26) + temp);
	} else if( "num".equalsIgnoreCase(charOrNum) ) {
	val += String.valueOf(random.nextInt(10));
	}
	}
	return val;
	}
}
