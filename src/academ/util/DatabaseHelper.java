package academ.util;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DatabaseHelper {

    public static OracleDataSource getDataSource () throws SQLException
    {
        FileUtil fu = new FileUtil();
        fu.readConnection();
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(fu.getURL());
        ds.setUser(fu.getUser());
        ds.setPassword(fu.getPassword());
        return ds;
    }

    public static void createUser(String name, String log, String pass) {
        try {
            OracleDataSource ds = getDataSource();
           /* ds.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
            ds.setUser("bogdan");
            ds.setPassword("tkachuk");*/

            try (Connection conn = ds.getConnection()) {
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO USERS(user_log, user_pass, user_name) VALUES (? , ?, ?)")) {
                    ps.setString(1, log);
                    ps.setString(2, pass);
                    ps.setString(3, name);
                    ps.executeQuery();
                }
            }
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
    }

public static boolean loginCheck(String log) {
    boolean b = false;
    try {
        OracleDataSource ds = getDataSource();
//        OracleDataSource ds = new OracleDataSource();
//        ds.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
//        ds.setUser("bogdan");
//        ds.setPassword("tkachuk");

        try (Connection conn = ds.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT user_log FROM users WHERE user_log = ?")) {
                ps.setString(1, log);
                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()){
                        String s = rs.getString("user_log");
                        if(s.equals(log)) b=true;
                    }
                };
            }
        }
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    }

    return b;
}

    public static boolean userCheck(String log, String pas){
        boolean b = false;
        try {
            OracleDataSource ds = getDataSource();
//            OracleDataSource ds = new OracleDataSource();
//            ds.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
//            ds.setUser("bogdan");
//            ds.setPassword("tkachuk");

            try (Connection conn = ds.getConnection()) {
                try (PreparedStatement ps = conn.prepareStatement(
                        "SELECT user_log, user_pass FROM users WHERE user_log = ?")) {
                    ps.setString(1, log);
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            String s = rs.getString("user_log");
                            String p = rs.getString("user_pass");
                            if(s.equals(log)&&p.equals(pas)) b=true;
                        }
                    };
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return b;
    }




}