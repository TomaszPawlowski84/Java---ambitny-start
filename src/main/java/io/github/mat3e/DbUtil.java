package io.github.mat3e;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306";
    private static final String DB_USER = "";
    private static final String DB_PASS = "";
    //private static final String DB_PASS = "";
    private static final String DB_PARAMS = "?characterEncoding=utf8";
//    private static final String DB_PARAMS = "?characterEncoding=utf8&useSSL=false[&nazwa=wartość]*";

    public static Connection connect(String database) throws SQLException {
        String url = DB_URL + (database != null ? "/" + database : "") + DB_PARAMS;
        Connection connection = DriverManager.getConnection(url, DB_USER, DB_PASS);
        return connection;
    }
}
}
