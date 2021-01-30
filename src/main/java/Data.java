import java.io.File;
import java.util.Scanner;


public class Data {
    public static void main(String[] args) {
        //Create our scanner Object to read user Input and create our Log directory
        Scanner userInput = new Scanner(System.in);
        File logDir = new File("log");
        logDir.mkdir();
        try {
            Class.forName("org.sqlite.JDBC");
                if (args.length == 2) {
                    DataMonster dataMonster = new DataMonster(args[0], args[1]);
                    dataMonster.makeTable();
                    dataMonster.insertValues();
                }else{
                    System.out.println("Enter File:");
                    String fileName = userInput.nextLine();
                    System.out.println("Enter CSV File:");
                    String csvFile = userInput.nextLine();
                    DataMonster dataMonster = new DataMonster(fileName, csvFile);
                    dataMonster.makeTable();
                    dataMonster.insertValues();
                }
        }catch (ClassNotFoundException e){
            return;
        }
    }
}
