package cn.tedu.straw.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/admin/list")
    public String adminList() {
        return "admin list";
    }

    @GetMapping("/admin/delete")
    public String adminDelete() {
        return "admin delete";
    }

    @GetMapping("/user/list")
    public String userList() {
        return "user list";
    }

    @GetMapping("/user/delete")
    public String userDelete() {
        return "user delete";
    }

}
