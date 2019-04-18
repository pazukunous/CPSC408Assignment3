import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;


public class ParseData {

    public String firstName;
    public String lastName;
    public String region;
    public String main;
    public String favStage;
    public String ssn;
    public String job;
    public String jobState;
    public String jobCity;
    public int jobZip;
    public String jobCountry;

    DataFunctions myDB = new DataFunctions();

    private String FilePath;

    public void setFilePath(String filePath)
    {
        FilePath = filePath;
    }

    public void readData() throws IOException {

        try (
                Reader reader = Files.newBufferedReader(Paths.get(FilePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

                ) {
            for (CSVRecord csvRecord : csvParser)
            {
                firstName = csvRecord.get(0);
                lastName = csvRecord.get(1);
                region = csvRecord.get(2);
                main = csvRecord.get(3);
                favStage = csvRecord.get(4);
                ssn = csvRecord.get(5);
                job = csvRecord.get(6);
                jobCountry = csvRecord.get(7);
                jobState = csvRecord.get(8);
                jobCity = csvRecord.get(9);
                jobZip = Integer.valueOf(csvRecord.get(10));
                try {
                    myDB.Players(firstName, lastName, region, main, favStage, ssn, job, jobCountry, jobState, jobCity, jobZip);
                    myDB.ssnPlayerKey(firstName, lastName, ssn);
                    myDB.cityInfoInsert(jobCity, jobState);
                    myDB.jobInsert(job, jobCountry, jobCity, jobState, jobZip);
                    myDB.zipCityInsert(jobZip, jobCity);
                }
                catch(SQLException e)
                {
                    System.out.println(e);
                }
            }
        }
    }

}
