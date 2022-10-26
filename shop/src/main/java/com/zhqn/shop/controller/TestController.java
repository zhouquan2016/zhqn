package com.zhqn.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: TestController
 * Date:     2022/9/27 19:28
 * Description:
 * @author zhouquan3
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/log")
    public String log(int size) {
        log.error("loop index:{}", size);
        return "ok";
    }
}
