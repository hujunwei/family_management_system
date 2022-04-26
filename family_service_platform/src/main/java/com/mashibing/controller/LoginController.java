package com.mashibing.controller;

import com.alibaba.fastjson.JSONObject;
import com.mashibing.bean.TblUserRecord;
import com.mashibing.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String login(String username, String password) {
        TblUserRecord tblUserRecord = loginService.login(username, password);
        System.out.println(tblUserRecord);
        return JSONObject.toJSONString(tblUserRecord);
    }
}