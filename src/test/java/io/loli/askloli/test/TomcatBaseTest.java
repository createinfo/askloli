package io.loli.askloli.test;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.apache4.ApacheHttpClient4;
import com.sun.jersey.client.apache4.config.DefaultApacheHttpClient4Config;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class TomcatBaseTest {
    private static Tomcat mTomcat;
    protected static ApacheHttpClient4 client;
    protected static DefaultApacheHttpClient4Config config;

    protected String output;
    protected final static String GET = "GET";
    protected final static String POST = "POST";
    protected WebResource webResource;
    protected static Map<String, String> paramMap;
    protected String host = "http://localhost:8080/rest";
    protected static ObjectMapper mapper;
    protected static String BASE_DIR = System.getProperty("user.dir");
    protected static String webappDirLocation = "src/main/webapp";
    @BeforeClass
    public static void beforeClass() throws LifecycleException,
            ServletException {
        paramMap = new TreeMap<String, String>();
        paramMap = new TreeMap<String, String>();
        mapper = new ObjectMapper();
        mTomcat = new Tomcat();
        mTomcat.setHostname("localhost");
        mTomcat.setPort(8080);
        mTomcat.setBaseDir(BASE_DIR);
        mTomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        mTomcat.getHost().setAutoDeploy(true);
        mTomcat.getHost().setDeployOnStartup(true);
        mTomcat.init();
        mTomcat.start();
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

    @AfterClass
    public static void afterClass() throws LifecycleException {
        mTomcat.stop();
    }
}
