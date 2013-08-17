package io.loli.askloli.controller;

import io.loli.askloli.entity.User;
import io.loli.askloli.service.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Named
@Path("/user")
public class UserController {
    @Inject
    private UserService us;

    /**
     * 用户注册
     * 
     * @param username
     *            用户名
     * @param password
     *            密码
     * @param email
     *            邮箱
     * @return 注册后的User对象, 会由rest框架自动转换成json字符串
     */
    @Path("/regist")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public User regist(@FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("email") String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        us.save(user);
        return user;
    }

    /**
     * 判断用户名是否存在
     * 
     * @param strToCheck
     *            需要判断的字符串
     * @param type
     *            "username"代表用户名 "email"代表邮箱
     * @return 如果存在则是"failed" 如果不存在则是"success"
     */
    @Path("/exists")
    @Produces(MediaType.TEXT_PLAIN)
    @POST
    public String canRegist(@FormParam("name") String strToCheck,
            @FormParam("type") String type) {
        final String SUCCESS = "success";
        final String FAILED = "failed";
        String success_or_failed = SUCCESS;
        if (type.equals("username")) {
            success_or_failed = us.isExistByUsername(strToCheck) ? FAILED
                    : SUCCESS;
        } else if (type.equals("email")) {
            success_or_failed = us.isExistByEmail(strToCheck) ? FAILED
                    : SUCCESS;
        }
        return success_or_failed;
    }

    /**
     * 判断是否已经登陆
     * 
     * @param request
     * @return 当session中有user对象时, 返回之, 否则返回new User()
     */
    @POST
    @Path("/loggedIn")
    @Produces(MediaType.APPLICATION_JSON)
    public User checkLogin(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
        }
        return user;
    }

    /**
     * 用户登录
     * 
     * @param request
     * @param username
     *            post参数-用户名
     * @param password
     *            post参数-密码(最好是md5)
     * @return 此用户的User对象, 登陆失败则返回new User()
     */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public User login(@Context HttpServletRequest request,
            @FormParam("username") String username,
            @FormParam("password") String password) {
        User result = us.findByUsernameAndPassword(username, password);
        if (result != null) {
            request.getSession(true).setAttribute("user", result);
        } else {
            result = new User();
        }
        return result;
    }

    /**
     * 用户登出
     * 
     * @param request
     * @return session中有user对象则返回"logout_success", 否则返回"not_login"
     */
    @GET
    @Path("logout")
    public String logout(@Context HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "not_login";
        } else {
            request.getSession().removeAttribute("user");
            return "logout_success";
        }
    }
}
