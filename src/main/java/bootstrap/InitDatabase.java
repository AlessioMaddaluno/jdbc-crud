package bootstrap;

import db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDatabase {

    private InitDatabase(){}

    public static void init(){
        Connection connection = DBConnection.getConnection();
        try(Statement statement = connection.createStatement()) {
            statement.execute(getCustomersCreateQuery());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static String getCustomersCreateQuery(){
        return "CREATE TABLE IF NOT EXISTS customers (\n" +
                "\tID_CUSTOMER IDENTITY NOT NULL PRIMARY KEY,\n" +
                "\tFIRST_NAME varchar(255) NOT NULL,\n" +
                "\tLAST_NAME varchar(255) NOT NULL,\n" +
                "\tEMAIL varchar(255) \n" +
                ");";
    }
}
