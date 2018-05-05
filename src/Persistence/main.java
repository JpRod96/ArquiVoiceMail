package Persistence;

/**
 * Created by Jp on 05/05/2018.
 */
public class main {
    public static void main(String args[]){
        DatabaseService db=new DatabaseService();
        db.load();
        /*db.insertValue(4);
        db.insertValue(7);
        db.deleteValue(8);
        db.deleteValue(9);*/

        db.run();
    }
}
