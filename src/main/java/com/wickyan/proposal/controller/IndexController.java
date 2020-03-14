package com.wickyan.proposal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wickyan on 2020/3/14
 */
@Controller
public class IndexController {

    @RequestMapping({"/","/index"})
    public String index() {
        return "index";
    }
}
