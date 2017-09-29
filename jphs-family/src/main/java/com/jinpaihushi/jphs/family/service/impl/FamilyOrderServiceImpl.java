package com.jinpaihushi.jphs.family.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.account.dao.AccountDao;
import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.family.dao.FamilyCardDao;
import com.jinpaihushi.jphs.family.dao.FamilyMemberDao;
import com.jinpaihushi.jphs.family.dao.FamilyModeDao;
import com.jinpaihushi.jphs.family.dao.FamilyOrderDao;
import com.jinpaihushi.jphs.family.dao.FamilyPackageDao;
import com.jinpaihushi.jphs.family.model.FamilyCard;
import com.jinpaihushi.jphs.family.model.FamilyMember;
import com.jinpaihushi.jphs.family.model.FamilyMode;
import com.jinpaihushi.jphs.family.model.FamilyOrder;
import com.jinpaihushi.jphs.family.model.FamilyPackage;
import com.jinpaihushi.jphs.family.service.FamilyOrderService;
import com.jinpaihushi.jphs.transaction.dao.TransactionDao;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.user.dao.UserDao;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.pay.alipay.AlipaySign;
import com.jinpaihushi.pay.wechatpay.WechatPay;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.DateUtils;
import com.jinpaihushi.utils.DoubleUtils;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @version 1.0
 */
@Service("familyOrderService")
public class FamilyOrderServiceImpl extends BaseServiceImpl<FamilyOrder> implements FamilyOrderService {

	@Autowired
	private FamilyOrderDao familyOrderDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private FamilyModeDao familyModeDao;
	@Autowired
	private FamilyPackageDao familyPackageDao;
	@Autowired
	private TransactionDao transactionDao;
	@Autowired
    private AccountDao accountDao;
	@Autowired
	private FamilyCardDao familyCardDao;
	@Autowired
	private FamilyMemberDao familyMemberDao;

	@Autowired
	private PlatformTransactionManager txManager;// 创建事务管理器
	@Override
	protected BaseDao<FamilyOrder> getDao() {
		return familyOrderDao;
	}

	public int userWechatFamilyOrder(String openId) {
		User user = new User();
		user.setOpenId(openId);
		user.setStatus(1);
		List<User> user_list = userDao.list(user);
		if (user_list.size() < 1) {
			return 0;
		}
		FamilyOrder familyOrder = new FamilyOrder();
		familyOrder.setCreatorId(user_list.get(0).getId());
		familyOrder.setStatus(1);
		List<FamilyOrder> familyOrder_list = familyOrderDao.list(familyOrder);
		if (familyOrder_list.size() < 1) {
			return 0;
		}
		return 1;
	}
	
	public int userIdFamilyOrder(String id) {
		User user = new User();
		user.setId(id);
		user.setStatus(1);
		List<User> user_list = userDao.list(user);
		if (user_list.size() < 1) {
			return 0;
		}
		FamilyOrder familyOrder = new FamilyOrder();
		familyOrder.setCreatorId(user_list.get(0).getId());
		familyOrder.setStatus(1);
		List<FamilyOrder> familyOrder_list = familyOrderDao.list(familyOrder);
		if (familyOrder_list.size() < 1) {
			return 0;
		}
		return 1;
	}

