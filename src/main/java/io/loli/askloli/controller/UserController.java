package io.loli.askloli.controller;

import io.loli.askloli.entity.User;
import io.loli.askloli.service.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Named
@Path("/user")
public class UserController {
    @Inject
    private UserService us;

    @Path("/regist")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public User regist(@QueryParam("username") String username,
            @QueryParam("password") String password,
            @QueryParam("email") String email) {
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
    @Path("/exsits")
    @Produces(MediaType.TEXT_PLAIN)
    @POST
    public String canRegist(@QueryParam("name") String strToCheck,
            @QueryParam("type") String type) {
        final String SUCCESS = "success";
        final String FAILED = "falied";
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
}
