package springmvckarkas;



import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"springmvckarkas.repository"})
@PropertySource("classpath:db.properties")
public class DBConfig {
    @Autowired
    private Environment env;

    @Bean
    DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
        dataSource.setUrl(env.getProperty("database.url"));
        dataSource.setUsername(env.getProperty("database.username"));
        dataSource.setPassword(env.getProperty("database.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() { // База настраивается так
        LocalContainerEntityManagerFactoryBean emFactory =
                new LocalContainerEntityManagerFactoryBean();
        emFactory.setDataSource(dataSource());
        emFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties properties = new Properties(); // Настройки пишем через вот это
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        properties.put("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        emFactory.setJpaProperties(properties);
        emFactory.setPackagesToScan("springmvckarkas.model");
        return emFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
