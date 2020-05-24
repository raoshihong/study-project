package com.rao.study.quartz.simple;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author raoshihong
 * @date 2020-05-23 10:55
 */
public class SimpleTriggerTest {

    @Test
    public void simple01() throws Exception{
        //1.创建一个Job
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
            // 定义job的名称和group,可以通过group获取下面的所有job,所以最终job的唯一表示为group+name
            .withIdentity("job1", "jobGroup01")
            // 使用jobDataMap,也可以使用单独的key-value
            .usingJobData("myName", "aaa")
            .build();

        //2.创建一个触发器，（可以绑定job,也可以不绑定job）
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
            // 也可以给触发器定义唯一标识
            .withIdentity("trigger01", "triggerGroup")
            // 设置触发器的规则,startNow表示启动调度就执行一次
            .startNow()
            // 设置触发器调度规则
            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                        // 表示每隔1s执行一次
                                        .withIntervalInSeconds(1)
                                        // 表示总共执行多少次,这里表示反复执行,也可以使用withRepeatCount方法指定执行次数
                                        .repeatForever())
//                                        .withRepeatCount(1))
            .build();

        //3.创建调度器,创建一个标准的调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //4.注册job和trigger到调度器中（当需要调度某个job时,则再注册也可以）
        scheduler.scheduleJob(jobDetail,trigger);

        //5.启动调度器,去执行任务
        scheduler.start();

        Thread.sleep(10000);

        //关闭调度器
        scheduler.shutdown();
    }

}
