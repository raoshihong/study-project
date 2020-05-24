package com.rao.study.quartz.ss;

import com.rao.study.quartz.simple.MyJob;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author raoshihong
 * @date 2020-05-23 15:48
 */
public class Testss {
    @Test
    public void test() throws Exception{
        //1. 创建job
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("myName","raoshihong");
        jobDataMap.put("age",10);
        JobDetail jobDetail = JobBuilder.newJob(MyJob2.class)
            .withIdentity("job04","group04")
            .usingJobData(jobDataMap)
            .build();

        //2. 创建trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger04", "triggerGroup04")
            //设置cron表达式，表示每年每月每日每时每分没间隔2秒执行一次
            .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ? *"))
            .startNow().build();

        //3. 创建scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //4.绑定执行的trigger和job
        scheduler.scheduleJob(jobDetail,trigger);

        //5.启动调度器
        scheduler.start();

        Thread.sleep(1000000);
    }
}
