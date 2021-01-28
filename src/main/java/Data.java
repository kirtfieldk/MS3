import java.sql.*;


public class Data {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            DataMonster dataMonster = new DataMonster("tester.db", "fillDb.csv");
            dataMonster.makeTable();
            dataMonster.insertValues();
        }catch (ClassNotFoundException e){
            return;
        }
    }
}
