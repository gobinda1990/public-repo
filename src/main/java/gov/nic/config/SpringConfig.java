package gov.nic.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringConfig {
	
	@Bean
    public DataSource datasource() {
        DriverManagerDataSource dmds = new DriverManagerDataSource();
        dmds.setDriverClassName("oracle.jdbc.OracleDriver");
        dmds.setUrl("jdbc:oracle:thin:@10.153.181.6:1525:stagedb");
        dmds.setUsername("acs_mast");
        dmds.setPassword("stage123");
        return dmds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws NamingException {
        JdbcTemplate jdbctemp = new JdbcTemplate();
        jdbctemp.setDataSource(datasource());
        return jdbctemp;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() throws NamingException {
        return new NamedParameterJdbcTemplate(datasource());
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager getTransactionManager() throws NamingException {
        DataSourceTransactionManager dtm = new DataSourceTransactionManager();
        dtm.setDataSource(datasource());
        return dtm;
    }   

}
