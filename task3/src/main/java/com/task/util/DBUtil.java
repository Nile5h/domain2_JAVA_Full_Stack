package com.task.util;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;

public class DBUtil {
    private static MysqlDataSource dataSource;

    public static DataSource getDataSource() throws SQLException {
        if (dataSource == null) {
            dataSource = new MysqlDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/your_database_name");
            dataSource.setUser("root");
            dataSource.setPassword("NileshSQL");
            // Optional: Handle timezone issues
            dataSource.setServerTimezone("UTC");
        }
        return dataSource;
    }
}