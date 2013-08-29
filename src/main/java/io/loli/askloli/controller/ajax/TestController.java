package io.loli.askloli.controller.ajax;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
@Named
public class TestController {
    @Inject
    private TestBean testBean;

    @GET
    public String test() {
        return testBean.say();
    }
}
