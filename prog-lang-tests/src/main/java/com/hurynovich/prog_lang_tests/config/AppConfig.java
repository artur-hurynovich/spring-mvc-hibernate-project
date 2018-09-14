package com.hurynovich.prog_lang_tests.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.hurynovich.prog_lang_tests.controller.interceptor.AuthInterceptor;
import com.hurynovich.prog_lang_tests.controller.interceptor.EncodingInterceptor;
import com.hurynovich.prog_lang_tests.entity.Answer;
import com.hurynovich.prog_lang_tests.entity.Lang;
import com.hurynovich.prog_lang_tests.entity.Question;
import com.hurynovich.prog_lang_tests.entity.Test;
import com.hurynovich.prog_lang_tests.entity.User;
import com.hurynovich.prog_lang_tests.util.AuthsXMLHandler;

import static org.hibernate.cfg.Environment.*;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan("com.hurynovich.prog_lang_tests")
public class AppConfig {
	@Autowired
	private Environment env;
	
	@Bean("sessionFactory")
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	    
	    Properties props = new Properties();
	    
	    // Setting JDBC properties
	    props.put(DRIVER, env.getProperty("mysql.driver"));
	    props.put(URL, env.getProperty("mysql.jdbcUrl"));
	    props.put(USER, env.getProperty("mysql.username"));
	    props.put(PASS, env.getProperty("mysql.password"));

	    // Setting Hibernate properties
	    props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
	    props.put(DIALECT, env.getProperty("hibernate.dialect"));

	    // Setting C3P0 properties
	    props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
	    props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));

	    sessionFactory.setHibernateProperties(props);
	    sessionFactory.setAnnotatedClasses(User.class, 
	    	Lang.class, 
	    	Test.class, 
	    	Question.class, 
	    	Answer.class);
	    
	    return sessionFactory;
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	    transactionManager.setSessionFactory(getSessionFactory().getObject());
	    
	    return transactionManager;
	}
	
	@Bean("authUris")
	public List<String> getAuthUris() 
		throws ParserConfigurationException, SAXException, IOException {
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		AuthsXMLHandler handler = new AuthsXMLHandler();
		String xmlPath = "classpath:auths.xml";
		parser.parse(new InputSource(xmlPath), handler);
		return handler.getAuthUris();
	}
	
	@Bean("encodingInterceptor")
	public EncodingInterceptor getEncodingInterceptor() {
		return new EncodingInterceptor();
	}
	
	@Bean("authInterceptor")
	public AuthInterceptor getAuthInterceptor() {
		return new AuthInterceptor();
	}
}
