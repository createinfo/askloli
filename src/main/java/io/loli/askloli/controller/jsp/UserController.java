package io.loli.askloli.controller.jsp;

import io.loli.askloli.entity.User;
import io.loli.askloli.service.UserService;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

@Named
@Path("/user")
@Produces(MediaType.TEXT_HTML)
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
     * @return Response
     */
    @Path("/regist")
    @POST
    public Response regist(@FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("email") String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        us.save(user);
        Map<String, Object> map = new HashMap<String, Object>();
        if (user.getId() == 0) {
            map.put("info", "用户名或邮箱已存在");
        } else {
            map.put("info", "注册成功, 请登陆");
        }
        return Response.ok(new Viewable("/user/login", map)).build();
    }

    /**
     * 用户登出
     * 
     * @param request
     * @return Response对象
     */
    @GET
    @Path("/logout")
    public Response logout(@Context HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> map = new HashMap<String, Object>();
        if (user == null) {
            map.put("info", "您还未登陆");
            // TODO
        } else {
            request.getSession().removeAttribute("user");
            map.put("info", "您成功登出");
        }
        return Response.ok(new Viewable("/user/login", map)).build();
    }

    /**
     * 用户登录
     * 
     * @param request
     * @param username
     *            post参数-用户名
     * @param password
     *            post参数-密码(最好是md5)
     * @return 登陆成功: /user/home
     *         登陆失败: /user/login
     */
    @POST
    @Path("/login")
    public Response login(@Context HttpServletRequest request,
            @FormParam("username") String username,
            @FormParam("password") String password) {
        User result = us.findByUsernameAndPassword(username, password);
        Map<String, Object> map = new HashMap<String, Object>();
        if (result != null) {
            request.getSession(true).setAttribute("user", result);
            map.put("info", "登陆成功");
        } else {
            map.put("info", "用户名或密码错误");
        }
        return Response.ok(new Viewable("/user/login", map)).build();
    }
}
