package com.example.getmewet.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebInitializer
        extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebInitializer() {
        super(SecurityConfiguration.class);
    }
}
