package com.yilan.pan.schedule;

/**
 * 定时任务的任务接口
 *
 * @author yilan0916
 * @date 2024/7/28
 */
public interface ScheduleTask extends Runnable {

    /**
     * 获取定时任务的名称
     *
     * @return
     */
    String getName();

}
