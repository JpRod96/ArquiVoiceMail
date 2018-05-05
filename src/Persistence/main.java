package Persistence;

/**
 * Created by Jp on 05/05/2018.
 */
public class main {
    public static void main(String args[]){
        DatabaseUser db=new DatabaseUser();
        db.load();
        db.insertValue(4);
        db.insertValue(7);
        db.deleteValue(8);
        db.deleteValue(9);

        db.run();
    }
}
