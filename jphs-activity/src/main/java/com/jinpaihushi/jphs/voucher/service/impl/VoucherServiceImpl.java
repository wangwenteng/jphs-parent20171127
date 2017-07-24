package com.jinpaihushi.jphs.voucher.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.goods.dao.GoodsDao;
import com.jinpaihushi.jphs.goods.model.Goods;
import com.jinpaihushi.jphs.price.dao.PricePartDao;
import com.jinpaihushi.jphs.price.model.PricePart;
import com.jinpaihushi.jphs.voucher.dao.VoucherDao;
import com.jinpaihushi.jphs.voucher.model.Voucher;
import com.jinpaihushi.jphs.voucher.service.VoucherService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

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
	public List<Map<String,Object>> getUserAllvoucher(String userId, Integer type) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("type", type);
		List<Map<String,Object>> list = voucherDao.getUserAllVocher(map);
		return list;
	}

}