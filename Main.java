import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter direct filepath to your .csv file");
        String filePath = input.next();
        System.out.println("Input received");


        Connection connect = SQLDatabaseConnection.getDatabaseConnection();


        DataFunctions tableCreator = new DataFunctions();
        tableCreator.TableCreation();

        ParseData dataParser = new ParseData();

        dataParser.setFilePath(filePath);

        dataParser.readData();

        tableCreator.PrintTables();

        connect.close();
    }

}