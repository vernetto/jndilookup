package com.example.demo;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(
        {
                "classpath:spring-beans.xml"
        }
)
public class MyConfiguration {
        @Bean
        public ServletWebServerFactory servletContainer() {
                TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
                        @Override
                        protected void postProcessContext(Context context) {
                                SecurityConstraint securityConstraint = new SecurityConstraint();
                                securityConstraint.setUserConstraint("CONFIDENTIAL");
                                SecurityCollection collection = new SecurityCollection();
                                collection.addPattern("/*");
                                securityConstraint.addCollection(collection);
                                context.addConstraint(securityConstraint);
                        }

                        @Override
                        protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
                                tomcat.enableNaming();
                                return super.getTomcatWebServer(tomcat);
                        }
                };
                return tomcat;
        }


}

