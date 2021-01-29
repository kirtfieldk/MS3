import java.util.Scanner;


public class Data {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
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
