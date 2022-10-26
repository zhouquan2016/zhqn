package com.zhqn.platform.controller;

import com.zhqn.base.feign.platform.AppLogClient;
import com.zhqn.base.feign.platform.AppLogDto;
import com.zhqn.platform.service.AppLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
/**
 * FileName: LogController
 * Author:   zhouquan3
 * Date:     2022/9/27 14:42
 * Description:
 * @author zhouquan3
 */
@RestController
@RequestMapping("/log")
@Slf4j
public class LogController implements AppLogClient {

    @Resource
    AppLogService appLogService;

    @PostMapping("")
    @Override
    public void save(@RequestBody AppLogDto logDto)  {
        appLogService.saveLog(logDto);

    }
}
