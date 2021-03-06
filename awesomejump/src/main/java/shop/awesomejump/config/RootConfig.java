package shop.awesomejump.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;
import shop.awesomejump.config.webSocket.EchoHandler;

@Configuration
	//해당 클래스에서 1개 이상의 Bean을 생성하고 있음을 명시
@ComponentScan(basePackages = { "shop.awesomejump"})
@MapperScan(basePackages = { "shop.awesomejump.mapper" })

@EnableScheduling
@EnableTransactionManagement
	//XML의 <tx:annotation-driven/>와 동일한 컨테이너 인프라 Bean을 등록해주는 자바 코드 설정용 어노테이션
@EnableAspectJAutoProxy
	//@Aspect 어노테이션을 적용한 클래스를 Bean으로 등록
@RequiredArgsConstructor
	//의존성 주입
public class RootConfig {
	
	private final ApplicationContext applicationContext;
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public DataSource dataSource() {
		
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@howtis.ddns.net:1521:xe");
		hikariConfig.setUsername("awesomejump");
		hikariConfig.setPassword("sixsense");
//		hikariConfig.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@172.20.10.4:1521:xe");
//		hikariConfig.setUsername("scott");
//		hikariConfig.setPassword("1234");
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}
	
	@Bean(name = "filterMultipartResolver")
	public MultipartResolver multiPartResolver(){
	    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	    resolver.setMaxUploadSize(10 * 1024 * 1024);
	    resolver.setDefaultEncoding("UTF-8");
	    return resolver;
	}
	
	@Bean(name="mailSender")
	public JavaMailSender getJavaMailSender() {
		Properties properties = new Properties(); 
		properties.put("mail.smtp.auth", true);
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.debug", true);
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("awesomejump.project@gmail.com");
		mailSender.setPassword("#awesome#");
		mailSender.setDefaultEncoding("utf-8");
		mailSender.setJavaMailProperties(properties);
		
		return mailSender;
		
	}
	
	@Bean
	public EchoHandler echoHandler() {
		EchoHandler echoHandler = new EchoHandler();
		return echoHandler;
	}

}