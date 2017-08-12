package com.jinpaihushi.jphs.information.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.information.dao.InformationDao;
import com.jinpaihushi.jphs.information.model.Information;
import com.jinpaihushi.jphs.information.service.InformationService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @version 1.0
 */
@Service("informationService")
public class InformationServiceImpl extends BaseServiceImpl<Information> implements InformationService {

	@Autowired
	private InformationDao informationDao;

	@Override
	protected BaseDao<Information> getDao() {
		return informationDao;
	}

	@Override
	public List<Map<String, Object>> getLatestformation(String channelId, Integer num) {
		Map<String, Object> query = new HashMap<>();
		query.put("num", num);
		query.put("channelId", channelId);
		List<Map<String, Object>> latestformation = informationDao.queryOrderBy(query);
		return latestformation;
	}

	/**
	 * 获取资讯列表
	 * 
	 * @param channelId
	 *            频道id
	 * @param num
	 *            页数
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getTopList(String channelId, Integer num) {
		Map<String, Object> query = new HashMap<>();
		query.put("top", 1);
		query.put("num", num);
		query.put("channelId", channelId);
		List<Map<String, Object>> top = informationDao.queryOrderBy(query);
		for (Map<String, Object> map : top) {
			String[] strings = ((String) map.get("image")).split(",");
			if (strings.length > 0) {
				map.put("image", strings[0]);
			} else {
				map.put("image", "");
			}
		}
		return top;
	}

	@Override
	public Information getInformationDetail(String id) {
		// 资讯的基本信息
		Information information = informationDao.loadById(id);
		String str = information.getContent().replace("＜", "<");
		str = str.replace("＞", ">");
		str = str.replace("＆", "&");
		information.setContent(str);
		// 资讯的评价信息
		return information;
	}

	public List<Map<String, Object>> listapp(Map<String, Object> query) {
		List<Map<String, Object>> list = informationDao.listapp(query);
		for (Map<String, Object> map : list) {
			String[] strings = ((String) map.get("image")).split(",");
			if (strings.length > 0) {
				map.put("image", strings[0]);
			} else {
				map.put("image", "");
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getCollection(String userId) {
		List<Map<String, Object>> list = informationDao.getCollection(userId);
		for (Map<String, Object> map : list) {
			String[] strings = ((String) map.get("image")).split(",");
			if (strings.length > 0) {
				map.put("image", strings[0]);
			} else {
				map.put("image", "");
			}
		}
		return list;
	}

	@Override
	public int deleteCollection(String inforationIds, String userId) {
		String[] informationId = inforationIds.split(",");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", informationId);
		params.put("userId", userId);
		int i = informationDao.deleteCollection(params);
		return i;
	}
}