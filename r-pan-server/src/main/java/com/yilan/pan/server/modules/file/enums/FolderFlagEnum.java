package com.yilan.pan.server.modules.file.enums;

/**
 * @author yilan0916
 * @date 2024/7/28
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件夹标识枚举类
 */
@AllArgsConstructor
@Getter
public enum FolderFlagEnum {

    /**
     * 非文件夹
     */
    NO(0),
    /**
     * 是文件夹
     */
    YES(1);

    private Integer code;

}
