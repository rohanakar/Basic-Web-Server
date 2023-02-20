package rishabh.server.config;

import oracle.jdbc.datasource.impl.OracleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class OracleConfiguration {
    Logger logger = LoggerFactory.getLogger(DbSettings.class);

    @Autowired
    private DbSettings dbSettings;

    @Bean
    public DataSource dataSource() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
//      For local testing
        ds.setDriverType(dbSettings.getDriver_class_name());
        logger.info("Using Driver " + dbSettings.getDriver_class_name());
        ds.setURL(dbSettings.getUrl());
        logger.info("Using URL: " + dbSettings.getUrl());
        ds.setUser(dbSettings.getUsername());
        logger.info("Using Username: " + dbSettings.getUsername());
        ds.setPassword(dbSettings.getPassword());
        return ds;
    }
}