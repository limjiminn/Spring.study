package shop.awesomejump.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@Configuration
	//해당 클래스에서 1개 이상의 Bean을 생성하고 있음을 명시
@ComponentScan(basePackages = { "shop.awesomejump" })
@MapperScan(basePackages = { "shop.awesomejump.mapper" })
@EnableTransactionManagement
	//XML의 <tx:annotation-driven/>와 동일한 컨테이너 인프라 Bean을 등록해주는 자바 코드 설정용 어노테이션
@EnableAspectJAutoProxy
	//@Aspect 어노테이션을 적용한 클래스를 Bean으로 등록
@RequiredArgsConstructor
	//의존성 주입
public class RootConfig {
	
	private final ApplicationContext applicationContext;
	
	@Bean
	public DataSource dataSource() {
		
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@howtis.ddns.net:1521:xe");
		hikariConfig.setUsername("awesomejump");
		hikariConfig.setPassword("sixsense");
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
	
}