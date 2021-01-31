/*
    Keith Kirtfield
    MS3 Code
    FileWriterMonster is responsible for writting stats into
    a .log file and .csv file. These new files are placed into
    the current Directory.

    Use of FileWriter and Common CSV
 */
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriterMonster {
    //Mainly a static class -> No reason to create an instance of this class
    public static void writeErrorLog(ArrayList<CSVRecord> errors, String file) throws IOException{
        String outputFileName = System.getProperty("user.dir")+"/log/" + file + "-bad.csv";
        String[] headers = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(outputFileName), CSVFormat.DEFAULT.withHeader(headers));
        for(CSVRecord record: errors){
            //For each record in the errors array will be written into this csv file
            csvPrinter.printRecord(record.get("A"), record.get("B"),record.get("C"),
                    record.get("D"), record.get("E"), record.get("F"), record.get("G"),
                    record.get("H"), record.get("I"), record.get("J")
            );
        }
        csvPrinter.close();
    }
    //Standard method to write three lines > Records, failed, success
    public static void writeStats(int fail, int success, String file) throws  IOException{
            int sum = success + fail;
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir")+"/log/" +file + ".log");
            myWriter.write("Number of Records: " + sum + "\n");
            myWriter.write("Number of Success Records: " + success  + "\n");
            myWriter.write("Number of Failed Records: " + fail + "\n");
            myWriter.close();
        }
}
