package Persistence;

        import java.sql.Statement;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;

public class DatabaseUser {
    private Connection connectionObj;
    private Statement statementObj;
    private ResultSet resultSet;
    private final String dbString = "jdbc:mysql://localhost:3306/ejemplo";
    private final String userName = "root";
    private final String password = "mysql";
    private String SQLString ="SELECT * FROM example";

    public void load(){
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connectionObj = DriverManager.getConnection(dbString,userName,password);
            statementObj = connectionObj.createStatement();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void run()
    {
        try
        {
            resultSet = statementObj.executeQuery(SQLString);

            while(resultSet.next())
            {
                System.out.print("ID:"+resultSet.getString("id"));
                System.out.println("");
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                resultSet.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        }


    }
    void insertValue(int i) {

        try {
            statementObj.executeUpdate("INSERT INTO `ejemplo`.`example` (`id`) VALUES" + "('" + i + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void deleteValue(int i){
        try {
            statementObj.executeUpdate("DELETE FROM `ejemplo`.`example` WHERE  `id`="+i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}