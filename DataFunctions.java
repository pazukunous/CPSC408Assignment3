import java.sql.*;

public class DataFunctions {

    Connection connect = SQLDatabaseConnection.getDatabaseConnection();

    public void TableCreation() throws SQLException {

        PreparedStatement Players = connect.prepareStatement("Create Table IF NOT EXISTS Players(" +
                //"UID int NOT NULL AUTO_INCREMENT," +
                "firstName VARCHAR(50)," +
                "lastName VARCHAR(50)," +
                "region VARCHAR(50)," +
                "main VARCHAR(50)," +
                "favStage VARCHAR(50),"+
                "ssn VARCHAR(50)," +
                "job VARCHAR(50)," +
                "jobCountry VARCHAR(60)," +
                "jobState VARCHAR(50)," +
                "jobCity VARCHAR(50)," +
                "jobZip INTEGER);");
        Players.executeUpdate();

        //Player to SSN
        PreparedStatement ssnPlayerKey = connect.prepareStatement("CREATE TABLE IF NOT EXISTS ssnPlayerKey(" +
                "firstName VARCHAR(50)," +
                "lastName VARCHAR(50)," +
                "ssn VARCHAR(50))");
        ssnPlayerKey.executeUpdate();

        //Player job
        PreparedStatement jobInfo = connect.prepareStatement("CREATE TABLE IF NOT EXISTS jobInfo(" +
                "job VARCHAR(50)," +
                "jobCountry VARCHAR(50)," +
                "jobCity VARCHAR(50)," +
                "jobState VARCHAR(50)," +
                "jobZip INTEGER)");
        jobInfo.executeUpdate();

        //zip to city
        PreparedStatement zipCity = connect.prepareStatement("CREATE TABLE IF NOT EXISTS zipCity(" +
                "jobZip INTEGER," +
                "jobCity VARCHAR(50))");
        zipCity.executeUpdate();

        //city to state
        PreparedStatement cityInfo = connect.prepareStatement("CREATE TABLE IF NOT EXISTS cityInfo(" +
                "jobCity VARCHAR(50)," +
                "jobState VARCHAR(50))");
        cityInfo.executeUpdate();



    }

    public void Players(String firstName, String lastName, String region, String main, String favStage, String ssn, String job, String jobCountry, String jobState, String jobCity, int jobZip) throws SQLException
    {
        PreparedStatement jobInsert = connect.prepareStatement("INSERT INTO Players(firstName, lastName, region, main, favStage, ssn, job, jobCountry, jobState, jobCity, jobZip)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)");

        jobInsert.setString(1, firstName);
        jobInsert.setString(2, lastName);
        jobInsert.setString(3, region);
        jobInsert.setString(4, main);
        jobInsert.setString(5, favStage);
        jobInsert.setString(6, ssn);
        jobInsert.setString(7, job);
        jobInsert.setString(8, jobCountry);
        jobInsert.setString(9, jobState);
        jobInsert.setString(10, jobCity);
        jobInsert.setInt(11, jobZip);

        jobInsert.executeUpdate();
    }

    public void ssnPlayerKey(String firstName, String lastName, String ssn) throws SQLException
    {
        PreparedStatement ssnInsert = connect.prepareStatement("INSERT INTO ssnPlayerKey(firstName, lastName, ssn)" +
                "VALUES(?,?,?)");

        ssnInsert.setString(1, firstName);
        ssnInsert.setString(2, lastName);
        ssnInsert.setString(3, ssn);

        ssnInsert.executeUpdate();
    }

    public void jobInsert(String job, String jobCountry, String jobCity, String jobState, int jobZip) throws SQLException
    {
        PreparedStatement jobInsert = connect.prepareStatement("INSERT INTO jobInfo(job, jobCountry, jobCity, jobState, jobZip)" +
                "VALUES(?,?,?,?,?)");

        jobInsert.setString(1, job);
        jobInsert.setString(2, jobCountry);
        jobInsert.setString(3, jobCity);
        jobInsert.setString(4, jobState);
        jobInsert.setInt(5, jobZip);

        jobInsert.executeUpdate();
    }

    public void zipCityInsert(int jobZip, String jobCity) throws SQLException{
        PreparedStatement zipCity = connect.prepareStatement("INSERT INTO zipCity(jobZip, jobCity)" +
                "VALUES(?,?)");
        zipCity.setInt(1, jobZip);
        zipCity.setString(2, jobCity);

        zipCity.executeUpdate();

    }

    public void cityInfoInsert(String jobCity, String jobState) throws SQLException{
        PreparedStatement cityState = connect.prepareStatement("INSERT INTO cityInfo(jobCity, jobState)" +
                "VALUES(?,?)");
        cityState.setString(1, jobCity);
        cityState.setString(2, jobState);

        cityState.executeUpdate();
    }

    public void PrintTables() throws SQLException{

        PreparedStatement testCompositeKey = connect.prepareStatement("SELECT * FROM Players");
        ResultSet resultsTest = testCompositeKey.executeQuery();
        SQLDatabaseConnection.print(resultsTest);

        PreparedStatement testPlayerKey = connect.prepareStatement("SELECT * FROM ssnPlayerKey");
        ResultSet resultsPlayertest = testPlayerKey.executeQuery();
        SQLDatabaseConnection.print(resultsPlayertest);

        PreparedStatement testJobInfo = connect.prepareStatement("SELECT * FROM jobInfo");
        ResultSet resultsPersonTest = testJobInfo.executeQuery();
        SQLDatabaseConnection.print(resultsPersonTest);

        PreparedStatement testZipCity = connect.prepareStatement("SELECT * FROM zipCity");
        ResultSet resultsZipCity = testZipCity.executeQuery();
        SQLDatabaseConnection.print(resultsZipCity);

        PreparedStatement testCityInfo = connect.prepareStatement("SELECT * FROM cityInfo");
        ResultSet resultsCityInfo = testCityInfo.executeQuery();
        SQLDatabaseConnection.print(resultsCityInfo);


        //Closes the tables once opened.
        testCompositeKey.close();
        testPlayerKey.close();
        testJobInfo.close();
        testZipCity.close();
        testCityInfo.close();




    }


}
