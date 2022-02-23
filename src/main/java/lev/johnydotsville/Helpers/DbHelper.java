package lev.johnydotsville.Helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbHelper {
    public static int getMaxIdFromTable(Connection connection, String idColumnName, String tableName) {
        try {
            // Параметризация, увы, не работает для имен таблиц и столбцов, поэтому приходится пользоваться конкатенацией
            PreparedStatement prep = connection.prepareStatement("select max(" + idColumnName + ") as maxId from " + tableName);
            ResultSet result = prep.executeQuery();

            result.next();
            return result.getInt("maxId");
        }
        catch (SQLException se) {
            se.printStackTrace();
            return -1;
        }
    }
}
