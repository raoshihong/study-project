package com.rao.study.quartz.springboot.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author raoshihong
 * @date 2020-05-24 08:35
 */
public class MyJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        System.out.println("thread:"+Thread.currentThread().getName()+
            ",data:"+new Date()+
            "jobName:"+jobKey.getName()+
            "jobGroup:"+jobKey.getGroup()+
            "name:"+jobDataMap.get("name"));
    }
}
