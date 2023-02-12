package com.example.demo;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextEnvironment;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

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
                                context.getNamingResources().addEnvironment(resourceFor("jdbc/DatabaseName", "puzzo"));
                        }

                        private ContextEnvironment resourceFor(String jndiName, String value) {
                                final ContextEnvironment resource = new ContextEnvironment();
                                resource.setName(jndiName);
                                resource.setType(String.class.getName());
                                resource.setValue(value);
                                return resource;
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

