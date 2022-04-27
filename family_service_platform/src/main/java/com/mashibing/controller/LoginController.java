package com.mashibing.controller;

import com.alibaba.fastjson.JSONObject;
import com.mashibing.bean.TblUserRecord;
import com.mashibing.returnJson.Permission;
import com.mashibing.returnJson.Permissions;
import com.mashibing.returnJson.ReturnObject;
import com.mashibing.returnJson.UserInfo;
import com.mashibing.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/auth/2step-code")
    public boolean step_code2() {
        System.out.println("此请求是前端框架带的默认请求，可以不做任何处理，也可以在前端将其删除");
        System.out.println("step_code2");
        return true;
    }

    @RequestMapping("/auth/login")
    public String login(String username, String password, HttpSession session) {
        TblUserRecord tblUserRecord = loginService.login(username, password);
        tblUserRecord.setToken(tblUserRecord.getUserName());
        session.setAttribute("loginUser", tblUserRecord);
        ReturnObject returnObject = new ReturnObject(tblUserRecord);

        return JSONObject.toJSONString(returnObject);
    }

    @RequestMapping("/user/info")
    public String info(HttpSession session) {
        TblUserRecord tblUserRecord = (TblUserRecord) session.getAttribute("loginUser");
        String[] splitedRolePrivileges = tblUserRecord.getTblRole().getRolePrivileges().split("-");

        List<Permission> permissionList = new ArrayList<>();
        for (String splitedRolePrivilege : splitedRolePrivileges) {
            permissionList.add(new Permission(splitedRolePrivilege));
        }

        UserInfo userInfo = new UserInfo(tblUserRecord.getUserName(), new Permissions(permissionList));
        ReturnObject returnObject = new ReturnObject(userInfo);

        return JSONObject.toJSONString(returnObject);
    }

    @RequestMapping("/auth/logout")
    public JSONObject loginOut(HttpSession session){
        System.out.println("退出登录");
        session.invalidate();
        return JSONObject.parseObject(JSONObject.toJSONString(new ReturnObject(null)));
    }
}