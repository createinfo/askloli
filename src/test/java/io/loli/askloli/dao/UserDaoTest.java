package io.loli.askloli.dao;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import io.loli.askloli.entity.User;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Inject
    private UserDao userDao;

    private User user;

    
    public void setUp() {
        user = new User();
        user.setUsername("chocotan");
        user.setPassword("password");
        user.setEmail("uzumakitenye@gmail.com");
    }

    
    public void testSave() {
        userDao.save(user);
        assertThat(user.getId(), not(0));
    }
}
