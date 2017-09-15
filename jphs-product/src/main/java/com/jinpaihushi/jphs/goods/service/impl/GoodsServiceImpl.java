package com.jinpaihushi.jphs.goods.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.goods.dao.GoodsDao;
import com.jinpaihushi.jphs.goods.dao.GoodsDetailDao;
import com.jinpaihushi.jphs.goods.dao.GradeDao;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.goods.model.GoodsDetail;
import com.jinpaihushi.jphs.goods.model.GoodsPrice;
import com.jinpaihushi.jphs.goods.model.Grade;
import com.jinpaihushi.jphs.goods.model.ImageType;
import com.jinpaihushi.jphs.goods.model.ListPrice;
import com.jinpaihushi.jphs.goods.service.GoodsService;
import com.jinpaihushi.jphs.jobtitle.dao.JobtitleDao;
import com.jinpaihushi.jphs.jobtitle.model.Jobtitle;
import com.jinpaihushi.jphs.price.dao.PriceDao;
import com.jinpaihushi.jphs.price.dao.PriceNurseDao;
import com.jinpaihushi.jphs.price.dao.PricePartDao;
import com.jinpaihushi.jphs.price.model.Price;
import com.jinpaihushi.jphs.price.model.PriceGrade;
import com.jinpaihushi.jphs.price.model.PriceNurse;
import com.jinpaihushi.jphs.price.model.PricePart;
import com.jinpaihushi.jphs.product.dao.ProductDao;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.sequence.dao.SequenceDao;
import com.jinpaihushi.jphs.service.dao.ServiceImagesDao;
import com.jinpaihushi.jphs.service.model.ServiceImages;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.DoubleUtils;

/**
 * 
 * @author scj
 * @date 2017-06-27 10:02:37
 * @version 1.0
 */
