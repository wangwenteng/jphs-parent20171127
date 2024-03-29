package com.jinpaihushi.jphs.person.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.account.service.AccountService;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.service.NurseImagesService;
import com.jinpaihushi.jphs.nurse.service.NurseService;
import com.jinpaihushi.jphs.person.model.PersonGroup;
import com.jinpaihushi.jphs.person.service.PersonGroupService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.jphs.verification.model.Verification;
import com.jinpaihushi.jphs.verification.service.VerificationService;
import com.jinpaihushi.jphs.worktime.model.Worktime;
import com.jinpaihushi.jphs.worktime.service.WorktimeService;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.MD5;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(path = "/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private WorktimeService worktimeService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private NurseImagesService nurseImagesService;

    @Autowired
    private PersonGroupService personGroupService;

    @Autowired
    private NurseService nurseService;

    @SuppressWarnings("static-access")
    @ResponseBody
    @RequestMapping(name = "登录", path = "/login.json")
    public byte[] login(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String phone, String password,
            String type, String openid) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("login.login.json,phone=" + phone + " password=" + password + " type=" + type);
            }

            // 查空
            if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(type)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            int tid = 0; // type
            try {
                tid = Integer.valueOf(type);
            }
            catch (NumberFormatException e) {
                return JSONUtil.toJSONResult(0, "字符串转整形失败", null);
            }

            User user = new User();

            // 判断登录的类型 如果是护士 判断密码
            if (StringUtils.isEmpty(password)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }

            user.setPhone(phone);
            user.setType(tid);
            // 2.错误N种情况判断及返回前端
            user = userService.queryUser(user);
            if (user == null) {
                return JSONUtil.toJSONResult(0, "您还未注册，请前往注册！", null);
            }
            if (!user.getPassword().equals(MD5.md5crypt(MD5.md5crypt(password)))) {
                return JSONUtil.toJSONResult(0, "账号密码不匹配。", null);
            }
            if (StringUtils.isEmpty(user.getName()))
                user.setName(user.getPhone());
            if (tid == 0) {
                Nurse nurse_s = new Nurse();
                nurse_s.setCreatorId(user.getId());
                Nurse n = nurseService.load(nurse_s);
                user.setNurse(new JSONObject().fromObject(n));
                if (n.getStatus() == 1) {
                    Worktime time = new Worktime();
                    time.setUserid(user.getId());
                    //判断有没有默认分组
                    int i = worktimeService.count(time);
                    if (i == 0) {
                        worktimeService.insertNurseWorkTime(user.getId());
                    }
                    PersonGroup group = new PersonGroup();
                    group.setIsDefault(1);
                    group.setCreatorId(user.getId());
                    i = personGroupService.count(group);
                    if (i == 0) {
                        personGroupService.createDefaultGroup(user.getId());
                    }
                }
            }

            if (!StringUtils.isEmpty(openid)) {
                User user_up = new User();
                user_up.setId(user.getId());
                user_up.setOpenId(openid);
                userService.update(user_up);
            }
            NurseImages img = new NurseImages();
            img.setSourceId(user.getId());
            img.setType(1);
            img.setStatus(1);
            img = nurseImagesService.load(img);
            if (img != null)
                user.setHeadPicture(img.getUrl());
            // 设置token
            String token = "";
            token = Common.getToken(phone, user.getPassword());// 生成token
            user.setToken(token);
            // 3.信息无误，封装信息以及生成token，返回前端

            return JSONUtil.toJSONResult(1, "登录成功！", user);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("login.login.json,phone=" + phone + " password=" + password + " type=" + type, e);
        }
        return null;
    }

    /**
     * 微信快捷登录
     * @param hs
     * @param req
     * @param resp
     * @param validateCode
     * @param type
     * @param openid
     * @return
     */
    @ResponseBody
    @RequestMapping(name = "微信openid快捷登录", path = "/wechatLogin.json")
    public byte[] wechatLogin(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String validateCode,
            String type, String openid) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug(
                        "login.wechatLogin.json,type=" + type + " openid=" + openid + " verifyCode=" + validateCode);
            }
            if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(type)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            int tid = 0; // type
            try {
                tid = Integer.valueOf(type);
            }
            catch (NumberFormatException e) {
                return JSONUtil.toJSONResult(0, "字符串转整形失败", null);
            }

            User user = new User();
            user.setOpenId(openid);
            user.setType(tid);
            // 1.根据 name，password,type查询完整信息
            user = userService.queryUser(user);
            if (user == null || user.equals("")) {
                return JSONUtil.toJSONResult(0, "登录失败！", null);
            }

            NurseImages img = null;
            if (img == null) {
                img = new NurseImages();
                img.setSourceId(user.getId());
                img.setType(1);
                img.setStatus(1);
                img = nurseImagesService.load(img);
            }
            if (img != null)
                user.setHeadPicture(img.getUrl());
            // 设置token
            String token = "";
            token = Common.getToken(user.getPhone(), user.getPassword());// 生成token
            user.setToken(token);
            if (StringUtils.isEmpty(user.getName()))
                user.setName(user.getPhone());
            // 3.信息无误，封装信息以及生成token，返回前端
            // 将用户信息放到session中
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("token", token);
            return JSONUtil.toJSONResult(1, "登录成功！", user);

        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("login.quickLogin.json, type=" + type + " verifyCode=" + validateCode, e);
        }

        return null;
    }

    /**
     * {登录注册合并}
     * 
     * @param hs
     * @param req
     * @param resp
     * @param phone
     *            手机号
     * @param validateCode
     *            图片验证码
     * @param smsCode
     *            短信验证码
     * @param type
     *            类型 1、用户 2 护士
     * @return
     * @author: wangwt
     */
    @SuppressWarnings("static-access")
    @ResponseBody
    @RequestMapping(name = "快捷登录", path = "/quickLogin.json")
    public byte[] quickLogin(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String validateCode,
            String smsCode, String phone, String type, String openid) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("login.quickLogin.json,phone=" + phone + " type=" + type + " smsCode=" + smsCode
                        + " verifyCode=" + validateCode);
            }

            // 如果session中没有图片验证码并且请求的验证码数据存在 ---非法请求
            if ((null == req.getSession().getAttribute("validateCode")
                    || StringUtils.isEmpty(req.getSession().getAttribute("validateCode").toString()))
                    && StringUtils.isNotEmpty(validateCode)) {
                return JSONUtil.toJSONResult(0, "非法请求", null);
            }
            else if (StringUtils.isNotEmpty(validateCode)
                    && !validateCode.equalsIgnoreCase(req.getSession().getAttribute("validateCode").toString())) {
                return JSONUtil.toJSONResult(0, "图片验证码错误", null);
            }
            // 查空
            if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(type) || StringUtils.isEmpty(smsCode)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            int tid = 0; // type
            try {
                tid = Integer.valueOf(type);
            }
            catch (NumberFormatException e) {
                return JSONUtil.toJSONResult(0, "字符串转整形失败", null);
            }
            // 获取该手机号最新一条短信
            Verification vc = verificationService.getLastRecordByPhone(phone);
            if (vc == null) {
                return JSONUtil.toJSONResult(0, "非法请求", null);

            }
            else {
                if (!smsCode.equals(vc.getCode())) {
                    // 验证码不对
                    return JSONUtil.toJSONResult(0, "短信验证码不正确", null);
                }
            }
            int t = Common.beforeNow(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vc.getValidTime()));
            if (t == 0) {
                // 无效
                return JSONUtil.toJSONResult(0, "验证码已失效", null);
            }
            else if (t == -1) {
                // 失败
                return JSONUtil.toJSONResult(0, "判断验证码有效时间失败", null);
            }
            User user = new User();
            user.setPhone(phone);
            user.setType(tid);
            // 1.根据 name，password,type查询完整信息
            user = userService.queryUser(user);
            NurseImages img = null;
            // 2.错误N种情况判断及返回前端
            if (user == null) {
                // 如果不存在给用户注册信息
                user = new User();
                user.setPhone(phone);
                user.setType(tid);
                // 设置默认密码为手机号
                user.setPassword(MD5.md5crypt(MD5.md5crypt(phone)));
                if (!StringUtils.isEmpty(openid)) {
                    user.setOpenId(openid);
                }
                // 设置用户的注册端
                // 设置当前注册类型为用户
                if (StringUtils.isEmpty(user.getName())) {
                    user.setName(user.getPhone());
                }
                user.setStatus(1);
                user.setSex(0);
                if (!StringUtils.isEmpty(openid)) {
                    user.setOpenId(openid);
                }
                // 写入数据
                String userId = userService.insertUser(user);
                if (userId.length() < 0) {
                    return JSONUtil.toJSONResult(0, "注册失败", null);
                }
                // 设置默认头像
                img = new NurseImages();
                img.setId(UUID.randomUUID().toString());
                img.setSourceId(user.getId());
                img.setUrl("https://resource.jinpaihushi.com/images/default_head/yonghu_nan.jpg");
                img.setCreateTime(new Date());
                img.setType(1);
                img.setStatus(1);
                img.setCreatorId(user.getId());
                img.setCreatorName(user.getName());
                nurseImagesService.insert(img);

                // 给用户创建相应的account账户
                Account account = new Account();
                account.setId(UUIDUtils.getId());
                account.setBalance(0.0);
                account.setScore(0);
                account.setAvailableScore(0);
                account.setCreateTime(new Date());
                account.setCreatorId(userId);
                account.setStatus(0);
                accountService.insert(account);
            }
            if (img == null) {
                img = new NurseImages();
                img.setSourceId(user.getId());
                img.setType(1);
                img.setStatus(1);
                img = nurseImagesService.load(img);
            }
            if (tid == 0) {
                Nurse nurse_s = new Nurse();
                nurse_s.setCreatorId(user.getId());
                Nurse n = nurseService.load(nurse_s);
                user.setNurse(new JSONObject().fromObject(n));
            }
            if (img != null)
                user.setHeadPicture(img.getUrl());

            if (!StringUtils.isEmpty(openid)) {
                User user_up = new User();
                user_up.setId(user.getId());
                user_up.setOpenId(openid);
                userService.update(user_up);
            }
            // 设置token
            String token = "";
            token = Common.getToken(phone, user.getPassword());// 生成token
            user.setToken(token);
            if (StringUtils.isEmpty(user.getName()))
                user.setName(user.getPhone());
            // 3.信息无误，封装信息以及生成token，返回前端
            // 将用户信息放到session中
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("token", token);
            return JSONUtil.toJSONResult(1, "登录成功！", user);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("login.quickLogin.json,phone=" + phone + " type=" + type + " smsCode=" + smsCode
                    + " verifyCode=" + validateCode, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "退出", path = "/loginOut.json")
    public byte[] loginOut(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String phone,
            String password, String type) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("login.loginOut.json");
            }
            hs.invalidate();

            return JSONUtil.toJSONResult(1, "已成功退出，欢迎下次登录", null);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.debugLog.debug("login.loginOut.json", e);
        }
        return null;
    }
}
