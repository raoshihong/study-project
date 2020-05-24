package com.rao.study.quartz.daily;

import com.rao.study.quartz.simple.MyJob;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author raoshihong
 * @date 2020-05-23 14:54
 */
public class DailyTimeIntervalTriggerTest {
    @Test
    public void test() throws Exception{
        //1.创建job
        JobKey jobKey = new JobKey("j3","g3");
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
            .withIdentity(jobKey)
            .usingJobData("myName","aaaa")
            .build();

        //2.创建trigger
        DailyTimeIntervalTrigger trigger = TriggerBuilder.newTrigger()
            .withIdentity("t3", "tg3")
            .withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                //表示每天都执行
                .onEveryDay()
                // 设置间隔3s执行一次
                .withIntervalInSeconds(3)
                //设置总共支持三次三次
                .withRepeatCount(3))
            .build();
        //上面都触发器表示每天都执行，总共执行三次,每次间隔3秒,比如从2020-05-23 15:06:48开始执行,则执行如下
        /*
            2020-05-23 15:06:48
            2020-05-23 15:06:51
            2020-05-23 15:06:54
            2020-05-23 15:06:57

            第二天执行(时间可能从第二天凌晨12点开始执行)
            2020-05-24 00:00:00
            2020-05-24 00:00:03
            2020-05-24 00:00:06

         */

        //3.创建scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //4.设置要执行的job和trigger
        scheduler.scheduleJob(jobDetail,trigger);

        //5.启动调度
        scheduler.start();


        Thread.sleep(100000);

        scheduler.shutdown();
    }
}
