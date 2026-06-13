package com.task.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            try {
                Context ctx = new InitialContext();
                dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ContactDB");
            } catch (NamingException e) {
                throw new RuntimeException("Failed to lookup DataSource from JNDI", e);
            }
        }
        return dataSource;
    }
}