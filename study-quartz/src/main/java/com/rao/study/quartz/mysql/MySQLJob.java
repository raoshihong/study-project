package com.rao.study.quartz.mysql;

import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author raoshihong
 * @date 2020-05-23 17:58
 */
public class MySQLJob extends QuartzJobBean {
    @Override protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("thread:"+Thread.currentThread().getName()+"date:"+new Date());
    }
}
