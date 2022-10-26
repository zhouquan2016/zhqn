package com.zhqn.base.feign.platform;

import feign.Headers;
import feign.RequestLine;

/**
 * FileName: LogApi
 * Author:   zhouquan3
 * Date:     2022/9/27 14:48
 * Description:
 * @author zhouquan3
 */
public interface AppLogClient {

    /**
     * 保存日志
     * @param logDto dto
     */
    @RequestLine("POST /log")
    @Headers("Content-Type: application/json")
    void save(AppLogDto logDto);
}
