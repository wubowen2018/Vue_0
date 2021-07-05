package com.jer.vue2_0.controller;

import com.jer.vue2_0.config.annotation.LogPrint;
import com.jer.vue2_0.pojo.User;
import com.jer.vue2_0.result.Result;
import com.jer.vue2_0.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;


@Controller
@Slf4j
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    @LogPrint(description ="用户登录")
    public Result login( @RequestBody User requestUser){
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassword());
        if (null == user) {
            return new Result(400);
        } else {
            return new Result(200);
        }
    }

}
