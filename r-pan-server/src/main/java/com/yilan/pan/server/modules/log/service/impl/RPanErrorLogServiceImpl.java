package com.yilan.pan.server.modules.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yilan.pan.server.modules.log.entity.RPanErrorLog;
import com.yilan.pan.server.modules.log.service.RPanErrorLogService;
import com.yilan.pan.server.modules.log.mapper.RPanErrorLogMapper;
import org.springframework.stereotype.Service;

/**
* @author yilan
* @description 针对表【r_pan_error_log(错误日志表)】的数据库操作Service实现
* @createDate 2024-07-27 00:24:51
*/
@Service
public class RPanErrorLogServiceImpl extends ServiceImpl<RPanErrorLogMapper, RPanErrorLog>
    implements RPanErrorLogService{

}




