package io.loli.askloli.test;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class GrizzlyTestSample extends JerseyTest {
    @Override
    protected AppDescriptor configure() {
        return new WebAppDescriptor.Builder("io.loli.askloli.controller")
            .contextParam("contextConfigLocation", "classpath:applicationContext.xml")
            .contextPath("/").servletClass(SpringServlet.class)
            .contextListenerClass(ContextLoaderListener.class)
            .requestListenerClass(RequestContextListener.class)
            .build();
    }
 
    
    public void searchAPI() throws Exception {
        WebResource webResource = resource();
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("q", "Java");
        String response = webResource.path("/test/test2")
                .queryParams(params)
                .header("Authorization", "...")
                .get(String.class);
        System.out.println(response.toString());
    }
}
