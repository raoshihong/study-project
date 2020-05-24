package com.rao.study.quartz.ss;

import org.quartz.*;

import java.util.Date;

/**
 * 实现job执行类
 * @author raoshihong
 * @date 2020-05-23 10:55
 */
@DisallowConcurrentExecution
public class MyJob2 implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //在这块编写Job的具体执行逻辑

        try {
            Thread.sleep(5000);
        }catch (Exception e){

        }

        //从JobExecutionContext中获取job的数据
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        //获取job的唯一标识
        JobKey jobKey = jobDetail.getKey();
        //
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        Object data = jobDataMap.get("myName");

        System.out.println("thread:"+Thread.currentThread().getName()+",group:"+jobKey.getGroup()+",name:"+jobKey.getName()+"myName:"+data + new Date());
    }
}