	public byte[] setOrderFamily(String userId, String Ip, String name, String promoCode, String wxNo, String fmId,
			String openId, int payType,String code,String card) {
		FamilyMode familyMode = new FamilyMode();
		familyMode.setId(fmId);
		FamilyMode fm = familyModeDao.load(familyMode);

		FamilyPackage familyPackage = new FamilyPackage();
		familyPackage.setId(fm.getFamilyPackageId());
		FamilyPackage fp = familyPackageDao.load(familyPackage);
		if(fm.getAccessMode() == 1){
			
			 //	支付宝支付
            if (payType == 1) {
                Util.debugLog.debug("order.createOrder.json  支付宝支付");
                if (StringUtils.isEmpty("") || StringUtils.isEmpty("") || StringUtils.isEmpty("")
                        || "" == null) {
                    try {
						return JSONUtil.toJSONResult(0, "参数不能为空", null);
					} catch (IOException e) {
					}
                }

                JSONObject sParaTemp = new JSONObject();
                sParaTemp.put("_input_charset", "utf-8");
                sParaTemp.put("body", "");
                sParaTemp.put("notify_url", "");
                sParaTemp.put("out_trade_no", "");
                sParaTemp.put("payment_type", "1");
                sParaTemp.put("return_url", "");
                sParaTemp.put("show_url", "");
                sParaTemp.put("subject", "");
                sParaTemp.put("total_fee", "");
                sParaTemp.put("serviceType", "");

                byte[] s = AlipaySign.getAlisign(sParaTemp.toString(), "PRIVATE_KEY", "4");
                return s;
            }else 
			// 微信web支付
			if (payType == 3) {
				JSONObject json = new JSONObject();
				json.put("out_trade_no", Common.getOrderNumber());
				json.put("body", fp.getTitle());
				json.put("price", fp.getPrice() * 100);
				json.put("CREATE_IP", Ip);
				json.put("serviceType", "3");

				JSONObject familyData = new JSONObject();
				familyData.put("ud", userId);
				familyData.put("wo", wxNo);
				familyData.put("fd", fmId);
				familyData.put("ne", name);
				familyData.put("pp", fp.getPrice());
				familyData.put("code", code);

				json.put("familyData", familyData.toString());
				try {
					byte[] s = WechatPay.weixin_webpay(json.toString());
					return s;
				} catch (Exception e) {
				}
			} else if(payType == 4){
				// 微信公众号支付
				JSONObject json = new JSONObject();
				json.put("out_trade_no", Common.getOrderNumber());
				json.put("body", fp.getTitle());
				json.put("price", fp.getPrice() * 100);
				json.put("CREATE_IP", Ip);
				json.put("serviceType", "3");

				JSONObject familyData = new JSONObject();
				familyData.put("ud", userId);
				familyData.put("wo", wxNo);
				familyData.put("fd", fmId);
				familyData.put("ne", name);
				familyData.put("code", code);
				json.put("familyData", familyData.toString());
				json.put("OPENID", openId);
				try {
					byte[] s = WechatPay.weixin_wechatpay(json.toString());
					return s;
				} catch (Exception e) {
				}
			}else if(payType == 5){
				// 余额支付
				return balancePayment(fmId,wxNo,fp.getPrice(),userId,name,fp.getId(),fp.getTitle(),code);
			}
		}else if(fm.getAccessMode() == 2){
			
        	if (StringUtils.isEmpty(card) ) {
                try {
					return JSONUtil.toJSONResult(0, "参数不能为空", null);
				} catch (IOException e) {
				}
            }else{
            	return duFamily(fmId,wxNo,fp.getPrice(),userId,name,fp.getId(),fp.getTitle(),fm.getDay(),code,card);
            }
			
		}else if(fm.getAccessMode() == 3){
			return freeFamily(fmId,wxNo,fp.getPrice(),userId,name,fp.getId(),fp.getTitle(),fm.getDay(),code);
		}
		
		try {
			return JSONUtil.toJSONResult(0, "该活动已结束.", null);
		} catch (IOException e) {
		}
		return null;
	}
	
