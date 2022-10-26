package com.zhqn.platform.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @author zhouquan3
 * @TableName app_log
 */
@TableName(value ="app_log")
@Data
public class AppLogEntity implements Serializable {
    /**
     * 微服务名称
     */
    private String appName;

    /**
     * 
     */
    private String host;

    /**
     * 
     */
    private Long timestamp;

    /**
     * 
     */
    private String level;

    /**
     * 
     */
    private String threadName;

    /**
     * 
     */
    private String classFullName;

    /**
     * 
     */
    private String methodName;

    /**
     * 
     */
    private String lineNumber;

    /**
     * 
     */
    private String message;

    /**
     * 
     */
    private String exception;

    /**
     * 
     */
    private LocalDateTime createTime;

}