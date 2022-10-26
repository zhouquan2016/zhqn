package com.zhqn.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhqn.base.feign.platform.AppLogDto;
import com.zhqn.platform.domain.entity.AppLogEntity;
import com.zhqn.platform.mapper.clickhouse.AppLogMapper;
import com.zhqn.platform.service.AppLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
* @author zhouquan3
* @description 针对表【app_log】的数据库操作Service实现
* @createDate 2022-09-29 16:14:01
*/
@Service
public class AppLogServiceImpl extends ServiceImpl<AppLogMapper, AppLogEntity>
    implements AppLogService {

    @Override
    public void saveLog(AppLogDto logDto) {
        try {
            AppLogEntity appLogEntity = new AppLogEntity();
            BeanUtils.copyProperties(logDto, appLogEntity);
            appLogEntity.setCreateTime(LocalDateTime.now());
            save(appLogEntity);
        }catch (Exception e) {
            System.err.println("clickhouse保存日志失败:" + e.getMessage());
        }

    }
}




