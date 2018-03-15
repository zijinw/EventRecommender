package db;

import db.mongodb.MongoDBConnection;
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
            case "mongodb":
                return MongoDBConnection.getInstance();
            default:
                throw new IllegalArgumentException("Invalid pipeline: " + db);
        }
    }

    public static DBConnection getDBConnection() {
        return getDBConnection(DEFAULT_DB);
    }
}
