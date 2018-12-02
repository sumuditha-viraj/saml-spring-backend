package org.viraj.spring.saml.backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.viraj.spring.saml.backend.interceptor.RequestHandlerInterceptor;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(MvcConfig.class);


    @Autowired
    RequestHandlerInterceptor interceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LOG.debug("Registering Interceptors ");
        registry.addInterceptor(interceptor)
                .addPathPatterns("/getAccount/**");
    }
}
