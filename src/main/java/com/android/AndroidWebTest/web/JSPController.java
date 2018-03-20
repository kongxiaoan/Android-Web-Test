package com.android.AndroidWebTest.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JSPController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
