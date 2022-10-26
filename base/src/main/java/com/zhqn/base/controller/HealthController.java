package com.zhqn.base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: controller
 * Author:   zhouquan3
 * Date:     2022/10/21 18:32
 * Description:
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping("")
    public String check() {
        return "ok";
    }
}
