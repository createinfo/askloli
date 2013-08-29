package io.loli.askloli.service.impl;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import io.loli.askloli.entity.User;
import io.loli.askloli.service.UserService;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest extends
        AbstractTransactionalJUnit4SpringContextTests {
    private User user;

    @Inject
    private UserService us;

    @Before
    public void setUp() {
        user = new User();
        user.setUsername("chocotan");
        user.setPassword("password");
        user.setEmail("uzumakitenye@gmail.com");
    }

    @Test
    public void testSave() {
        us.save(user);
        assertThat(user.getId(), not(0));
    }

    @Test
    public void testIsExistByUsername() {
        boolean result1 = us.isExistByUsername(user.getUsername());
        assertTrue(!result1);
        us.save(user);
        boolean result2 = us.isExistByUsername(user.getUsername());
        assertTrue(result2);
        boolean result3 = us.isExistByUsername("");
        assertTrue(!result3);
    }

    @Test
    public void testIsExistByEmail() {
        us.save(user);
        boolean result = us.isExistByEmail(user.getEmail());
        assertTrue(result);
        boolean result2 = us.isExistByEmail("");
        assertTrue(!result2);
    }

    @Test
    public void testFindByUsernameAndPassword() {
        us.save(user);
        User result = us.findByUsernameAndPassword(user.getUsername(),
                user.getPassword());

        User result2 = us.findByUsernameAndPassword(user.getUsername(), "");
        User result3 = us.findByUsernameAndPassword("", "");
        assertNotNull(result);
        assertNull(result2);
        assertNull(result3);
    }

    @Test
    public void testFindById() {
        us.save(user);
        User result = us.findById(user.getId());
        assertEquals(user.getId(), result.getId());
    }
}
