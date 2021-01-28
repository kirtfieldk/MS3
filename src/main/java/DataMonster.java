/*
    Keith Kirtfield
    MS3 Code
    DataMonster is responsible for reading in the data and passing the Data into either
    the SQLite database or the FileWriterMonster Object

    No implementation of the Scanner object; instead, I used the commons CSV
 */
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;

public class DataMonster {
    /*
        Needed vars so that we can define our connection, our needed files
        the errorRecords where we will insert bad rows from the CSV
        And a count of how many were inserted correctly and how many were not
     */
    private Connection conn;
    private String fileName;
    private String csvFile;
    ArrayList<CSVRecord> errorRecords;
    int success;
    int fail;

    public DataMonster(String filename, String csvFile){
        this.fileName = filename;
        this.csvFile = csvFile;
        this.success = 0;
        this.fail = 0;
        this.errorRecords = new ArrayList<>();
        this.getConnectionAndMakeDb();

    }
    public void getConnectionAndMakeDb(){
        String url = "jdbc:sqlite:"+System.getProperty("user.dir") + "/" + this.fileName;
        //Create Database
        try {
            this.conn = DriverManager.getConnection(url);
            conn.getMetaData();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void makeTable(){
        String table = """
                CREATE TABLE IF NOT EXISTS csvValues (
                    id Integer PRIMARY KEY,
                    A varchar(100),
                    B varchar(100),
                    C varchar(100),
                    D varchar(50),
                    E blob,
                    f varchar(100),
                    G varchar(50),
                    H varchar(50),
                    I varchar(50),
                    J blob
                );               
                """;
        try {
            Statement stmt = this.conn.createStatement();
            // create a new table
            stmt.execute(table);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //This method is in charge of inserting values > Iterate through the csv file
    //and push the CSVRecord object to the setStm method
    public void insertValues(){
        try {
            //Create a brand new statment for each iteration
            String stm = "INSERT INTO csvValues(A,B,C,D,E,F,G,H,I,J) values (?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = this.conn.prepareStatement(stm);
            Reader reader = new FileReader(this.csvFile);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            for(CSVRecord record : csvParser) {
//                Check For Null or empty values > Increase fail count and end function
                if(DataMonster.checkIfAnyEmpty(record)){
                    this.fail+=1;
                    this.errorRecords.add(record);
                }else {
                    DataMonster.setStm(record, pstmt);
                    //After we establish that there are no blank values, we execute the stmt and increment success
                    this.success += 1;
                    pstmt.executeUpdate();
                }
            }
        }catch(SQLException e) {
            this.fail += 1;
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        //At the vary end of this function -> Create records
        FileWriterMonster.writeErrorLog(this.errorRecords, this.fileName);
        FileWriterMonster.writeStats(this.fail, this.success, this.fileName);
    }


    //Boolean value to see if any values will be empty and increase the failure count
    public static boolean checkIfAnyEmpty(CSVRecord record){
        return record.get("A").length() == 0 || record.get("B").length() == 0 || record.get("C").length() == 0 ||
                record.get("D").length() == 0 || record.get("E").length() == 0 || record.get("F").length() == 0 ||
                record.get("G").length() == 0 || record.get("H").length() == 0 || record.get("I").length() == 0 ||
                record.get("J").length() == 0;
    }
    //Set the prepared statment with values and returns no value
    public static void setStm(CSVRecord record, PreparedStatement pstmt) throws SQLException{
        pstmt.setString(1, record.get("A").trim());
        pstmt.setString(2, record.get("B").trim());
        pstmt.setString(3, record.get("C").trim());
        pstmt.setString(4, record.get("D").trim());
        pstmt.setString(5, record.get("E").trim());
        pstmt.setString(6, record.get("F").trim());
        pstmt.setString(7, record.get("G").trim());
        pstmt.setString(8, record.get("H").trim());
        pstmt.setString(9, record.get("I").trim());
        pstmt.setString(10, record.get("J").trim());

    }
    //Getters
    public int getSuccess(){
        return this.success;
    }
    public int getFailed(){
        return this.fail;
    }
}
