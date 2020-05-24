package com.rao.study.quartz.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author raoshihong
 * @date 2020-05-23 16:56
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-quartz.xml")
public class SpringQuartzTest {

    @Test
    public void test()throws Exception{
        Thread.sleep(100000);
    }

}
