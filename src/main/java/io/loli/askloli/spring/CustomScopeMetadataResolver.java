package io.loli.askloli.spring;

import org.springframework.context.annotation.Jsr330ScopeMetadataResolver;
import org.springframework.web.context.WebApplicationContext;


public class CustomScopeMetadataResolver extends Jsr330ScopeMetadataResolver {
    
    public CustomScopeMetadataResolver() {
        registerScope(RequestScoped.class.getName(),
                WebApplicationContext.SCOPE_REQUEST);
        registerScope(SessionScoped.class.getName(),
                WebApplicationContext.SCOPE_SESSION);
    }
 
}