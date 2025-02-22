package com.yilan.pan.server.modules.file.service;

import com.yilan.pan.server.modules.file.context.CreateFolderContext;
import com.yilan.pan.server.modules.file.entity.RPanUserFile;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yilan
* @description 针对表【r_pan_user_file(用户文件信息表)】的数据库操作Service
* @createDate 2024-07-27 00:23:45
*/
public interface IUserFileService extends IService<RPanUserFile> {

    /**
     * 创建文件夹信息
     *
     * @param createFolderContext
     * @return
     */
    Long createFolder(CreateFolderContext createFolderContext);

    /**
     * 查询用户的根文件夹信息
     *
     * @param userId
     * @return
     */
    RPanUserFile getUserRootFile(Long userId);
}
