package com.jinpaihushi.jphs.nurse.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.person.model.PersonGroup;
import com.jinpaihushi.jphs.person.model.PersonManager;
import com.jinpaihushi.jphs.person.service.PersonGroupService;
import com.jinpaihushi.jphs.person.service.PersonManagerService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/nurseManagement")
public class NurseManagementController {
    @Autowired
    private PersonManagerService personManagerService;

    @Autowired
    private PersonGroupService personGroupService;

    @ResponseBody
    @RequestMapping(name = "创建分组", path = "/createGroup.json", method = RequestMethod.POST)
    public byte[] createGroup(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            PersonGroup personGroup) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseManagement.createGroup.json");
            }
            if (StringUtils.isEmpty(personGroup.getName()) || personGroup.getIsDefault() == null
                    || StringUtils.isEmpty(personGroup.getCreatorId())
                    || StringUtils.isEmpty(personGroup.getCreatorName())) {
                return JSONUtil.toJSONResult(0, "请核对参数！！", null);
            }
            boolean flag = false;
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(0, "token不能为空", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(collection.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
                // 身份认证失败,返回错误信息
                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            //判斷分組名稱是否存在
            PersonGroup group = new PersonGroup();
            group.setName(personGroup.getName());
            group.setCreatorId(personGroup.getCreatorId());
            group.setStatus(0);
            int i = personGroupService.count(group);
            if (i > 0) {
                return JSONUtil.toJSONResult(0, "改分组已经存在！请不要重复添加！", null);
            }
            personGroup.setIsDefault(0);
            personGroup.setCreateTime(new Date());
            personGroup.setId(UUIDUtils.getId());
            personGroup.setStatus(0);
            String insert = personGroupService.insert(personGroup);
            if (insert.length() < 0) {
                return JSONUtil.toJSONResult(0, "插入失败！", null);
            }
            return JSONUtil.toJSONResult(1, "添加分组成功", null);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("information.collectionInformation.json", e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "编辑分组", path = "/editGroup.json", method = RequestMethod.POST)
    public byte[] editGroup(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, PersonGroup personGroup) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseManagement.editGroup.json");
            }
            if (StringUtils.isEmpty(personGroup.getName()) || StringUtils.isEmpty(personGroup.getId())) {
                return JSONUtil.toJSONResult(0, "请核对参数！！", null);
            }
            boolean flag = false;
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(0, "token不能为空", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(collection.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
                // 身份认证失败,返回错误信息
                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            //判断是否是默认分组
            PersonGroup group = personGroupService.loadById(personGroup.getId());
            if (group.getIsDefault() == 1) {
                return JSONUtil.toJSONResult(0, "默认分组不可以编辑", null);
            }
            //判断分组是否存在
            group = new PersonGroup();
            group.setName(personGroup.getName());
            int i = personGroupService.count(group);
            if (i > 0) {
                return JSONUtil.toJSONResult(0, "该分组已经存在！请不要重复添加！", null);
            }
            flag = personGroupService.update(personGroup);
            if (!flag) {

                return JSONUtil.toJSONResult(0, "编辑失败！", null);
            }
            return JSONUtil.toJSONResult(1, "修改成功", null);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseManagement.editGroup.json", e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "删除分组", path = "/deleteGroup.json")
    public byte[] deleteGroup(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String id) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseManagement.deleteGroup.json id=" + id);
            }
            if (StringUtils.isEmpty(id)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            boolean flag = false;
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(0, "token不能为空", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(collection.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
                // 身份认证失败,返回错误信息
                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            //判断该分组是否为默认分组
            PersonGroup group = personGroupService.loadById(id);
            if (group.getIsDefault() == 1) {
                return JSONUtil.toJSONResult(0, "默认分组不可以删除", null);
            }
            //判断分组下是否有护士
            PersonManager model = new PersonManager();
            model.setPersonGroupId(id);
            List<PersonManager> manager = personManagerService.list(model);
            //如果有护士将护士放入默认分组
            PersonGroup query = new PersonGroup();
            query.setIsDefault(1);
            query.setCreatorId(group.getCreatorId());
            query = personGroupService.load(query);
            for (PersonManager personManager : manager) {
                personManager.setPersonGroupId(query.getId());
                personManagerService.update(personManager);
            }
            flag = personGroupService.disableById(id);
            if (!flag) {

                return JSONUtil.toJSONResult(0, "编辑失败！", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", null);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseManagement.deleteGroup.json id=" + id, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "添加护士", path = "/addNurse.json", method = RequestMethod.POST)
    public byte[] addNurse(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            PersonManager personManager) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseManagement.addNurse.json");
            }
            if (StringUtils.isEmpty(personManager.getCreatorId()) || StringUtils.isEmpty(personManager.getCreatorName())
                    || StringUtils.isEmpty(personManager.getUserId()) || personManager.getIsLeader() == null) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            if (personManager.getStatus() != -1) {
                if (StringUtils.isEmpty(personManager.getPersonGroupId())) {
                    return JSONUtil.toJSONResult(0, "参数不能为空", null);
                }
            }
            boolean flag = false;
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(0, "token不能为空", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(collection.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
                // 身份认证失败,返回错误信息
                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            //判断是否是添加自己
            if (personManager.getUserId().equals(personManager.getCreatorId())) {
                return JSONUtil.toJSONResult(0, "你不能添加自己", null);
            }
            PersonManager manager = new PersonManager();
            //判断是是否已经添加过
            manager.setUserId(personManager.getUserId());
            manager.setCreatorId(personManager.getCreatorId());
            manager.setStatus(0);
            List<PersonManager> list = personManagerService.list(manager);
            if (list.size() > 0) {
                    return JSONUtil.toJSONResult(0, "您已经发送过请求了！", null);
            }
            //判断被添加者是否已经添加过添加者
            manager = new PersonManager();
            manager.setCreatorId(personManager.getUserId());
            manager.setUserId(personManager.getCreatorId());
            manager.setStatus(1);
            list = personManagerService.list(manager);
            if (list.size() > 0) {
                return JSONUtil.toJSONResult(0, "您已经在对方好友列表中！！", null);
            }
            //判断被添加者是否被别人添加
            manager = new PersonManager();
            manager.setUserId(personManager.getUserId());
            manager.setStatus(1);
            list = personManagerService.list(manager);
            if (list.size() > 0) {
                if (personManager.getIsLeader() == 1) {
                    return JSONUtil.toJSONResult(0, "该护士已经被别人添加！！", null);
                }
            }
           
            //判断是否是管理者添加被管理者
            if (personManager.getIsLeader() == 0) {
                //判断被添加的是同意还是拒绝
                manager = new PersonManager();
                manager.setCreatorId(personManager.getUserId());
                manager.setUserId(personManager.getCreatorId());
                manager = personManagerService.load(manager);
                if (personManager.getStatus() == 1) {
                    if (manager != null) {
                        if (manager.getIsLeader() == 0) {
                            personManager.setIsLeader(1);
                        }
                        manager.setStatus(1);
                    }
                }
                else {
                    personManager.setStatus(-1);
                    manager.setStatus(-1);
                }
                personManagerService.update(manager);
            }
            personManager.setId(UUIDUtils.getId());
            personManager.setIsRead(0);
            personManager.setCreateTime(new Date());
            String insert = personManagerService.insert(personManager);

            if (insert.length() == 0) {
                return JSONUtil.toJSONResult(0, "操作失败", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", null);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseManagement.addNurse.json", e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "编辑护士", path = "/editNurse.json", method = RequestMethod.POST)
    public byte[] editNurse(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            PersonManager personManager) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseManagement.editNurse.json");
            }
            if (StringUtils.isEmpty(personManager.getId()) || StringUtils.isEmpty(personManager.getPersonGroupId())) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            boolean flag = false;
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(0, "token不能为空", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(collection.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
                // 身份认证失败,返回错误信息
                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            PersonManager manager = personManagerService.loadById(personManager.getId());
            if (manager == null) {
                return JSONUtil.toJSONResult(0, "非法请求！", null);
            }
            personManager.setId(manager.getId());
            boolean b = personManagerService.update(personManager);
            if (!b) {
                return JSONUtil.toJSONResult(0, "编辑失败！", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", null);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseManagement.editNurse.json", e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "我的分组列表", path = "/groupList.json")
    public byte[] groupList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseManagement.groupList.json userId=" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            boolean flag = false;
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(0, "token不能为空", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(collection.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
                // 身份认证失败,返回错误信息
                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            Map<String, Object> result = new HashMap<>();
            PersonGroup query = new PersonGroup();
            query.setCreatorId(userId);
            query.setStatus(0);
            query.setOrderby("is_default DESC");
            //我的分组
            List<PersonGroup> list = personGroupService.listNurse(query);
            //所有添加我的未读信息
            PersonManager manager = new PersonManager();
            manager.setUserId(userId);
            manager.setIsRead(0);
            int count = personManagerService.count(manager);
            result.put("groupList", list);
            result.put("unread", count);
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseManagement.groupList.json userId=" + userId, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "新护士通知列表", path = "/noticeList.json")
    public byte[] noticeList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseManagement.noticeList.json userId=" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            boolean flag = false;
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(0, "token不能为空", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(collection.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
                // 身份认证失败,返回错误信息
                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            //将未读的信息修改为已读
            personManagerService.updateIsRead(userId);
            PersonManager manager = new PersonManager();
            manager.setUserId(userId);
            List<PersonManager> list = personManagerService.noticeList(manager);
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseManagement.noticeList.json userId=" + userId, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "分组下的护士", path = "/nurserListByGroup.json")
    public byte[] nurserListByGroup(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            String personGroupId) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseManagement.nurserListByGroup.json personGroupId=" + personGroupId);
            }
            if (StringUtils.isEmpty(personGroupId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            boolean flag = false;
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(0, "token不能为空", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(collection.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
                // 身份认证失败,返回错误信息
                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            //将未读的信息修改为已读
            Map<String, Object> query = new HashMap<>();
            query.put("personGroupId", personGroupId);
            List<Map<String, Object>> list = personManagerService.getNurseList(query);
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseManagement.nurserListByGroup.json personGroupId=" + personGroupId, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "删除护士", path = "/deleteNurse.json")
    public byte[] deleteNurse(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String id) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseManagement.deleteNurse.json id=" + id);
            }
            if (StringUtils.isEmpty(id)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            boolean flag = false;
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(0, "token不能为空", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(collection.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
                // 身份认证失败,返回错误信息
                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            //将未读的信息修改
            return JSONUtil.toJSONResult(1, "操作成功！", null);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseManagement.nurserListByGroup.json id=" + id, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "搜索未添加的护士", path = "/searchNurse.json")
    public byte[] searchNurse(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String searchName,
            String userId) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog
                        .debug("nurseManagement.searchAddNurse.json searchName=" + searchName + " userId=" + userId);
            }
            if (StringUtils.isEmpty(searchName) || StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            boolean flag = false;
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(0, "token不能为空", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(collection.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
                // 身份认证失败,返回错误信息
                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            //将未读的信息修改为已读
            Map<String, Object> query = new HashMap<>();
            query.put("searchName", searchName);
            query.put("userId", userId);
            List<Map<String, Object>> list = personManagerService.searchNurse(query);
            if (list.size() == 0) {

                return JSONUtil.toJSONResult(0, "沒有搜索到匹配的护士", list);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseManagement.searchAddNurse.json searchName=" + searchName + " userId=" + userId, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "搜索添加的护士", path = "/searchAddNurse.json")
    public byte[] searchAddNurse(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String searchName,
            String userId) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog
                        .debug("nurseManagement.searchAddNurse.json searchName=" + searchName + " userId=" + userId);
            }
            if (StringUtils.isEmpty(searchName) || StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            boolean flag = false;
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(0, "token不能为空", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(collection.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
                // 身份认证失败,返回错误信息
                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            //将未读的信息修改为已读
            Map<String, Object> query = new HashMap<>();
            query.put("search", searchName);
            query.put("userId", userId);
            List<Map<String, Object>> list = personManagerService.getNurseList(query);
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseManagement.searchAddNurse.json searchName=" + searchName + " userId=" + userId, e);
        }
        return null;
    }

}
