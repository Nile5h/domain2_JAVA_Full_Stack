package com.task.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DBUtil {
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/contactdb");
            config.setUsername("admin");
            config.setPassword("secret");
            config.setMaximumPoolSize(20);
            config.setConnectionTimeout(30000);

            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }

    public static void closeConnection(DataSource dataSource) {
        if (dataSource != null) {
            try {
                dataSource.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}