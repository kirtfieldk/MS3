import java.io.File;
import java.util.Scanner;


public class Data {
    public static void main(String[] args) {
        DataMonster dataMonster;
        //Create our scanner Object to read user Input and create our Log directory
        Scanner userInput = new Scanner(System.in);
        File logDir = new File("log");
        logDir.mkdir();
        try {
            Class.forName("org.sqlite.JDBC");
            if (args.length == 1)
                dataMonster = new DataMonster(args[0]);
            else{
                System.out.println("Enter CSV File:");
                String csvFile = userInput.nextLine();
                dataMonster = new DataMonster(csvFile);
            }
            //After we set the value to dataMonster we make the table and insert the values
            dataMonster.makeTable();
            dataMonster.insertValues();
        }catch (ClassNotFoundException e){
            return;
        }
    }
}
