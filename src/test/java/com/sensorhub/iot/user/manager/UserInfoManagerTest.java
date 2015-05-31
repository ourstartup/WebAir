package com.sensorhub.iot.user.manager;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.sensorhub.iot.user.domain.UserInfo;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import javax.annotation.Resource;
import java.util.Date;
import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext*.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class })
public class UserInfoManagerTest
{
    private UserInfoManager userInfoManager;

    @Resource
    public void setUserInfoManager(UserInfoManager userInfoManager)
    {
        this.userInfoManager = userInfoManager;
    }

    @Test
    @DatabaseSetup(value = "classpath:DB.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void testCheckUserName() throws Exception
    {
       boolean result =  userInfoManager.checkUserName("zhangfan");
        assertEquals(false, result);

    }
    @Test
    public void testLogin() throws Exception
    {
        UserInfo result = userInfoManager.login("zhangfan", "123456");
        assertEquals("zhangfan", result.getUsername());

    }

    @Test
    @DatabaseSetup(value = "classpath:DB.xml", type = DatabaseOperation.DELETE)
    public void testAddUserAccount() throws Exception
    {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName("predator2");
        userInfo.setRegisterDate(new Date());
        userInfo.setUsername("zhangfan2");
        userInfoManager.addUserAccount(userInfo);

        UserInfo userInfo1 = userInfoManager.findUniqueBy("nickName", "predator2");
        assertEquals("zhangfan2", userInfo1.getUsername());

    }



}