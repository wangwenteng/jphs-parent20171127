package com.jinpaihushi.jphs.information.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
     * @param channelId 频道id
     * @param page 页数
     * @return
     */
    @Override
    public Map<String, Object> getInformationList(String channelId, Integer page) {
        Map<String, Object> result = new HashMap<>();
        Information information = new Information();
        if (page == null) {
            Map<String, Object> query = new HashMap<>();
            query.put("top", 1);
            query.put("num", 3);
            query.put("channelId", channelId);
            List<Information> top = informationDao.queryList(query);
            for (Information information2 : top) {
                String[] strings = information2.getImage().split(",");
                if (strings.length > 0) {
                    information2.setImage(strings[0]);
                }
                else {
                    information2.setImage("");
                }
            }
            result.put("top", top);
        }
        information.setInformationChannelId(channelId);
        information.setStatus(1);
        information.setOrderby("create_time DESC");
        if (page == null)
            page = 1;
        PageHelper.startPage(page, 5);
        Page<Information> list = informationDao.query(information);
        for (Information informations : list) {
            String[] strings = informations.getImage().split(",");
            if (strings.length > 0) {
                informations.setImage(strings[0]);
            }
            else {
                informations.setImage("");
            }
            String str = informations.getContent().replace("＜", "<");
            str = str.replace("＞", ">");
            str = str.replace("＆", "&");
            informations.setContent(str);
        }
        PageInfo<Information> pageInfo = new PageInfo<Information>(list);
        result.put("list", pageInfo);
        return result;
    }

    @Override
    public Map<String, Object> getHomeInformation(String channelId) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> query = new HashMap<>();
        query.put("num", 5);
        query.put("channelId", channelId);
        List<Information> top = informationDao.queryList(query);
        result.put("top", top);
        Map<String, Object> mapList = new HashMap<>();
        query.put("top", 1);
        query.put("num", 9);
        query.put("channelId", channelId);
        List<Information> list = informationDao.queryList(query);
        List<Information> listTop = new ArrayList<>();
        for (Information information : list) {
            String[] strings = information.getImage().split(",");
            if (strings.length > 0) {
                information.setImage(strings[0]);
            }
            else {
                information.setImage("");
            }
        }
        if (list.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                if (listTop.size() == 3)
                    break;
                listTop.add(list.get(i));
                list.remove(i);
                i = -1;
            }
            mapList.put("top", listTop);
            mapList.put("list", list);
        }
        else {
            mapList.put("top", list);
            mapList.put("list", null);
        }
        result.put("list", mapList);
        return result;
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
            }
            else {
                map.put("image", "");
            }
        }
        return top;
    }

    @Override
    public Information getInformationDetail(String id) {
        // 资讯的基本信息
        Information information = informationDao.loadById(id);
        String str = information.getContent() == null ? "" : information.getContent().replace("＜", "<");
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
            }
            else {
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
            }
            else {
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