package cn.tedu.straw.gateway.controller;

import cn.tedu.straw.commons.security.LoginUserInfo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {

    @GetMapping("/index.html")
    public String index(@AuthenticationPrincipal LoginUserInfo loginUserInfo) {
        // 在整合了Thymeleaf的SpringBoot项目中，当转发到视图时，默认情况下
        // 前缀是 /templates/
        // 后缀是 .html
        // 当返回 index 时，会根据”前缀+返回值+后缀“组合出 /templates/index.html
        System.err.println("LoginUserInfo >>> " + loginUserInfo);
        return "index";
    }

    @GetMapping("/register.html")
    public String register() {
        return "register";
    }

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/question/create.html")
    public String createQuestion() {
        return "question/create";
    }
    @GetMapping("/question/detail.html")
    public String show(){
        return "question/detail";
    }
}
