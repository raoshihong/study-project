package com.rao.study.quartz.springboot.dto;

/**
 * @author raoshihong
 * @date 2020-05-23 23:55
 */
public class JobDto {
    private String jobName;
    private String jobGroup;
    private String jobClassName;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }
}
