package db;

import db.mysql.MySQLConnection;

/**
 * Created by Zijin Wang on 10/12/2017.
 */
public class DBConnectionFactory {
    private static final String DEFAULT_DB = "mysql";

    // Create a DBConnection based on given db type.
    public static DBConnection getDBConnection(String db) {
        switch (db) {
            case "mysql":
                return MySQLConnection.getInstance();
            default:
                throw new IllegalArgumentException("Invalid pipeline: " + db);
        }
    }

    public static DBConnection getDBConnection() {
        return getDBConnection(DEFAULT_DB);
    }
}
