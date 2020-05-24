package com.rao.study.quartz.springboot.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @author raoshihong
 * @date 2020-05-24 10:33
 */
public class MyJobListener implements JobListener {
    @Override public String getName() {
        System.out.println("MyJobListener");
        return MyJobListener.class.getName();
    }

    @Override public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("jobToBeExecuted");
    }

    @Override public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("jobExecutionVetoed");
    }

    @Override public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("jobWasExecuted");
    }
}
