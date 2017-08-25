package com.jinpaihushi.jphs.commodity.service.impl;

<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityOrderDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityOrderInfoDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityPriceDao;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.jphs.commodity.model.CommodityOrderInfo;
import com.jinpaihushi.jphs.commodity.model.CommodityPrice;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.jphs.user.dao.UserAddressDao;
import com.jinpaihushi.jphs.user.dao.UserDao;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.model.UserAddress;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:02:59
 * @version 1.0
 */
@Service("commodityOrderService")
public class CommodityOrderServiceImpl extends BaseServiceImpl<CommodityOrder>implements CommodityOrderService {

	@Autowired
	private CommodityOrderDao commodityOrderDao;
	@Autowired
	private CommodityDao commodityDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CommodityOrderInfoDao commodityOrderInfoDao;
	@Autowired
	private CommodityPriceDao commodityPriceDao;

	@Autowired
	private UserAddressDao userAddressDao;

	@Override
	protected BaseDao<CommodityOrder> getDao() {
		return commodityOrderDao;
	}

	@Override
	public String createCommodityOrder(String userId, String commodityIds, String userAddressId, String cpIds,
			String guideId, Integer number, String remark) {
		try {
			String[] commodityIdArr = commodityIds.split(",");
			String[] cpIdArr = cpIds.split(",");
			Set<String> set = new HashSet<String>();
			Map<String, List<String>> map = new HashMap<String, List<String>>();

			for (int i = 0; i < commodityIdArr.length; i++) {
				Commodity commodity = commodityDao.loadById(commodityIdArr[i]);
				set.add(commodity.getBusinessId());
			}

			for (String businessId : set) {
				for (int i = 0; i < commodityIdArr.length; i++) {
					List<String> list = new ArrayList<String>();
					Commodity commodity = commodityDao.loadById(commodityIdArr[i]);
					if (commodity.getBusinessId() == businessId) {
						list.add(commodityIdArr[i]+","+cpIdArr[i]);
					}
					map.put(businessId, list);
				}
			}

			Set<String> keys = map.keySet();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				
				String id = UUID.randomUUID().toString();
				// 按时间格式生成orderNo
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String orderNo = sdf.format(date);
				
				String key = iterator.next();
				List<String> arrayList = map.get(key);
				UserAddress userAddress = userAddressDao.loadById(userAddressId);
				User user = userDao.loadById(userId);
				for (String comAndCp : arrayList) {
					String[] comAndCpArr = comAndCp.split(",");

					Commodity commodity = commodityDao.loadById(comAndCpArr[0]);
					 
					
					CommodityPrice commodityPrice = commodityPriceDao.loadById(comAndCpArr[1]);

					CommodityOrder commodityOrder = new CommodityOrder();
					commodityOrder.setId(id);
					commodityOrder.setOrderNo(orderNo);
					commodityOrder.setPayPrice(commodityPrice.getPrice());
					commodityOrder.setProtectDay(commodity.getProtectDay());
					commodityOrder.setVoucherUseId("");
					// commodityOrder.setVoucherPrice(0);
					commodityOrder.setSchedule(0);
					commodityOrder.setStatus(0);
					commodityOrder.setAddress(userAddress.getProvince() + "，" + userAddress.getCity() + "，"
							+ userAddress.getArea() + "，");
					commodityOrder.setDetailAddress(userAddress.getDetailaddress());
					commodityOrder.setCreatorId(userId);
					commodityOrder.setCreatorName(user.getName());
					commodityOrder.setCreateTime(date);

					int insert = commodityOrderDao.insert(commodityOrder);

					Map<String, Object> modelMap = new HashMap<>();
					modelMap.put("orderNo", orderNo);
					CommodityOrder comObj = commodityOrderDao.getObjectByOrder(modelMap);

					if (insert < 0) {
						return "创建失败";
					}
					// 创建详细订单
					CommodityOrderInfo commodityOrderInfo = new CommodityOrderInfo();

					commodityOrderInfo.setId(UUID.randomUUID().toString());
					commodityOrderInfo.setCommodityOrderId(comObj.getId());
					commodityOrderInfo.setCommodityId(comAndCpArr[0]);
					commodityOrderInfo.setUserId(guideId);
					commodityOrderInfo.setProfit(commodityPrice.getProfit());
					commodityOrderInfo.setTitle(commodity.getTitle());
					commodityOrderInfo.setOldPrice(commodityPrice.getOldPrice());
					commodityOrderInfo.setPrice(commodityPrice.getPrice());
					commodityOrderInfo.setCommodityPriceName(commodityPrice.getName());
					commodityOrderInfo.setCommodityModel(commodity.getModel());
					commodityOrderInfo.setNumber(number);
					commodityOrderInfo.setRemark(remark);
					commodityOrderInfo.setCreatorId(userId);
					commodityOrderInfo.setCreatorName(user.getName());
					commodityOrderInfo.setCreateTime(date);
					int insert2 = commodityOrderInfoDao.insert(commodityOrderInfo);
					if (insert2 < 0) {
						return "创建失败";
					} else {
						return orderNo;
					}
				}
			}

		/*		for (int i = 0; i < commodityIdArr.length; i++) {
					String id = UUID.randomUUID().toString();
					// 按时间格式生成orderNo
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String orderNo = sdf.format(date);

					User user = userDao.loadById(userId);

					Commodity commodity = commodityDao.loadById(commodityIdArr[i]);
					 
					UserAddress userAddress = userAddressDao.loadById(userAddressId);
					CommodityPrice commodityPrice = commodityPriceDao.loadById(cpIdArr[i]);

					// 创建主订单
					CommodityOrder commodityOrder = new CommodityOrder();
					commodityOrder.setId(id);
					commodityOrder.setOrderNo(orderNo);
					commodityOrder.setPayPrice(commodityPrice.getPrice());
					commodityOrder.setProtectDay(commodity.getProtectDay());
					commodityOrder.setVoucherUseId("");
					// commodityOrder.setVoucherPrice(0);
					commodityOrder.setSchedule(0);
					commodityOrder.setStatus(0);
					commodityOrder.setAddress(userAddress.getProvince() + "，" + userAddress.getCity() + "，"
							+ userAddress.getArea() + "，");
					commodityOrder.setDetailAddress(userAddress.getDetailaddress());
					commodityOrder.setCreatorId(userId);
					commodityOrder.setCreatorName(user.getName());
					commodityOrder.setCreateTime(date);

					int insert = commodityOrderDao.insert(commodityOrder);

					Map<String, Object> modelMap = new HashMap<>();
					modelMap.put("orderNo", orderNo);
					CommodityOrder comObj = commodityOrderDao.getObjectByOrder(modelMap);

					if (insert < 0) {
						return "创建失败";
					}
					// 创建详细订单
					CommodityOrderInfo commodityOrderInfo = new CommodityOrderInfo();

					commodityOrderInfo.setId(UUID.randomUUID().toString());
					commodityOrderInfo.setCommodityOrderId(comObj.getId());
					commodityOrderInfo.setCommodityId(commodityIdArr[i]);
					commodityOrderInfo.setUserId(guideId);
					commodityOrderInfo.setProfit(commodityPrice.getProfit());
					commodityOrderInfo.setTitle(commodity.getTitle());
					commodityOrderInfo.setOldPrice(commodityPrice.getOldPrice());
					commodityOrderInfo.setPrice(commodityPrice.getPrice());
					commodityOrderInfo.setCommodityPriceName(commodityPrice.getName());
					commodityOrderInfo.setCommodityModel(commodity.getModel());
					commodityOrderInfo.setNumber(number);
					commodityOrderInfo.setRemark(remark);
					commodityOrderInfo.setCreatorId(userId);
					commodityOrderInfo.setCreatorName(user.getName());
					commodityOrderInfo.setCreateTime(date);
					int insert2 = commodityOrderInfoDao.insert(commodityOrderInfo);
					if (insert2 < 0) {
						return "创建失败";
					} else {
						return orderNo;
					}
				}
			*/
		} catch (Exception e) {

		}
		return "创建失败";
	}

	@Override
	public String cancelShopOrder(String id) {

		CommodityOrder comObj = commodityOrderDao.loadById(id);

		Date date = new Date();
		comObj.setStatus(-1);
		comObj.setConfirmTime(date);
		int result = commodityOrderDao.update(comObj);

		CommodityOrderInfo commodityOrderInfo = new CommodityOrderInfo();
		commodityOrderInfo.setStatus(-1);
		commodityOrderInfo.setCommodityOrderId(id);
		result = commodityOrderInfoDao.updateByOrderNo(commodityOrderInfo);
		if (result > 0) {
			return "1";
		} else {
			return "0";
		}

	}

	@Override
	public List<CommodityOrder> getOrderList(String userId, String schedule) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("schedule", schedule);
		List<CommodityOrder> orderList = commodityOrderDao.getOrderList(map);

		for (int i = 0; i < orderList.size(); i++) {
			String id = orderList.get(i).getId();
			int allNumber = commodityOrderInfoDao.getAllNumber(id);
			orderList.get(i).setCount(allNumber);

			List<CommodityOrderInfo> coiList = orderList.get(i).getCoiList();
			Double payment = 0.0;
			for (int j = 0; j < coiList.size(); j++) {
				Integer number = coiList.get(j).getNumber();
				Double price = coiList.get(j).getPrice();
				payment += (price * number);
			}
			orderList.get(i).setPayment(payment);
		}

		return orderList;
	}

	@Override
	public CommodityOrder getOrderDetail(String orderId) {

		CommodityOrder commodityOrder = commodityOrderDao.getOrderDetail(orderId);

		return commodityOrder;
	}

	@Override
	public Integer updateShopOrderSchedule(CommodityOrder commodityOrder) {

		Integer result = commodityDao.updateShopOrderSchedule(commodityOrder);

		// 修改状态成功且为退货，取消订单
		if (result > 0) {
			if (commodityOrder.getSchedule() < 0) {
				CommodityOrderInfo coi = new CommodityOrderInfo();
				coi.setCommodityOrderId(commodityOrder.getId());
				coi.setStatus(-1);
				result = commodityOrderInfoDao.updateByOrderNo(coi);
			}
		}

		return result;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.dao.CommodityOrderDao;
import com.jinpaihushi.jphs.commodity.model.CommodityOrder;
import com.jinpaihushi.jphs.commodity.service.CommodityOrderService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-08-09 09:02:59
 * @version 1.0
 */
@Service("commodityOrderService")
public class CommodityOrderServiceImpl extends BaseServiceImpl<CommodityOrder> implements CommodityOrderService{

	@Autowired
	private CommodityOrderDao commodityOrderDao;
	
	@Override
	protected BaseDao<CommodityOrder> getDao(){
		return commodityOrderDao;
>>>>>>> branch 'master1' of https://github.com/120591516/jphs-parent.git
	}

}