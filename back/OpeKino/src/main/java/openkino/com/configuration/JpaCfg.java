package openkino.com.configuration;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(basePackages = "openkino")
@EnableTransactionManagement
@AllArgsConstructor
public class JpaCfg {
    DataSource dataSource;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HashMap<String, String> hibernateProperties = new HashMap<>();
        hibernateProperties.put("hibernate.connection.driver_class", "org.h2.Driver");
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.put("hibernate.show_sql", "true");
        hibernateProperties.put("hibernate.generate_statistics", "true");
        hibernateProperties.put("hibernate.connection.charSet", "UTF-8");
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.use_sql_comments", "true");
//        hibernateProperties.put("hibernate.hbm2ddl.auto","create-drop");
        hibernateProperties.put("hibernate.hbm2ddl.auto","update");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setPackagesToScan("openkino");
        factory.setDataSource(dataSource);
        factory.setJpaPropertyMap(hibernateProperties);
        return factory;
    }
}
