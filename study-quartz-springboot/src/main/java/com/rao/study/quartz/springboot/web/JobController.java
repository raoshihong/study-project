package com.rao.study.quartz.springboot.web;

import com.rao.study.quartz.springboot.dto.JobDto;
import com.rao.study.quartz.springboot.dto.TriggerDto;
import com.rao.study.quartz.springboot.job.MyJob;
import com.rao.study.quartz.springboot.listener.MyJobListener;
import org.quartz.*;
import org.quartz.impl.matchers.EverythingMatcher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author raoshihong
 * @date 2020-05-23 23:43
 */
@RestController
public class JobController {

    @Resource
    private Scheduler scheduler;

    /**
     * 添加一个job
     * @param triggerDto
     */
    @PostMapping(value = "/addJob")
    public String addJob(@RequestBody TriggerDto triggerDto){
        try {
            JobDto jobDto = triggerDto.getJobDto();
            //创建Job
            Class<? extends Job> clazz = null;
            if ("MyJob".equals(jobDto.getJobClassName())) {
                clazz = MyJob.class;
            }else{
                clazz = (Class<? extends Job>)Class.forName(jobDto.getJobClassName());
            }
            JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(jobDto.getJobName(),jobDto.getJobGroup()).build();

            if (!CronExpression.isValidExpression(triggerDto.getCron())) {
                //表达式格式不正确
                return "Illegal cron expression";
            }

            //创建trigger
            CronTrigger trigger =
                TriggerBuilder.newTrigger()
                    .withIdentity(triggerDto.getTriggerName(),triggerDto.getTriggerGroup())
                    .withSchedule(CronScheduleBuilder.cronSchedule(triggerDto.getCron()))
                    .build();

            //注册trigger和job到scheduler中,scheduler在spring容器启动后会自动起到scheduler
            //当执行scheduleJob方法注册了trigger和job到scheduler成功后,就会唤醒所有执行线程去执行相关任务
            scheduler.scheduleJob(jobDetail,trigger);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

    @GetMapping(value = "/getJob")
    public String getJob(@RequestParam("jobName") String jobName,@RequestParam("jobGroup") String jobGroup){
        JobKey jobKey = new JobKey(jobName,jobGroup);
        try {
            JobDetail jobDetail =scheduler.getJobDetail(jobKey);
            return jobDetail.getKey().getName()+jobDetail.getKey().getGroup();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/getTrigger")
    public String getTrigger(@RequestParam("tName") String tName,@RequestParam("tGroup") String tGroup){
        TriggerKey triggerKey = new TriggerKey(tName,tGroup);
        try {
            Trigger trigger = scheduler.getTrigger(triggerKey);
            return trigger.getKey().getName();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 暂停所有任务
     */
    @GetMapping(value = "/pauseAll")
    public void pauseAll()throws Exception{
        scheduler.pauseAll();
    }

    /**
     * 恢复所有任务
     */
    @GetMapping(value = "/resumeAll")
    public void resumeAll()throws Exception{
        scheduler.resumeAll();
    }

    /**
     * 删除指定job
     */
    @GetMapping(value = "/delete")
    public void delete(@RequestParam("jobName") String jobName,@RequestParam("jobGroup") String jobGroup)throws Exception{
        JobKey jobKey = new JobKey(jobName,jobGroup);
        scheduler.deleteJob(jobKey);
    }

    /**
     * 修改调度任务
     */
    @PostMapping(value = "/rescheduleJob")
    public String rescheduleJob(@RequestBody TriggerDto triggerDto) throws Exception{
        TriggerKey triggerKey = new TriggerKey(triggerDto.getTriggerName(),triggerDto.getTriggerGroup());

        CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerKey);

        //修改trigger的参数
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(triggerDto.getCron());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
            .withSchedule(scheduleBuilder).build();

        //修改trigger中job的参数
        trigger.getJobDataMap().put("name","aaaa");


        scheduler.rescheduleJob(triggerKey,trigger);

        return "success";
    }

}
