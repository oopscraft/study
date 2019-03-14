package net.oopscraft.study.study.springboot;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class StudyApplication {
	
	static Properties applicationProperties = new Properties();
	static SpringApplication springApplication;
	static ApplicationContext applicationContext;
	
	public static void main(String[] args) throws Exception {
		System.setProperty("spring.config.location","file:conf/application.properties");
		
		SpringApplication springApplication = new SpringApplication(StudyApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		loadApplicationProperties();
		springApplication.setDefaultProperties(applicationProperties);
		applicationContext = springApplication.run(args);
		
		// join thread
		Thread.currentThread().join();
	}
	
	private static void loadApplicationProperties() throws Exception {
		InputStream is = null;
		try {
			is = new FileInputStream(new File("conf/application.properties"));
			applicationProperties.load(is);
		}catch(Exception e) {
			e.printStackTrace(System.err);
			throw e;
		}finally {
			if(is != null) {
				try {
					is.close();
				}catch(Exception ignore) {}
			}
		}
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
		return null;
	}
	
	@Bean
	public ConfigurableServletWebServerFactory webServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
	    factory.setPort(Integer.parseInt(applicationProperties.getProperty("server.port").trim()));
	    factory.setDocumentRoot(new File("webapp"));
	    factory.setContextPath("");
	    return factory;
	}
}
