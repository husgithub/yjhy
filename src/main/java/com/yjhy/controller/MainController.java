package com.yjhy.controller;

import com.yjhy.entity.SysAccount;
import com.yjhy.entity.common.ResponseBean;
import com.yjhy.service.SysAccountService;
import com.yjhy.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by husong on 2018/5/6.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private SysAccountService sysAccountService;

    @RequestMapping("/")
    public String index() {
        SysAccount admin = SessionUtil.getAdmin();
        return "index";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("loginVerify")
    @ResponseBody
    public ResponseBean loginVerify(SysAccount sysAccount, String vCode, HttpServletRequest request, HttpServletResponse response) {
        ResponseBean responseBean = new ResponseBean();
        if (ObjectUtils.isEmpty(sysAccount) || StringUtils.isEmpty(sysAccount.getAccountName()) || StringUtils.isEmpty(sysAccount.getAccountPwd())) {
            responseBean.setError("用户名或密码为空！");
            return responseBean;
        }
        SysAccount resAccount = sysAccountService.login(sysAccount);
        if (ObjectUtils.isEmpty(resAccount)) {
            responseBean.setError("用户名或密码错误！");
            return responseBean;
        }
        HttpSession session = request.getSession();
        session.setAttribute("admin", resAccount);
        session.setMaxInactiveInterval(-1);
        return responseBean;
    }

    @RequestMapping("logout")
    public void logout(HttpServletResponse response, HttpSession session) {
        session.invalidate();
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
