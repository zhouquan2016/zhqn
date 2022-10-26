package com.zhqn.base.feign.platform;

import lombok.Data;

/**
 * FileName: LogDto
 * Author:   zhouquan3
 * Date:     2022/9/26 18:51
 * Description:
 * @author zhouquan3
 */
@Data
public class AppLogDto {

    private String appName;

    private String host;

    private Long timestamp;

    private String level;

    private String threadName;

    private String classFullName;

    private String methodName;

    private Integer lineNumber;

    private String message;

    private String exception;


}
