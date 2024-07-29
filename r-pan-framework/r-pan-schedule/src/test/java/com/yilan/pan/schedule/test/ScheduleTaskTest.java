package com.yilan.pan.schedule.test;

import com.yilan.pan.schedule.ScheduleManager;
import com.yilan.pan.schedule.test.config.ScheduleTestConfig;
import com.yilan.pan.schedule.test.task.SimpleScheduleTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 定时任务模块单元测试
 *
 * @author yilan0916
 * @date 2024/7/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ScheduleTestConfig.class)
public class ScheduleTaskTest {

    @Autowired
    private ScheduleManager scheduleManager;

    @Autowired
    private SimpleScheduleTask simpleScheduleTask;

    @Test
    public void testRunScheduleTask() throws InterruptedException {

        String cron = "0/5 * * * * ? ";
        String key = scheduleManager.startTask(simpleScheduleTask, cron);

        Thread.sleep(10000);
        cron = "0/1 * * * * ? ";
        scheduleManager.changeTask(key, cron);

        Thread.sleep(10000);
        scheduleManager.stopTask(key);
    }
}