@Service("goodsService")
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private SequenceDao sequenceDao;

    @Autowired
    private PriceDao priceDao;

    @Autowired
    private PricePartDao pricePartDao;

    @Autowired
    private JobtitleDao jobtitleDao;

    @Autowired
    private ServiceImagesDao serviceImagesDao;

    @Autowired
    private GoodsDetailDao goodsDetailDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private PriceNurseDao priceNurseDao;

    @Override
    protected BaseDao<Goods> getDao() {
        return goodsDao;
    }

    @Override
    public Page<Product> getProductGoodsListDetail(Goods goods) {
        Page<Product> products = productDao.getProductGoodsListDetail(goods);
        return products;
    }

    @Override
    public List<Map<String, Object>> getGoodsByProduct(String productId, String platformId) {
        return goodsDao.getGoodsByProduct(productId, platformId);
    }

    public List<com.jinpaihushi.jphs.price.model.Jobtitle> getJobtitle() {
        List<com.jinpaihushi.jphs.price.model.Jobtitle> jobList = new ArrayList<com.jinpaihushi.jphs.price.model.Jobtitle>();

        Jobtitle jobtitle = new Jobtitle();
        jobtitle.setStatus(1);
        List<Jobtitle> jobtitleList = jobtitleDao.list(jobtitle);
        for (int v = 0; v < jobtitleList.size(); v++) {
            com.jinpaihushi.jphs.price.model.Jobtitle jobOne = new com.jinpaihushi.jphs.price.model.Jobtitle();
            jobOne.setId(jobtitleList.get(v).getId());
            jobOne.setName(jobtitleList.get(v).getName());
            jobOne.setJobtitleTypeId(jobtitleList.get(v).getJobtitleTypeId());
            jobOne.setGrade(jobtitleList.get(v).getGrade());
            jobOne.setCreatorId(jobtitleList.get(v).getCreatorId());
            jobOne.setCreatorName(jobtitleList.get(v).getCreatorName());
            jobOne.setCreateTime(jobtitleList.get(v).getCreateTime());
            jobList.add(jobOne);
        }
        return jobList;
    }

    @Override
    public Price getJobtitlePrice(String id) {
        Price price = new Price();
        if (id != null && !"".equals(id)) {
            price.setId(id);
        }
        price.setStatus(0);
        Price p = priceDao.load(price);
        return p;
    }

    @Override
    public Goods getGoodsDetail(String id) {
        List<PriceGrade> priceList = new ArrayList<PriceGrade>();
        Goods goods = goodsDao.getGoodsDetail(id);
        List<Price> priceGradeList = priceDao.getGoodsPriceGradeDetail(id);
        for (int a = 0; a < priceGradeList.size(); a++) {
            PriceGrade price = new PriceGrade();
            price.setGrade(priceGradeList.get(a).getGrade());
            price.setGradeName(priceGradeList.get(a).getGradeName());
            price.setGoodsId(priceGradeList.get(a).getGoodsId());
            priceGradeList.get(a).setSiteId("0");
            List<Price> priceListGone = priceDao.getGoodsPriceDetail(priceGradeList.get(a));
            price.setPrice(priceListGone);
            priceList.add(price);
        }
        goods.setPriceGrade(priceList);
        return goods;
    }

    @Override
    public Goods getGoodsImgDetail(String id) {
        Goods goods = goodsDao.getGoodsImgDetail(id);
        return goods;
    }

    /**
     * 多表添加 googs 商品表 price 价格表 service_img 图片表
     */
    @Transactional
    public int insertGoodsAndImg(Goods goods, ImageType imageType, ListPrice listPrice) {
        String ida = sequenceDao.getCurrentVal("goods");
        goods.setId(ida);
        try {
            String content = goods.getContent();
            String con = content.replace("＜", "<").replace("＞", ">").replace("＆quot;", "");
            goods.setContent(con);
        }
        catch (Exception e1) {
        }
        int result = goodsDao.insert(goods);
        if (result > 0) {
            ServiceImages serviceImages = new ServiceImages();
            serviceImages.setSourceId(ida);
            serviceImages.setSort(goods.getSort());
            serviceImages.setStatus(1);
            serviceImages.setType(1);
            try {
                serviceImages.setCreatorId(goods.getCreatorId());
                serviceImages.setCreatorName(goods.getCreatorName());
            }
            catch (Exception e) {
            }
            /* pc图片信息 */
            serviceImages.setId(UUID.randomUUID().toString());
            serviceImages.setUrl(imageType.getPcurl());
            serviceImages.setDevice_type(1);
            serviceImages.setRemarks("服务-" + goods.getTitle() + "-pc图片");
            // 添加
            serviceImagesDao.insert(serviceImages);
            /* 移动端图片信息 */
            serviceImages.setId(UUID.randomUUID().toString());
            serviceImages.setUrl(imageType.getMoveurl());
            serviceImages.setDevice_type(2);
            serviceImages.setRemarks("服务-" + goods.getTitle() + "-移动图片");
            // 添加
            serviceImagesDao.insert(serviceImages);
            if (listPrice != null && !"".equals(listPrice)) {
                if (listPrice.getPriceGrade() != null && !"".equals(listPrice.getPriceGrade())) {
                    for (int a = 0; a < listPrice.getPriceGrade().size(); a++) {
                        PriceGrade priceGradeOne = listPrice.getPriceGrade().get(a);
                        if (priceGradeOne.getGrade() != null && !"".equals(priceGradeOne.getGrade())
                                && priceGradeOne.getGradeName() != null && !"".equals(priceGradeOne.getGradeName())) {
                            List<Price> priceList = priceGradeOne.getPrice();
                            if (priceList != null && !"".equals(priceList)) {
                                for (int b = 0; b < priceList.size(); b++) {
                                    Price priceOne = priceList.get(b);
                                    if (priceOne != null && !"".equals(priceOne)) {
                                        if (priceOne.getTitle() != null && !"".equals(priceOne)
                                                && priceOne.getServiceNumber() != null && !"".equals(priceOne)
                                                && priceOne.getCostPrice() != null
                                                && !"".equals(priceOne.getCostPrice()) && priceOne.getProfit() != null
                                                && !"".equals(priceOne.getProfit())) {
                                            priceOne.setId(UUID.randomUUID().toString());
                                            priceOne.setGoodsId(ida);
                                            priceOne.setGrade(priceGradeOne.getGrade());
                                            priceOne.setGradeName(priceGradeOne.getGradeName());
                                            priceOne.setStatus(1);
                                            try {
                                                priceOne.setCreatorId(goods.getCreatorId());
                                                priceOne.setCreatorName(goods.getCreatorName());
                                            }
                                            catch (Exception e) {
                                            }
                                            priceDao.insert(priceOne);
                                            PricePart pricePart = new PricePart();
                                            pricePart.setSiteId("0");
                                            pricePart.setId(UUID.randomUUID().toString());
                                            pricePart.setPriceId(priceOne.getId());
                                            pricePart.setStatus(1);
                                            // pricePart.setOldPrice(priceOne.getOldPrice());
                                            // pricePart.setPrice(priceOne.getPrice());
                                            pricePart.setAptitudeIdArr(priceOne.getAptitudeIdArr());
                                            pricePart.setCostPrice(priceOne.getCostPrice());
                                            pricePart.setProfit(priceOne.getProfit());
                                            try {
                                                pricePart.setCreatorId(goods.getCreatorId());
                                                pricePart.setCreatorName(goods.getCreatorName());
                                            }
                                            catch (Exception e) {
                                            }
                                            pricePartDao.insert(pricePart);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return result;
        }
        else {
            return result;
        }
    }

    /*
     * if(priceOne.getTitle() != null && !"".equals(priceOne) &&
     * priceOne.getServiceNumber() != null && !"".equals(priceOne) &&
     * priceOne.getCostPrice()!= null && !"".equals(priceOne.getCostPrice()) &&
     * priceOne.getProfit()!= null && !"".equals(priceOne.getProfit())){
     * priceOne.setId(UUID.randomUUID().toString()); priceOne.setGoodsId(ida);
     * priceOne.setStatus(0); try { priceOne.setCreatorId(goods.getCreatorId());
     * priceOne.setCreatorName(goods.getCreatorName()); } catch (Exception e) {
     * } priceDao.insert(priceOne); PricePart pricePart = new PricePart();
     * pricePart.setSiteId("0"); pricePart.setId(UUID.randomUUID().toString());
     * pricePart.setPriceId(priceOne.getId());
     * pricePart.setStatus(priceOne.getStatus());
     * pricePart.setOldPrice(priceOne.getOldPrice());
     * pricePart.setPrice(priceOne.getPrice());
     * pricePart.setAptitudeIdArr(priceOne.getAptitudeIdArr());
     * pricePart.setCostPrice(priceOne.getCostPrice());
     * pricePart.setProfit(priceOne.getProfit()); try {
     * pricePart.setCreatorId(goods.getCreatorId());
     * pricePart.setCreatorName(goods.getCreatorName()); } catch (Exception e) {
     * } pricePartDao.insert(pricePart); }
     */

    /**
     * 多表修改 googs 商品表 price 价格表 service_img 图片表
     */
    @Transactional
    public boolean updateGoods(Goods goods, ListPrice listPrice, ImageType imageType) {
        boolean falg = true;

        try {
            String content = goods.getContent();
            String con = content.replace("＜", "<").replace("＞", ">").replace("＆quot;", "");
            goods.setContent(con);
        }
        catch (Exception e) {
        }
        int b = goodsDao.update(goods);

        if (b > 0) {
            if (!"".equals(imageType.getPcid()) && imageType.getPcid() != null) {
                ServiceImages serviceImages = new ServiceImages();
                serviceImages.setSourceId(goods.getId());
                serviceImages.setSort(goods.getSort());
                serviceImages.setStatus(1);
                serviceImages.setType(1);
                /* pc图片信息 */
                serviceImages.setId(imageType.getPcid());
                serviceImages.setUrl(imageType.getPcurl());
                serviceImages.setDevice_type(1);
                serviceImages.setRemarks("服务-" + goods.getTitle() + "-pc图片");
                // 修改
                int pc = serviceImagesDao.update(serviceImages);
                if (pc > 0) {
                    if (!"".equals(imageType.getMoveid()) && imageType.getMoveid() != null) {
                        /* 移动端图片信息 */
                        serviceImages.setId(imageType.getMoveid());
                        serviceImages.setUrl(imageType.getMoveurl());
                        serviceImages.setDevice_type(2);
                        serviceImages.setRemarks("服务-" + goods.getTitle() + "-移动图片");
                        // 修改
                        int wap = serviceImagesDao.update(serviceImages);
                        if (wap <= 0) {
                            falg = false;
                        }
                    }
                }
                else {
                    falg = false;
                }
            }

            if (listPrice != null && !"".equals(listPrice)) {
                if (listPrice.getPriceGrade() != null && !"".equals(listPrice.getPriceGrade())
                        && !"null".equals(listPrice.getPriceGrade())) {
                    for (int c = 0; c < listPrice.getPriceGrade().size(); c++) {
                        PriceGrade priceGradeOne = listPrice.getPriceGrade().get(c);
                        if (priceGradeOne.getGrade() != null && !"".equals(priceGradeOne.getGrade())
                                && priceGradeOne.getGradeName() != null && !"".equals(priceGradeOne.getGradeName())) {
                            List<Price> priceList = priceGradeOne.getPrice();
                            if (priceList != null && !"".equals(priceList) && !"null".equals(priceList)) {
                                for (int a = 0; a < priceList.size(); a++) {
                                    // 没有ID添加-有ID更新
                                    if (priceList.get(a).getId() != null && !"null".equals(priceList.get(a).getId())
                                            && !"".equals(priceList.get(a).getId())
                                            && priceList.get(a).getId() != null) {
                                        // 没有价格-等级和goodsID是为删除
                                        if (priceList.get(a).getGrade() != null
                                                && !"null".equals(priceList.get(a).getGrade())
                                                && !"".equals(priceList.get(a).getGrade())
                                                && priceList.get(a).getGrade() != null) {
                                            priceList.get(a).setGoodsId(goods.getId());
                                            priceList.get(a).setGradeName(priceGradeOne.getGradeName());
                                            priceDao.update(priceList.get(a));
                                            PricePart pricePart = new PricePart();
                                            pricePart.setSiteId("0");
                                            pricePart.setId(priceList.get(a).getPricePartId());
                                            pricePart.setPriceId(priceList.get(a).getId());
                                            pricePart.setStatus(priceList.get(a).getStatus());
                                            pricePart.setCostPrice(priceList.get(a).getCostPrice());
                                            /*
                                             * pricePart.setOldPrice(listPrice.
                                             * getPrice().get(a).getOldPrice());
                                             * pricePart.setPrice(listPrice.
                                             * getPrice().get(a).getPrice());
                                             */
                                            pricePart.setProfit(priceList.get(a).getProfit());
                                            pricePartDao.update(pricePart);
                                        }
                                        else {
                                            // 隐藏数据
                                            priceList.get(a).setStatus(-1);
                                            priceDao.update(priceList.get(a));
                                        }
                                    }
                                    else {
                                        if (priceList.get(a).getCostPrice() != null
                                                && !"".equals(priceList.get(a).getCostPrice())
                                                && priceList.get(a).getProfit() != null
                                                && !"".equals(priceList.get(a).getProfit())) {
                                            priceList.get(a).setId(UUID.randomUUID().toString());
                                            priceList.get(a).setGoodsId(goods.getId());
                                            priceList.get(a).setGradeName(priceGradeOne.getGradeName());
                                            priceList.get(a).setGrade(priceGradeOne.getGrade());
                                            priceList.get(a).setStatus(0);
                                            priceDao.insert(priceList.get(a));
                                            PricePart pricePart = new PricePart();
                                            pricePart.setSiteId("0");
                                            pricePart.setId(UUID.randomUUID().toString());
                                            pricePart.setPriceId(priceList.get(a).getId());
                                            pricePart.setStatus(1);
                                            /*
                                             * pricePart.setOldPrice(listPrice.
                                             * getPrice().get(a).getOldPrice());
                                             * pricePart.setPrice(listPrice.
                                             * getPrice().get(a).getPrice());
                                             */
                                            pricePart.setCostPrice(priceList.get(a).getCostPrice());
                                            pricePart.setProfit(priceList.get(a).getProfit());
                                            pricePartDao.insert(pricePart);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            falg = false;
        }
        return falg;
    }

    public List<Map<String, Object>> getHospitalGoods(Map<String, Object> map) {
        return goodsDao.getHospitalGoods(map);
    }

    /**
     * {获取服务的详情}
     * 
     * @param id
     * @return
     * @author: wangwt
     */
    @Override
    public GoodsDetail getOneGoodsDetail(String id, String siteId, Integer deviceType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goodsId", id);
        map.put("siteId", siteId);
        map.put("deviceType", deviceType);
        GoodsDetail goodsDetail = goodsDetailDao.getGoodsDetail(map);
        if (goodsDetail != null) {
            String str = goodsDetail.getContent().replace("＜", "<").replace("＞", ">");
            goodsDetail.setContent(str);
        }
        return goodsDetail;
    }

    public List<Map<String, Object>> getColumnGoods(Map<String, Object> map) {
        List<Map<String, Object>> goodsColumn = goodsDao.getColumnGoods(map);
        return goodsColumn;
    }

    @Override
    public List<Grade> getGoodsAllPrice(String goodsId, String siteId) {
        Map<String, Object> query = new HashMap<>();
        query.put("goodsId", goodsId);
        query.put("siteId", siteId);
        return gradeDao.getGoodsPrice(query);
    }

    @Override
    public List<Goods> getAllGoods(Goods goods) {

        List<Goods> allGoods = goodsDao.getAllGoods(goods);
        PriceNurse priceNurse = new PriceNurse();
        List<Goods> allGoodsList = new ArrayList<Goods>();
        List<Goods> list = goodsDao.getMyService(goods.getCreatorId());

        priceNurse.setCreatorId(goods.getCreatorId());
        priceNurse.setStatus(0);
        //	Page<PriceNurse> query = priceNurseDao.query(priceNurse);
        for (int i = 0; i < allGoods.size(); i++) {
            //System.out.println(i);
            List<Grade> grade = allGoods.get(i).getGrade();
            for (int j = 0; j < grade.size(); j++) {
                List<GoodsPrice> goodsPrice = grade.get(j).getGoodsPrice();
                for (int k = 0; k < goodsPrice.size(); k++) {

                    Double price = goodsPrice.get(k).getPrice();
                    Double maxPrice = goodsPrice.get(k).getMaxPrice();
                    double round = DoubleUtils.round(price * maxPrice, 0);
                    goodsPrice.get(k).setMaxPrice(round);
                }
            }

            System.out.println(list.size());
            for (int j = 0; j < list.size(); j++) {

                //System.out.println(list.get(j).getId()+"............");

                //System.out.println(allGoods.get(i).getId()+"。。。。。。。。。。");
                if ((list.get(j).getId().equals(allGoods.get(i).getId()))) {

                    List<Grade> grade3 = allGoods.get(i).getGrade();
                    List<Grade> gradeList = new ArrayList<Grade>();
                    List<Grade> grade2 = list.get(j).getGrade();
                    for (int h = 0; h < grade2.size(); h++) {
                        for (int k = 0; k < grade3.size(); k++) {
                            if (!(grade3.get(k).getGrade().equals(grade2.get(h).getGrade()))) {
                                gradeList.add(grade3.get(k));
                            }
                        }

                    }
                    System.out.println(grade3.size());
                    System.out.println(grade2.size());
                    System.out.println("-------------------------------------");

                    //System.out.println(gradeList.size());
                    //System.out.println(gradeList.size()<grade3.size());
                    //System.out.println(grade3.size());
                    if (grade2.size() < grade3.size()) {
                        System.out.println("==========================");
                        allGoods.get(i).setGrade(null);
                        allGoods.get(i).setGrade(gradeList);
                        allGoodsList.add(allGoods.get(i));
                    }

                    gradeList.removeAll(list);
                    //	break;
                }
                else {
                    //System.out.println("+++++++++++++++++++++++++");
                    //	System.out.println(allGoods.get(i).getId());
                    allGoodsList.add(allGoods.get(i));
                    //break;
                }

            }
        }

        //if(list.size()==0){
        return allGoods;
        /*}else{
        	return allGoodsList;
        }*/

    }

    @Override
    public List<Goods> getMyService(String creatorId) {
        List<Goods> list = goodsDao.getMyService(creatorId);

        for (int i = 0; i < list.size(); i++) {
            List<Grade> grade = list.get(i).getGrade();
            for (int j = 0; j < grade.size(); j++) {
                List<GoodsPrice> goodsPrice = grade.get(j).getGoodsPrice();
                for (int k = 0; k < goodsPrice.size(); k++) {

                    Double price = goodsPrice.get(k).getPrice();
                    Double maxPrice = goodsPrice.get(k).getMaxPrice();
                    double round = DoubleUtils.round(price * maxPrice, 0);
                    list.get(i).setMaxPrice(round);
                    list.get(i).setMinPrice(price);
                    double round2 = DoubleUtils.round(price * maxPrice, 0);
                    goodsPrice.get(k).setMaxPrice(round2);
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            List<Double> pricelist = new ArrayList<Double>();
            PriceNurse pn = new PriceNurse();
            pn.setGoodsId(list.get(i).getId());
            pn.setCreatorId(creatorId);
            List<PriceNurse> query = priceNurseDao.getList(pn);
            if (query.size() > 0) {
                for (int j = 0; j < query.size(); j++) {

                    pricelist.add(query.get(j).getPrice());
                }
                if (pricelist.size() > 1) {
                    double max = Collections.max(pricelist);
                    double min = Collections.min(pricelist);
                    System.out.println(min);
                    list.get(i).setMaxPrice(max);
                    list.get(i).setMinPrice(min);
                }
                else if (pricelist.size() == 1) {
                    list.get(i).setMaxPrice(pricelist.get(0));
                }

            }
        }

        return list;
    }

}