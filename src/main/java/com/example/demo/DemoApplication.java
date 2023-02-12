package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.naming.Context;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private Environment env;


	@Autowired
	@Qualifier("one")
	PropertyHolder theProperty;

	@Autowired
	@Qualifier("two")
	PropertyHolder theProperty2;

	@Autowired
	@Qualifier("three")
	PropertyHolder dbDataSource;

	public static void main(String[] args) {
		System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.naming.java.javaURLContextFactory");
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(theProperty);
		System.out.println(theProperty2);
		System.out.println("java.naming.factory.initial=" + env.getProperty("java.naming.factory.initial"));
		System.out.println("dbDataSource=" + dbDataSource);
	}
}
