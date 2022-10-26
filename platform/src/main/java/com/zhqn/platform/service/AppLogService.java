package com.zhqn.platform.service;

import com.zhqn.base.feign.platform.AppLogDto;
import com.zhqn.platform.domain.entity.AppLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhouquan3
* @description 针对表【app_log】的数据库操作Service
* @createDate 2022-09-29 16:14:01
*/
public interface AppLogService extends IService<AppLogEntity> {

    public void saveLog(AppLogDto logDto);
}
