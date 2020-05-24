package com.rao.study.quartz.springboot.config;

import javax.sql.DataSource;

import com.rao.study.quartz.springboot.listener.MyJobListener;
import org.gjt.mm.mysql.Driver;
import org.quartz.Scheduler;
import org.quartz.impl.matchers.EverythingMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author raoshihong
 * @date 2020-05-23 23:42
 */
@Configuration
public class Config {

    /**
     * 创建数据源
     * @return
     */
    @Bean("druidDataSource")
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/myquartz");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        druidDataSource.setDriverClassName(Driver.class.getName());
        return druidDataSource;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Autowired DataSource dataSource){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("quartz.properties"));
        //指定数据源
        schedulerFactoryBean.setDataSource(dataSource);

        // 设置当spring关闭时,会等待所有quartz job完成了再shutdown
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
        //设置是否覆盖已存在当job
        schedulerFactoryBean.setOverwriteExistingJobs(false);
        //设置延迟1s启动
        schedulerFactoryBean.setStartupDelay(1);

        return schedulerFactoryBean;
    }

    /**
     * 创建一个Scheduler
     * @return
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler(@Autowired SchedulerFactoryBean schedulerFactoryBean)throws Exception{

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        //可以在这里注册一个监听器
        scheduler.getListenerManager().addJobListener(new MyJobListener(), EverythingMatcher.allJobs());
        return scheduler;
    }

}
