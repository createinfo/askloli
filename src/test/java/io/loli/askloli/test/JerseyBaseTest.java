package io.loli.askloli.test;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.apache4.ApacheHttpClient4;
import com.sun.jersey.client.apache4.config.DefaultApacheHttpClient4Config;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class JerseyBaseTest extends JerseyTest {
    protected static ApacheHttpClient4 client;
    protected static DefaultApacheHttpClient4Config config;

    protected String output;
    protected final static String GET = "GET";
    protected final static String POST = "POST";
    protected WebResource webResource;
    protected static Map<String, String> paramMap;
    protected String host = "http://localhost:9998";
    protected static ObjectMapper mapper;

    @Override
    protected AppDescriptor configure() {
        return new WebAppDescriptor.Builder("io.loli.askloli.controller")
                .contextParam("contextConfigLocation",
                        "classpath:applicationContext.xml")
                .contextPath("/")
                .servletClass(SpringServlet.class)
                .initParam("com.sun.jersey.api.json.POJOMappingFeature", "true")
                .contextListenerClass(ContextLoaderListener.class)
                .requestListenerClass(RequestContextListener.class).build();
    }

    @BeforeClass
    public static void beforeClass() {
        paramMap = new TreeMap<String, String>();
        //加上后反而cookie反而会失效
        // config = new DefaultApacheHttpClient4Config();
        // config.getProperties().put(
        // "com.sun.jersey.impl.client.httpclient.handleCookies", true);
        paramMap = new TreeMap<String, String>();
        mapper = new ObjectMapper();
    }

    @Before
    public void setUp() {
        client = ApacheHttpClient4.create();
    }

    protected void connect(String url, String method, String mediaType,
            Map<String, String> paramMap) {
        url = host + url;
        webResource = client.resource(url);
        if (method.equals(GET)) {
            output = webResource.accept(mediaType).get(String.class);
        } else {
            MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
            for (Entry<String, String> entry : paramMap.entrySet()) {
                queryParams.add(entry.getKey(), entry.getValue());
            }
            output = webResource
                    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                    .accept(mediaType).post(String.class, queryParams);
        }
    }
}
