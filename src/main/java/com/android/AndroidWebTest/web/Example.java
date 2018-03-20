package com.android.AndroidWebTest.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Example {
    /**
     * RequestMapping 路由 ， 任何具有路径"/"的Http请求都会映射到home方法中
     *
     * @return
     */
    @RequestMapping("/")
    String home() {
        return "anroid-web-test";
    }

}
