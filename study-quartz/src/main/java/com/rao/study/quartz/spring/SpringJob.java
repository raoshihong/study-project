package com.rao.study.quartz.spring;

import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author raoshihong
 * @date 2020-05-23 16:41
 */
public class SpringJob extends QuartzJobBean {
    @Override protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            //可以在这里获取Spring上线文
            ApplicationContext ac = (ApplicationContext)context.getScheduler().getContext().get("applicationContextKey");
            JobDetail jobDetail = context.getJobDetail();
            JobDataMap jobDataMap = jobDetail.getJobDataMap();
            JobKey jobKey = jobDetail.getKey();
            System.out.println("thread:"+Thread.currentThread().getName()+"date:"+new Date() +","
                + "group:"+jobKey.getGroup()+",name:"+jobKey.getName()
                +",card:"+jobDataMap.get("card"));
        }catch (SchedulerException e){
            e.printStackTrace();
        }
    }
}
