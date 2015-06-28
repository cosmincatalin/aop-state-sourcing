package com.configurations;

import com.aspects.EventSourcing;
import com.entities.CompanyAddressEntity;
import com.entities.CompanyEntity;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.repositories.CompanyAddressesRepository;
import com.repositories.CompanyAddressesRepositoryHibernate;
import com.repositories.CompanyRepositoryHibernate;
import com.repositories.CompanyRepository;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.net.UnknownHostException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan("com.aspects, com.repositories, com.services")
public class AppConfig {

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/davos");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    @Autowired
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addAnnotatedClasses(CompanyEntity.class, CompanyAddressEntity.class);
        sessionBuilder.addProperties(getHibernateProperties());
        return sessionBuilder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", false);
        return properties;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Autowired
    @Bean(name = "companyRepository")
    public CompanyRepository getCompanyRepository(SessionFactory sessionFactory) {
        return new CompanyRepositoryHibernate(sessionFactory);
    }

    @Autowired
    @Bean(name = "companyAddressesRepository")
    public CompanyAddressesRepository getCompanyAddressesRepository(SessionFactory sessionFactory) {
        return new CompanyAddressesRepositoryHibernate(sessionFactory);
    }

    @Bean(name = "mongoDB")
    public DB getMongoDB() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost" , 27017);
        return mongoClient.getDB("davos_es");
    }

//    @Bean(name = "eventSourcing")
//    public EventSourcing getEventSourcing(DB mongoDB) {
//        return new EventSourcing(mongoDB);
//    }
}
