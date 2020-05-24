package com.rao.study.quartz.calendar;

import com.rao.study.quartz.simple.MyJob;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 按日历表来执行
 * @author raoshihong
 * @date 2020-05-23 11:42
 */
public class CalendarIntervalTriggerTest {

    @Test
    public void test()throws Exception{
        //1. 创建job
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name","raoshihong");
        jobDataMap.put("age",10);
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
            .withIdentity("job02","group02")
            .usingJobData(jobDataMap)
            .build();

        //2. 创建trigger
        CalendarIntervalTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger02", "triggerGroup02")
            .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
                    //  每隔2s执行一次
                    .withInterval(2, DateBuilder.IntervalUnit.SECOND))
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
