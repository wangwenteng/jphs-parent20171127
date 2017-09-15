package com.jinpaihushi.jphs.person.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.account.service.AccountService;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.service.NurseImagesService;
import com.jinpaihushi.jphs.nurse.service.NurseService;
import com.jinpaihushi.jphs.system.service.DoPostSmsService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.jphs.verification.model.Verification;
import com.jinpaihushi.jphs.verification.service.VerificationService;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.MD5;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/regist")
public class RegistController {
    @Autowired
    private UserService userService;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private NurseService nurseService;

    @Autowired
    private NurseImagesService nurseImagesService;

    @Autowired
    private DoPostSmsService doPostSmsService;

    @Value("${SMS_Verification_Code}")
    private String SMS_Verification_Code;

    @SuppressWarnings("static-access")
    @ResponseBody
    @RequestMapping(name = "用户注册", path = "/user.json")
    @Transactional
    public byte[] regist(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, User user,
            String verificattionCode, String type) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog
                        .debug("regist.user.json,phone=" + user.getPhone() + " verificattionCode=" + verificattionCode
                                + " type=" + type + " device=" + user.getDevice() + " password=" + user.getPassword());
            }

            // 查空
            if (StringUtils.isEmpty(user.getPhone()) || StringUtils.isEmpty(verificattionCode)
                    || StringUtils.isEmpty(type) || StringUtils.isEmpty(user.getPassword())
                    || StringUtils.isEmpty(user.getDevice().toString())) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            int type_id = 0; // type
            try {
                type_id = Integer.valueOf(type);
            }
            catch (NumberFormatException e) {
                return JSONUtil.toJSONResult(0, "字符串转整形失败", null);
            }

            // 查询手机号是否注册
            User user_register = new User();
            user_register.setPhone(user.getPhone());
            user_register.setType(type_id);
            user_register.setStatus(1);
            User user_enroll = userService.load(user_register);
            if (user_enroll != null) {
                return JSONUtil.toJSONResult(0, "手机号码已注册!", null);
            }

            // 获取该手机号最新一条短信
            Verification vc = verificationService.getLastRecordByPhone(user.getPhone());
            if (vc == null || !verificattionCode.equals(vc.getCode())) {
                // 验证码不对
                return JSONUtil.toJSONResult(0, "验证码不正确", null);
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
            // 设置用户的注册端
            user.setDevice(user.getDevice());
            // 密码md5加密
            String md5_p = MD5.md5crypt(MD5.md5crypt(user.getPassword()));
            user.setPassword(md5_p);
            // 设置当前注册类型为用户
            user.setType(type_id);
            user.setCreateTime(new Date());
            if (type_id == 0)
                user.setSex(1);
            else
                user.setSex(1);
            // 后期会用-默认用户名是手机号
            if (StringUtils.isEmpty(user.getName())) {
                user.setName(user.getPhone());
            }
            user.setStatus(1);

            // 写入数据
            String userId = userService.insertUser(user);
            if (userId.length() < 0) {
                return JSONUtil.toJSONResult(0, "注册失败", null);
            }
            if (type_id == 0) {
                Nurse nurse = new Nurse();
                nurse.setId(UUID.randomUUID().toString());
                nurse.setCreateTime(new Date());
                nurse.setCreatorId(userId);
                nurse.setCreatorName(user.getName());
                nurse.setActive(1);
                nurse.setStatus(0);
                String nurseId = nurseService.insert(nurse);
                if (nurseId.length() < 0) {
                    return JSONUtil.toJSONResult(0, "注册失败", null);
                }
            }
            // 设置默认头像
            // 设置默认头像
            NurseImages img = new NurseImages();
            img.setId(UUID.randomUUID().toString());
            img.setSourceId(user.getId());
            if (type_id == 0) {
                img.setUrl("https://resource.jinpaihushi.com/images/default_head/yonghu_nv.jpg");
            }
            else {
                img.setUrl("https://resource.jinpaihushi.com/images/default_head/yonghu_nan.jpg");
            }
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

            // 1.根据 name，password,type查询完整信息
            User user_login = userService.findUser(user);
            if (user_login == null) {

                return JSONUtil.toJSONResult(0, "注册成功,登录失败，请返回首页重试！", null);
            }
            // 设置token
            String token = "";
            token = Common.getToken(user_login.getPhone(), user_login.getPassword());// 生成token
            user_login.setToken(token);
            if (type_id == 0) {
                Nurse nurse_s = new Nurse();
                nurse_s.setCreatorId(user_login.getId());
                nurse_s = nurseService.load(nurse_s);
                if (nurse_s == null) {
                    return JSONUtil.toJSONResult(0, "注册成功,登录失败，请返回首页重试！", null);
                }
                user_login.setNurse(new JSONObject().fromObject(nurse_s));

            }
            return JSONUtil.toJSONResult(1, "注册成功！", user_login);

        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("regist.user.json,phone=" + user.getPhone() + " verificattionCode=" + verificattionCode
                    + " type=" + type, e);
        }
        return null;
    }

    // http://192.168.7.66:8059/regist/updatePwd.json?phone=手机号&verificattionCode=验证码&password=密码&type=2
    @RequestMapping(name = "更新密码", path = "/updatePwd.json")
    @ResponseBody
    public byte[] updatePwd(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String phone,
            String password, String type) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("regist.updatePwd.json 请求参数-phone" + phone + " password" + password);
            }
            // 查空
            if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password) || StringUtils.isEmpty(type)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            int type_id = 0; // type
            try {
                type_id = Integer.valueOf(type);
            }
            catch (NumberFormatException e) {
                return JSONUtil.toJSONResult(0, "字符串转整形失败", null);
            }

            User user = new User();
            user.setPhone(phone);
            user.setType(type_id);
            user.setStatus(1);
            User user_up = userService.load(user);
            if (user_up == null) {
                return JSONUtil.toJSONResult(0, "该手机号未注册", null);
            }
            String pwd = MD5.md5crypt(MD5.md5crypt(password));
            user_up.setPassword(pwd);
            boolean up = userService.update(user_up);
            if (up)
                return JSONUtil.toJSONResult(1, "修改成功", null);
            else
                return JSONUtil.toJSONResult(0, "修改失败", null);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.debugLog.debug("regist.updatePwd.json", e);
        }
        return null;
    }

    /**
     * 判断验证码是否正确
     * 
     * @param hs
     * @param req
     * @param resp
     * @param phone
     * @param validateCode
     * @return
     */
    @RequestMapping(name = "判断验证码是否正确", path = "/ifValidateCode.json")
    @ResponseBody
    public byte[] ifValidateCode(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String phone,
            String verificattionCode) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug(
                        "regist.ifValidateCode.json 请求参数-phone" + phone + " verificattionCode" + verificattionCode);
            }
            // 查空
            if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(verificattionCode)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            // 获取该手机号最新一条短信
            Verification vc = verificationService.getLastRecordByPhone(phone);
            if (!verificattionCode.equals(vc.getCode())) {
                // 验证码不对
                return JSONUtil.toJSONResult(0, "验证码不正确", null);
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

            return JSONUtil.toJSONResult(1, "验证码正确", null);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.debugLog.debug("regist.ifValidateCode.json", e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "发送验证码", path = "/sendMassage.json")
    public byte[] sendMassage(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String phone) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("regist.sendMassage.json,phone=" + phone);
            }
            // 查空
            if (phone == null || "".equals(phone)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            String regExp = "^((13)|(14)|(15)|(16)|(17)|(18)|(19))\\d{9}$";
            Pattern p = Pattern.compile(regExp);
            Matcher m = p.matcher(phone);
            if (m.equals(false)) {
                return JSONUtil.toJSONResult(0, "请输入有效的手机号！", null);
            }
            int s = (int) ((Math.random() * 9 + 1) * 1000);// 4位验证码
            // 查询该手机号今天发送验证码的次数
            int size = verificationService.countVerification(phone);
            if (size >= 5) {

                return JSONUtil.toJSONResult(0, "操作过于频繁，请稍后再试！", null);
            }

            // 发送验证码
            doPostSmsService.sendSms(phone, SMS_Verification_Code, "{\"s\":\"" + s + "\"}");
            Verification vc = new Verification();
            vc.setId(UUIDUtils.getId());
            vc.setPhone(phone);
            Date now = new Date();
            Date now_10 = new Date(now.getTime() + 600000);// 加10分钟前的时间
            vc.setValidTime(now_10);
            vc.setCode(s + "");
            vc.setStatus(0);
            String insert = verificationService.insert(vc);
            if (insert.length() < 0) {
                return JSONUtil.toJSONResult(0, "操作失败", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", vc);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("regist.sendMassage.json,phone=" + phone);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "验证手机号是否重复", path = "/distinctPhone.json")
    public byte[] distinctPhone(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String phone,
            String type) {
        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("regist.distinctPhone.json,phone=" + phone + " type" + type);
            }
            // 查空
            if (phone == null || "".equals(phone) || StringUtils.isEmpty(type)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            int type_id = 0; // type
            try {
                type_id = Integer.valueOf(type);
            }
            catch (NumberFormatException e) {
                return JSONUtil.toJSONResult(0, "字符串转整形失败", null);
            }

            // 查询手机号是否注册
            User user = new User();
            user.setPhone(phone);
            user.setType(type_id);
            user.setStatus(1);
            user = userService.load(user);
            if (user != null) {
                return JSONUtil.toJSONResult(0, "手机号已经注册", null);
            }
            return JSONUtil.toJSONResult(1, "该手机号还未注册！", user);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("regist.distinctPhone.json,phone=" + phone, e);
        }
        return null;
    }

}
