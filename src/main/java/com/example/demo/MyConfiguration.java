package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(
        {
                "classpath:spring-beans.xml"
        }
)
public class MyConfiguration {
}
