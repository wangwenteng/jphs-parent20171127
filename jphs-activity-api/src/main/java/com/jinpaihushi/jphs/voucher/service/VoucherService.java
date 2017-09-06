package com.jinpaihushi.jphs.voucher.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.voucher.model.Voucher;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-07-14 14:01:47
 * @version 1.0
 */
public interface VoucherService extends BaseService<Voucher> {

    Page<Voucher> getList(Voucher voucher);

    /**
     * 获取下单页用户可用的优惠券
     * @param pricePartId 站点商品的价格id
     * @param goodsId 商品id
     * @param userId 用户id
     * @return
     */
    List<Map<String, Object>> getUservoucher(String pricePartId, String goodsId, String userId, String nurseId);

    /**
     * 个人中心所有优惠券
     * @param userId 用户id
     * @param type 1.现金卷2.满减卷3.折扣卷
     * @param status 1.已使用2.已过去 3.可用
     * @return
     */
    List<Map<String, Object>> getUserAllvoucher(String userId, Integer type, Integer status);

    /**
     * 计算使用优惠券之后的商品价格
     * @param voucherUseId 用户优惠券id
     * @param pricePartId 所选商品价格id
     * @return
     */
    Double getGoodsPrice(String voucherUseId, String pricePartId, String nurseId);

    /**
     * 验证优惠券是否可用
     * @param voucherUseId
     * @param pricePartId
     * @return
     */
    boolean verificationVoucher(String voucherUseId, String pricePartId);

    /**
     * 判断是否拥有此优惠券
     * @param voucherUseId
     * @return
     */
    boolean isHaveVoucher(String voucherUseId, String userId);
}