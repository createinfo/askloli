package io.loli.askloli.dao.impl;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import io.loli.askloli.dao.UserDao;
import io.loli.askloli.entity.User;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Inject
    private UserDao userDao;

    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setUsername("chocotan");
        user.setPassword("password");
        user.setEmail("uzumakitenye@gmail.com");
    }

    @Test
    @Transactional
    public void testSave() {
        userDao.save(user);
        assertThat(user.getId(), not(0));
    }

    @Test
    @Transactional
    public void testFindByName() {
        userDao.save(user);
        User result = userDao.findByName(user.getUsername());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getId(), result.getId());
    }

    @Test
    @Transactional
    public void testFindByUsername() {
        userDao.save(user);
        User result = userDao.findByEmail(user.getEmail());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getId(), result.getId());
    }

    @Test
    @Transactional
    public void testFindByNameAndPassword() {
        userDao.save(user);
        User result1 = userDao.findByNameAndPassword(user.getUsername(),
                user.getPassword());

        assertNotNull(result1);
    }

    @Test(expected = NoResultException.class)
    @Transactional
    public void testFindNoResult() {
        userDao.save(user);
        User result = userDao.findByNameAndPassword(user.getUsername(), "");
        assertNull(result);
    }

    @Test
    @Transactional
    public void testFindById() {
        userDao.save(user);
        User result = userDao.findById(user.getId());
        assertNotNull(result);
    }
}
