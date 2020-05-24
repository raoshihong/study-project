package com.rao.study.quartz.mysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author raoshihong
 * @date 2020-05-23 17:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-quartz-jdbc.xml")
public class MySQLJobTest {

    @Test
    public void test()throws Exception{

        Thread.sleep(10000000);
    }

}
