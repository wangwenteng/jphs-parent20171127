package com.jinpaihushi.jphs.voucher.service.impl;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.activity.dao.VoucherUseDao;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.goods.dao.GoodsDao;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.price.dao.PricePartDao;
import com.jinpaihushi.jphs.price.model.PricePart;
import com.jinpaihushi.jphs.voucher.dao.VoucherDao;
import com.jinpaihushi.jphs.voucher.model.Voucher;
import com.jinpaihushi.jphs.voucher.model.VoucherRepertory;
import com.jinpaihushi.jphs.voucher.service.VoucherService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.DoubleUtils;

/**
 * 
 * @author yangsong
 * @date 2017-07-14 14:01:47
 * @version 1.0
 */
@Service("voucherService")
public class VoucherServiceImpl extends BaseServiceImpl<Voucher> implements VoucherService {
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private VoucherDao voucherDao;
	@Autowired
	private PricePartDao pricePartDao;
	@Autowired
	private VoucherUseDao voucherUseDao;

	@Override
	protected BaseDao<Voucher> getDao() {
		return voucherDao;
	}

	@Override
	public Page<Voucher> getList(Voucher voucher) {
		// TODO Auto-generated method stub
		return voucherDao.getList(voucher);
	}

	/**
	 * 获取下单页用户可用的优惠券
	 * 
	 * @param pricePartId
	 *            站点商品的价格id
	 * @param goodsId
	 *            商品id
	 * @param userId
	 *            用户id
	 * @return
	 */
	@SuppressWarnings("unused")
	@Override
	public List<Voucher> getUservoucher(String pricePartId, String goodsId, String userId) {
		// 根据商品id获取商品的信息
		Goods goods = goodsDao.loadById(goodsId);
		// 根据pricePartId 下单商品的价格
		PricePart pricePart = pricePartDao.loadById(pricePartId);
		// 商品的销售价
		Double salePrice = pricePart.getPrice();
		// 获取用户的优惠券
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("goodsId", goodsId);
		map.put("productId", goods.getProductId());
		if (goods != null) {
			List<Voucher> vocherList = voucherDao.getUserVocher(map);
			for (int i = 0; i < vocherList.size(); i++) {
				// 优惠券的现金券是所有商品可用
				if (vocherList.get(i).getType() != 1) {
					// 如果是满减券
					if (vocherList.get(i).getType() == 2) {
						// 判断需要满足的金额是否达到要求
						if (vocherList.get(i).getVoucherRepertory().getConditionAmount() > salePrice) {
							// 不满足要求移除改优惠券
							vocherList.remove(i);
						}
					}
					if (vocherList.get(i).getType() == 3) {
						if (vocherList.get(i).getVoucherRepertory().getDiscountAmount() > salePrice) {
							// 不满足要求移除改优惠券
							vocherList.remove(i);
						}
					}
				}
			}
			return vocherList;
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getUserAllvoucher(String userId, Integer type, Integer status) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("type", type);
		map.put("status", status);
		List<Map<String, Object>> list = voucherDao.getUserAllVocher(map);
		return list;
	}

	@Override
	public Double getGoodsPrice(String voucherUseId, String pricePartId) {
		// 获取商品的价格信息
		PricePart pricePart = pricePartDao.loadById(pricePartId);
		// 获取优惠券的信息
		Voucher voucher = voucherDao.getVocherByUser(voucherUseId);
		//判断优惠券类型
		if(voucher!=null){
			VoucherRepertory repertory = voucher.getVoucherRepertory();
			if(voucher.getType()==1){
				return DoubleUtils.sub(pricePart.getPrice(), repertory.getAmount());
			}else if(voucher.getType()==2){
				if(pricePart.getPrice()>repertory.getAmount()){
					return DoubleUtils.sub(pricePart.getPrice(), repertory.getAmount());
				}
			}else if(voucher.getType()==3){
				if(pricePart.getPrice()>repertory.getAmount()){
					return DoubleUtils.mul(pricePart.getPrice(), repertory.getAmount());
				}
			}
		}
		return pricePart.getPrice();
	}

	@Override
	public boolean verificationVoucher(String voucherUseId, String pricePartId) {
		// 商品信息
		Goods goods = goodsDao.getGoodsByPricePart(pricePartId);
		// 获取优惠券的信息
		Voucher voucher = voucherDao.getVocherByUser(voucherUseId);
		// 进行判断
		if (voucher != null) {
			if (voucher.getProductId() != null) {
				if (Arrays.asList(voucher.getProductId().split(",")).contains(goods.getProductId())) {
					if (voucher.getGoodsId() != null) {
						if (Arrays.asList(voucher.getProductId().split(",")).contains(goods.getProductId()))
							return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean isHaveVoucher(String voucherUseId, String userId) {
		VoucherUse voucherUse = new VoucherUse();
		voucherUse.setId(voucherUseId);
		voucherUse.setCreatorId(userId);
		VoucherUse result = voucherUseDao.load(voucherUse);
		if (result != null)
			return true;
		return false;
	}

}