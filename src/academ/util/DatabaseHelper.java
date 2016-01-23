package academ.util;

import oracle.jdbc.pool.OracleDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper {
    private static void createUser() {
        try {
            OracleDataSource ds = new OracleDataSource();
            ds.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
            ds.setUser("bogdan");
            ds.setPassword("tkachuk");

            try (Connection conn = ds.getConnection()) {
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO USERTABLE(id, login, password) VALUES (USERTABLE_ID_SEC.nextval, ?, ?)")) {
                    ps.setString(1, "login");
                    ps.setString(2, "password");
                    ps.executeQuery();
                }
            }
        } catch (SQLException e) {
            System.err.println("error");
        }
    }
}