import java.sql.*;

public class SQLDatabaseConnection {

    static void print(ResultSet results) throws SQLException {
        ResultSetMetaData data = results.getMetaData();

        int numberOfColumns = data.getColumnCount();
        for (int i = 1; i<= numberOfColumns; i++) {
            if (i > 1) System.out.print(" ");
            String columnName = data.getColumnName(i);
            System.out.print(columnName);
        }

        System.out.println();

        while (results.next()) {
            for (int i = 1; i <= numberOfColumns; i++) {
                if (i > 1) System.out.print(" ");
                String columnValue = results.getString(i);
                System.out.print(columnValue);
            }
            System.out.println();
        }

    }

    static Connection getDatabaseConnection()
    {
        try{
            return DriverManager.getConnection("jdbc:mysql://35.247.113.127/meleeplayers?useSSL=false","test","password");


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}