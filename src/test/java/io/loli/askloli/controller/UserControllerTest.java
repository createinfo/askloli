package io.loli.askloli.controller;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import io.loli.askloli.entity.User;
import io.loli.askloli.test.TomcatBaseTest;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

public class UserControllerTest extends TomcatBaseTest {

    @Test
    public void testRegist() throws JsonParseException, JsonMappingException,
            IOException {
        String username1 = "chocotan" + new Date().hashCode();
        String email1 = "uzumakitenye@gmail.com" + new Date().hashCode();
        paramMap.put("username", username1);
        paramMap.put("password", "password");
        paramMap.put("email", email1);
        this.connect("/user/regist", POST, MediaType.APPLICATION_JSON, paramMap);
        User user1 = mapper.readValue(output, User.class);
        assertThat(user1.getId(), not(0));
        this.connect("/user/regist", POST, MediaType.APPLICATION_JSON, paramMap);
        User user2 = mapper.readValue(output, User.class);
        assertEquals(0, user2.getId());
    }

    @Test
    public void testCanRegist() {
        String username1 = "chocotan" + new Date().hashCode();
        String email1 = "uzumakitenye@gmail.com" + new Date().hashCode();
        paramMap.put("username", username1);
        paramMap.put("password", "password");
        paramMap.put("email", email1);
        this.connect("/user/regist", POST, MediaType.APPLICATION_JSON, paramMap);

        // 用户名一样 不能注册
        paramMap.clear();
        paramMap.put("name", username1);
        paramMap.put("type", "username");
        this.connect("/user/exists", POST, MediaType.TEXT_PLAIN, paramMap);
        assertEquals("failed", output);

        // 邮箱一样 不能注册
        paramMap.clear();
        paramMap.put("name", email1);
        paramMap.put("type", "email");
        this.connect("/user/exists", POST, MediaType.TEXT_PLAIN, paramMap);
        assertEquals("failed", output);

        // 用户名不一样 能注册
        paramMap.clear();
        paramMap.put("name", username1 + new Date().hashCode());
        paramMap.put("type", "username");
        this.connect("/user/exists", POST, MediaType.TEXT_PLAIN, paramMap);
        assertEquals("success", output);

    }

    @Test
    public void testLogin() throws JsonParseException, JsonMappingException,
            IOException {
        String username1 = "chocotan" + new Date().hashCode();
        String email1 = "uzumakitenye@gmail.com" + new Date().hashCode();
        paramMap.put("username", username1);
        paramMap.put("password", "password");
        paramMap.put("email", email1);
        this.connect("/user/regist", POST, MediaType.APPLICATION_JSON, paramMap);

        // 用户名密码正确 成功登陆
        paramMap.clear();
        paramMap.put("username", username1);
        paramMap.put("password", "password");
        this.connect("/user/login", POST, MediaType.APPLICATION_JSON, paramMap);
        User user1 = mapper.readValue(output, User.class);
        assertEquals(username1, user1.getUsername());

        // 用户名错误 登录失败
        paramMap.clear();
        paramMap.put("username", username1 + new Date().hashCode());
        paramMap.put("password", "password");
        this.connect("/user/login", POST, MediaType.APPLICATION_JSON, paramMap);
        User user2 = mapper.readValue(output, User.class);
        assertEquals(0, user2.getId());
        assertNull(user2.getUsername());
    }

    @Test
    public void testCheckLogin() throws JsonParseException,
            JsonMappingException, IOException {
        String username1 = "chocotan" + new Date().hashCode();
        String email1 = "uzumakitenye@gmail.com" + new Date().hashCode();
        paramMap.put("username", username1);
        paramMap.put("password", "password");
        paramMap.put("email", email1);
        this.connect("/user/regist", POST, MediaType.APPLICATION_JSON, paramMap);

        // 用户名密码错误 登录失败
        paramMap.clear();
        paramMap.put("username", username1 + new Date().hashCode());
        paramMap.put("password", "password");
        this.connect("/user/login", POST, MediaType.APPLICATION_JSON, paramMap);

        // 判断是否登陆
        paramMap.clear();
        this.connect("/user/loggedIn", POST, MediaType.APPLICATION_JSON,
                paramMap);
        User user = mapper.readValue(output, User.class);
        assertEquals(0, user.getId());

        // 成功登陆
        paramMap.clear();
        paramMap.put("username", username1);
        paramMap.put("password", "password");
        this.connect("/user/login", POST, MediaType.APPLICATION_JSON, paramMap);

        // 判断是否登录
        paramMap.clear();
        this.connect("/user/loggedIn", POST, MediaType.APPLICATION_JSON,
                paramMap);
        User user2 = mapper.readValue(output, User.class);
        assertEquals(username1, user2.getUsername());
    }

    @Test
    public void testCheckLogOut() throws JsonParseException,
            JsonMappingException, IOException {
        // 未登录
        paramMap.clear();
        this.connect("/user/logout", GET, MediaType.APPLICATION_JSON, paramMap);
        assertEquals("not_login", output);

        // 注册
        String username1 = "chocotan" + new Date().hashCode();
        String email1 = "uzumakitenye@gmail.com" + new Date().hashCode();
        paramMap.put("username", username1);
        paramMap.put("password", "password");
        paramMap.put("email", email1);
        this.connect("/user/regist", POST, MediaType.APPLICATION_JSON, paramMap);

        // 登陆
        paramMap.clear();
        paramMap.put("username", username1);
        paramMap.put("password", "password");
        this.connect("/user/login", POST, MediaType.APPLICATION_JSON, paramMap);
        User user1 = mapper.readValue(output, User.class);
        assertEquals(username1, user1.getUsername());

        // 登出
        paramMap.clear();
        this.connect("/user/logout", GET, MediaType.APPLICATION_JSON, paramMap);
        assertEquals("logout_success", output);

        // 判断是否登录
        paramMap.clear();
        this.connect("/user/loggedIn", POST, MediaType.APPLICATION_JSON,
                paramMap);
        User user2 = mapper.readValue(output, User.class);
        assertEquals(0, user2.getId());
    }
}
