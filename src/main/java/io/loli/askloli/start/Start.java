package io.loli.askloli.start;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

public class Start {
    private HttpServer server;
    public void start() throws IOException{
        server = new HttpServer();
        NetworkListener listener = new NetworkListener("grizzly2", "localhost",
                8080);
        server.addListener(listener);
        WebappContext ctx = new WebappContext("ctx", "");
        final ServletRegistration reg = ctx.addServlet("spring",
                new SpringServlet());
        reg.addMapping("/*");
        ctx.addContextInitParameter("contextConfigLocation",
                "classpath:applicationContext.xml");
        ctx.addListener("org.springframework.web.context.ContextLoaderListener");
        ctx.addListener("org.springframework.web.context.request.RequestContextListener");
        reg.setInitParameter("com.sun.jersey.config.property.packages",
                "io.loli.askloli.controller");
        ctx.deploy(server);
        server.start();
        System.in.read();
    }
    public static void main(String[] args) throws IOException {
        new Start().start();
    }
    public void stop(){
        server.shutdown();
    }
}