	public byte [] duFamily(String fmId, String wxNo, Double payParice, String userId,String name,String fpId,String fpTitle,
			int day,String code,String card){
		TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
        //事务模板
	    public String doInTransaction(final TransactionStatus status) {
	         try {
	        	 FamilyCard familyCard = new FamilyCard();
	        	 familyCard.setCode(card);
	        	 List<FamilyCard> familyCard_list = familyCardDao.list(familyCard);
	        	 if(familyCard_list == null || familyCard_list.equals("") || familyCard_list.size() < 1){
	        		 return "2";
	        	 }
	        	 if (StringUtils.isEmpty(String.valueOf(familyCard_list.get(0).getStatus())) || familyCard_list.get(0).getStatus() != 0 ) {
	        		 return "7";
	        	 }
	        	 
	        	FamilyOrder familyOrder = new FamilyOrder();
				familyOrder.setId(UUIDUtils.getId());
				familyOrder.setFamilyPackageId(fpId);
				familyOrder.setPayPrice(payParice);
				familyOrder.setWxNo(wxNo);
				familyOrder.setEndTime(DateUtils.plusDay(day,new Date()));
				familyOrder.setCode(code);
				familyOrder.setStatus(1);
				familyOrder.setCreateTime(new Date());
				familyOrder.setCreatorId(userId);
				familyOrder.setCreatorName(name);
				int f = familyOrderDao.insert(familyOrder);
				if(f < 1){
					status.setRollbackOnly();// 回滚
					return "3";
				}
				Transaction transaction = new Transaction();
				transaction.setId(UUID.randomUUID().toString());
				transaction.setOrderId(familyOrder.getId());
				transaction.setAmount(payParice);
				transaction.setScore((new Double(payParice)).intValue());
				transaction.setOperate(3);
				transaction.setOperateSource(2);
				transaction.setRemark(fpTitle);
				transaction.setWithdraw(0);
				transaction.setPayType(2);
				transaction.setOutTradeNo("");
				transaction.setCreatorId(userId);
				transaction.setCreatorName(name);
				transaction.setCreateTime(new Date());
				transaction.setStatus(1);
				transaction.setType(2);
				// 记录日志-debug
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("updateWechatCommodityOrderStutas;transaction="
							+ JSONObject.fromObject(transaction).toString());
				}
				int t = transactionDao.insert(transaction);
				if(t < 1){
					status.setRollbackOnly();// 回滚
					return "4";
				}
				familyCard_list.get(0).setUseTime(new Date());
				familyCard_list.get(0).setRemark(userId);
				familyCard_list.get(0).setCreatorId(userId);
				familyCard_list.get(0).setCreatorName(name);
				familyCard_list.get(0).setStatus(1);
				int fcd = familyCardDao.update(familyCard_list.get(0));
				if(fcd < 1){
					status.setRollbackOnly();// 回滚
					return "5";
				}
				User user = new User();
				user.setId(userId);
				user.setStatus(1);
				user = userDao.load(user);
				if(user !=null){
					FamilyMember familyMember= new FamilyMember();
					familyMember.setId(UUIDUtils.getId());
					familyMember.setName(name);
					familyMember.setPhone(user.getPhone());
					familyMember.setRelation("自己");
					familyMember.setStatus(1);
					familyMember.setCreateTime(new Date());
					familyMember.setCreatorId(userId);
					familyMember.setCreatorName(user.getName());
					familyMemberDao.insert(familyMember);
				}
				
	         } catch (Exception e) {
	                e.printStackTrace();
	                //日志打印区
	                status.setRollbackOnly();//回滚
	                return "0";
	         }
	           return "1";
	        }
	    });
        String msg = "兑换成功！";
        int rsi = Integer.parseInt(rs);
        if(rsi == 0){
        	msg = "兑换失败！";
        }else if(rsi == 2){
        	msg = "兑换码不正确！";
        }else if(rsi == 3){
        	msg = "兑换失败，请通过正确渠道兑换！";
        }else if(rsi == 4){
        	msg = "兑换失败，请通过正确渠道兑换！！";
        }else if(rsi == 5){
        	msg = "兑换失败，请通过正确渠道兑换！！！";
        }else if(rsi == 7){
        	msg = "兑换失败,兑换码已失效！！！";
        }
        
        try {
			return JSONUtil.toJSONResult(rsi, msg, null);
		} catch (IOException e) {
		}
        return null;
	}
	
	/**
	 * 免费领取
	 */
	public byte[] freeFamily(String fmId, String wxNo, Double payParice, String userId,String name,String fpId,String fpTitle,int day,String code){
		TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
        //事务模板
	    public String doInTransaction(final TransactionStatus status) {
	         try {
                 
                FamilyOrder familyOrder = new FamilyOrder();
				familyOrder.setId(UUIDUtils.getId());
				familyOrder.setFamilyPackageId(fpId);
				familyOrder.setPayPrice(payParice);
				familyOrder.setWxNo(wxNo);
				familyOrder.setEndTime(DateUtils.plusDay(day,new Date()));
				familyOrder.setCode(code);
				familyOrder.setStatus(1);
				familyOrder.setCreateTime(new Date());
				familyOrder.setCreatorId(userId);
				familyOrder.setCreatorName(name);
				int f = familyOrderDao.insert(familyOrder);
				
				if(f < 1){
					status.setRollbackOnly();// 回滚
					return "5";
				}
				Transaction transaction = new Transaction();
				transaction.setId(UUID.randomUUID().toString());
				transaction.setOrderId(familyOrder.getId());
				transaction.setAmount(payParice);
				transaction.setScore((new Double(payParice)).intValue());
				transaction.setOperate(3);
				transaction.setOperateSource(2);
				transaction.setRemark(fpTitle);
				transaction.setWithdraw(0);
				transaction.setPayType(2);
				transaction.setOutTradeNo("");
				transaction.setCreatorId(userId);
				transaction.setCreatorName(name);
				transaction.setCreateTime(new Date());
				transaction.setStatus(1);
				transaction.setType(2);
				// 记录日志-debug
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("updateWechatCommodityOrderStutas;transaction="
							+ JSONObject.fromObject(transaction).toString());
				}
				int t = transactionDao.insert(transaction);
				if(t < 1){
					status.setRollbackOnly();// 回滚
					return "6";
				}
				User user = new User();
				user.setId(userId);
				user.setStatus(1);
				user = userDao.load(user);
				if(user !=null){
					FamilyMember familyMember= new FamilyMember();
					familyMember.setId(UUIDUtils.getId());
					familyMember.setName(name);
					familyMember.setPhone(user.getPhone());
					familyMember.setRelation("自己");
					familyMember.setStatus(1);
					familyMember.setCreateTime(new Date());
					familyMember.setCreatorId(userId);
					familyMember.setCreatorName(user.getName());
					familyMemberDao.insert(familyMember);
				}
	         } catch (Exception e) {
	                e.printStackTrace();
	                //日志打印区
	                status.setRollbackOnly();//回滚
	                return "0";
	         }
	           return "1";
	        }
	    });
        String msg = "支付成功";
        int rsi = Integer.parseInt(rs);
        if (rsi == 0) {
            msg = "支付失败，请刷新重试";
        }
        else if (rsi == 5) {
            msg = "支付失败，订单数据异常";
        }
        else if (rsi == 6) {
            msg = "支付失败，订单数据异常-1";
        }
        try {
            return JSONUtil.toJSONResult(rsi, msg, null);
        }
        catch (IOException e) {
        }
        return null;
	}
	
	/**
     * 余额支付
     * @return
     */
    public byte[] balancePayment(String fmId, String wxNo, Double payParice, String userId,String name,String fpId,String fpTitle,String code) {
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
        //事务模板
	    public String doInTransaction(final TransactionStatus status) {
	         try {
	        	 Account model = new Account();
                 model.setCreatorId(userId);
                 Account account = accountDao.load(model);
                 if (null == account || "".equals(account)) {
                     return "10";
                 }
                 if (null == account.getBalance() || "".equals(account.getBalance())) {
                     return "11";
                 }
                 /**
                  * 用于余额不足
                  */
                 if (DoubleUtils.sub(account.getBalance(), payParice) < 0) {
                     return "3";
                 }
                 /**
                  * 用户余额减去-订单金额
                  * 更新用户余额
                  */
                 Double balance = DoubleUtils.sub(account.getBalance(), payParice);
                 account.setBalance(balance);
                 int a = accountDao.update(account);
                 if (a < 1) {
                     status.setRollbackOnly();//回滚
                     return "4";
                 }
                 FamilyMode familyMode = new FamilyMode();
				familyMode.setId(fmId);
				FamilyMode fm = familyModeDao.load(familyMode);
				if(fm == null || fm.equals("")){
					status.setRollbackOnly();// 回滚
					return "2";
				}
                FamilyOrder familyOrder = new FamilyOrder();
				familyOrder.setId(UUIDUtils.getId());
				familyOrder.setFamilyPackageId(fpId);
				familyOrder.setPayPrice(payParice);
				familyOrder.setWxNo(wxNo);
				familyOrder.setEndTime(DateUtils.plusDay(fm.getDay(),new Date()));
				familyOrder.setCode(code);
				familyOrder.setStatus(1);
				familyOrder.setCreateTime(new Date());
				familyOrder.setCreatorId(userId);
				familyOrder.setCreatorName(name);
				int f = familyOrderDao.insert(familyOrder);
				
				if(f < 1){
					status.setRollbackOnly();// 回滚
					return "5";
				}
				Transaction transaction = new Transaction();
				transaction.setId(UUID.randomUUID().toString());
				transaction.setOrderId(familyOrder.getId());
				transaction.setAmount(payParice);
				transaction.setScore((new Double(payParice)).intValue());
				transaction.setOperate(3);
				transaction.setOperateSource(2);
				transaction.setRemark(fpTitle);
				transaction.setWithdraw(0);
				transaction.setPayType(2);
				transaction.setOutTradeNo("");
				transaction.setCreatorId(userId);
				transaction.setCreatorName(name);
				transaction.setCreateTime(new Date());
				transaction.setStatus(1);
				transaction.setType(2);
				// 记录日志-debug
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("updateWechatCommodityOrderStutas;transaction="
							+ JSONObject.fromObject(transaction).toString());
				}
				int t = transactionDao.insert(transaction);
				if(t < 1){
					status.setRollbackOnly();// 回滚
					return "6";
				}
				User user = new User();
				user.setId(userId);
				user.setStatus(1);
				user = userDao.load(user);
				if(user !=null){
					FamilyMember familyMember= new FamilyMember();
					familyMember.setId(UUIDUtils.getId());
					familyMember.setName(name);
					familyMember.setPhone(user.getPhone());
					familyMember.setRelation("自己");
					familyMember.setStatus(1);
					familyMember.setCreateTime(new Date());
					familyMember.setCreatorId(userId);
					familyMember.setCreatorName(user.getName());
					familyMemberDao.insert(familyMember);
				}
	         } catch (Exception e) {
	                e.printStackTrace();
	                //日志打印区
	                status.setRollbackOnly();//回滚
	                return "0";
	         }
	           return "1";
	        }
	    });
        String msg = "支付成功";
        int rsi = Integer.parseInt(rs);
        if (rsi == 0) {
            msg = "支付失败，请刷新重试";
        } else if (rsi == 2) {
            msg = "支付失败";
        }
        else if (rsi == 3) {
            msg = "支付失败，余额不足";
        }
        else if (rsi == 4) {
            msg = "支付失败，订单异常";
        }
        else if (rsi == 5) {
            msg = "支付失败，订单数据异常";
        }
        else if (rsi == 6) {
            msg = "支付失败，订单数据异常-1";
        }
        else if (rsi == 10) {
            msg = "支付失败，用户账户异常";
        }
        else if (rsi == 11) {
            msg = "支付失败，用户账户余额异常";
        }

        try {
            return JSONUtil.toJSONResult(rsi, msg, null);
        }
        catch (IOException e) {
        }
        return null;
    }

	// 商品支付回调
	@SuppressWarnings({ "static-access" })
	public boolean updateWechatfamilyOrderStutas(SortedMap<Object, Object> packageParams) {

		TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
		String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
			// 事务模板
			public String doInTransaction(final TransactionStatus status) {
				try {
						// 这里是支付成功
						////////// 执行自己的业务逻辑////////////////
						String mch_id = (String) packageParams.get("mch_id");
						String openid = (String) packageParams.get("openid");
						String attach = (String) packageParams.get("attach");
						String is_subscribe = (String) packageParams.get("is_subscribe");
						String out_trade_no = (String) packageParams.get("out_trade_no");
						String total_fee = (String) packageParams.get("total_fee");
						String transaction_id = (String) packageParams.get("transaction_id");
						// 记录日志-debug
						if (Util.debugLog.isDebugEnabled()) {
							Util.debugLog.debug("updateWechatfamilyOrderStutas:mch_id=" + mch_id + ",openid=" + openid
									+ ",is_subscribe=" + is_subscribe + ",out_trade_no=" + out_trade_no + ",total_fee="
									+ total_fee + "-------attach=" + attach + " transaction_id=" + transaction_id);
						}
						JSONObject attach_json = new JSONObject().fromObject(attach);
						if (!attach_json.containsKey("familyData")) {
							status.setRollbackOnly();// 回滚
							return "0";
						}
						JSONObject familyData = attach_json.getJSONObject("familyData");
						// 记录日志-debug
						if (Util.debugLog.isDebugEnabled()) {
							Util.debugLog.debug("updateWechatfamilyOrderStutas:familyData=" + familyData);
						}
						String userId = familyData.getString("ud");
						String wxNo = familyData.getString("wo");
						String fmId = familyData.getString("fd");
						String name = familyData.getString("ne");
						String code = familyData.getString("code");

						FamilyMode familyMode = new FamilyMode();
						familyMode.setId(fmId);
						FamilyMode fm = familyModeDao.load(familyMode);
						if(fm == null || fm.equals("")){
							status.setRollbackOnly();// 回滚
							return "2";
						}
						FamilyPackage familyPackage = new FamilyPackage();
						familyPackage.setId(fm.getFamilyPackageId());
						FamilyPackage fp = familyPackageDao.load(familyPackage);
						if(fp == null || fp.equals("")){
							status.setRollbackOnly();// 回滚
							return "3";
						}
						FamilyOrder familyOrder = new FamilyOrder();
						familyOrder.setId(UUIDUtils.getId());
						familyOrder.setFamilyPackageId(fp.getId());
						familyOrder.setPayPrice(fp.getPrice());
						familyOrder.setWxNo(wxNo);
						familyOrder.setEndTime(DateUtils.plusDay(fm.getDay(),new Date()));
						familyOrder.setCode(code);
						familyOrder.setStatus(1);
						familyOrder.setCreateTime(new Date());
						familyOrder.setCreatorId(userId);
						familyOrder.setCreatorName(name);
						int f = familyOrderDao.insert(familyOrder);
						
						if(f < 1){
							status.setRollbackOnly();// 回滚
							return "4";
						}
						
						Transaction transaction = new Transaction();
						transaction.setId(UUID.randomUUID().toString());
						transaction.setOrderId(familyOrder.getId());
						transaction.setAmount(fp.getPrice());
						transaction.setScore((new Double(fp.getPrice())).intValue());
						transaction.setOperate(3);
						transaction.setOperateSource(2);
						transaction.setRemark(fp.getTitle());
						transaction.setWithdraw(0);
						transaction.setPayType(2);
						transaction.setOutTradeNo(transaction_id);
						transaction.setCreatorId(userId);
						transaction.setCreatorName(name);
						transaction.setCreateTime(new Date());
						transaction.setStatus(1);
						transaction.setType(2);
						// 记录日志-debug
						if (Util.debugLog.isDebugEnabled()) {
							Util.debugLog.debug("updateWechatCommodityOrderStutas;transaction="
									+ JSONObject.fromObject(transaction).toString());
						}
						int t = transactionDao.insert(transaction);
						if(t < 1){
							status.setRollbackOnly();// 回滚
							return "5";
						}
						User user = new User();
						user.setId(userId);
						user.setStatus(1);
						user = userDao.load(user);
						if(user !=null){
							FamilyMember familyMember= new FamilyMember();
							familyMember.setId(UUIDUtils.getId());
							familyMember.setName(name);
							familyMember.setPhone(user.getPhone());
							familyMember.setRelation("自己");
							familyMember.setStatus(1);
							familyMember.setCreateTime(new Date());
							familyMember.setCreatorId(userId);
							familyMember.setCreatorName(user.getName());
							familyMemberDao.insert(familyMember);
						}
				} catch (Exception e) {
					Util.failLog.error("updateWechatCommodityOrderStutas：2e=", e);
					// 日志打印区
					status.setRollbackOnly();// 回滚
					return "7";
				}
				return "1";
			}
		});
		boolean rf = true;
		if(!rs.equals("1")){
			rf = false;
		}
		return rf;
	}
}