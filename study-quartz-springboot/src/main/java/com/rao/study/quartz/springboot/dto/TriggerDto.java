package com.rao.study.quartz.springboot.dto;

/**
 * @author raoshihong
 * @date 2020-05-24 10:06
 */
public class TriggerDto {
    private String triggerName;
    private String triggerGroup;
    private String cron;
    private JobDto jobDto;

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public JobDto getJobDto() {
        return jobDto;
    }

    public void setJobDto(JobDto jobDto) {
        this.jobDto = jobDto;
    }
}
