package com.zhqn.base.feign.platform;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * FileName: ElasticAppender
 * Author:   zhouquan3
 * Date:     2022/9/26 19:28
 * Description:
 * @author zhouquan3
 */
@Component
public class AppLogAppender extends UnsynchronizedAppenderBase<ILoggingEvent> implements ApplicationContextAware {

    AppLogClient appLogClient;

    @Value("${spring.application.name}")
    private String applicationName;

    @PostConstruct
    public void init() {
        AsyncAppender asyncAppender = new AsyncAppender();
        asyncAppender.addAppender(this);
        asyncAppender.setIncludeCallerData(true);
        LoggerContext loggerFactory = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = loggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.addAppender(asyncAppender);
        this.start();
        asyncAppender.start();
    }

    @Override
    protected void append(ILoggingEvent event) {
        try {
            AppLogDto logDto = new AppLogDto();
            logDto.setAppName(applicationName);
            logDto.setHost(getHost());
            logDto.setTimestamp(System.nanoTime());
            logDto.setLevel(event.getLevel().toString());
            logDto.setThreadName(event.getThreadName());
            if (!event.hasCallerData()) {
                event.prepareForDeferredProcessing();
            }
            StackTraceElement firstCaller = event.getCallerData() != null && event.getCallerData().length > 0 ? event.getCallerData()[0] : null;
//            if (Objects.isNull(firstCaller) || firstCaller.getClassName() == null || !firstCaller.getClassName().startsWith("com.zhqn")) {
//                return;
//            }

            if (Objects.nonNull(firstCaller)) {
                logDto.setClassFullName(firstCaller.getClassName());
                logDto.setMethodName(firstCaller.getMethodName());
                logDto.setLineNumber(firstCaller.getLineNumber());

            }
            logDto.setMessage(event.getFormattedMessage());
            logDto.setException(proxyThrowableToString(event.getThrowableProxy()));
            appLogClient.save(logDto);
        }catch (Exception e) {
            System.out.println("保存日志失败:" + e.getMessage());
        }

    }

    private String getHost()  {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        if (Objects.isNull(inetAddress)) {
            return null;
        }
        return inetAddress.getHostAddress();
    }


    private String proxyThrowableToString(IThrowableProxy throwableProxy) {
        if (Objects.isNull(throwableProxy)) {
            return null;
        }
        StackTraceElementProxy[] stackTraces = throwableProxy.getStackTraceElementProxyArray();
        if (stackTraces == null || stackTraces.length <= 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (StackTraceElementProxy stackTrace : stackTraces) {
            builder.append(stackTrace.getSTEAsString()).append("\n");
        }
        return builder.toString();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String beanName = "platform".equals(applicationName) ? "logController" : "appLogClient";
        appLogClient = applicationContext.getBean(beanName, AppLogClient.class);
    }
}