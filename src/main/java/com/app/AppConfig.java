package com.app;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.app.entity.Employee;


@Configuration
@PropertySource("resources/db.properties")
@ComponentScan(basePackages= {"com.app"})
@EnableTransactionManagement
public class AppConfig 
{
	@Autowired
	private Environment env;
	//1. DataSource Obj
	@Bean
	public BasicDataSource dsObj() 
	{
	BasicDataSource ds=new BasicDataSource();
	ds.setDriverClassName(env.getProperty("dc"));
	ds.setUrl(env.getProperty("url"));
	ds.setUsername(env.getProperty("un"));
	ds.setPassword(env.getProperty("pwd"));
	ds.setInitialSize(1);
	ds.setMaxTotal(10);
	ds.setMaxIdle(5);
	ds.setMinIdle(2);
	return ds;
	}
	@Bean
	public LocalSessionFactoryBean sfObj()
	{
	LocalSessionFactoryBean sf=new
	LocalSessionFactoryBean();
	sf.setDataSource(dsObj());
	sf.setHibernateProperties(props());
	sf.setAnnotatedClasses(Employee.class);
	return sf;
	}
	private Properties props() 
	{
		Properties p=new Properties();
		p.put("hibernate.dialect",
		env.getProperty("dialect"));
		p.put("hibernate.show_sql",
		env.getProperty("showsql"));
		p.put("hibernate.format_sql",
		env.getProperty("formatsql"));
		p.put("hibernate.hbm2ddl.auto",env.getProperty("ddlauto"
		));
		return p;
	}
	@Bean
	public HibernateTransactionManager htx() {
	HibernateTransactionManager htm=new
	HibernateTransactionManager();
	htm.setSessionFactory(sfObj().getObject());
	return htm;
	}
	@Bean
	public InternalResourceViewResolver ivr() {
		InternalResourceViewResolver v=new InternalResourceViewResolver();
		v.setPrefix("/");
		v.setSuffix(".jsp");
		return v;
	}

}
