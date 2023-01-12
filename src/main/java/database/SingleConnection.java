package database;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;

@Getter
@Setter
public class SingleConnection {

    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String password = "celk";
    private static String user = "celk";
    private static Connection conn = null;

    public SingleConnection() {
        connect();
    }

    static {
        connect();
    }

    public static Connection connect() {
        try {

            if (conn == null) {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(url, user, password);
                conn.setAutoCommit(false);
                System.out.println("Connectou com sucesso!");
            }
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
