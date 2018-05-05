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

    public void run()
    {
        try
        {

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connectionObj = DriverManager.getConnection(dbString,userName,password);
            statementObj = connectionObj.createStatement();
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
                connectionObj.close();
                statementObj.close();
                resultSet.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        }


    }

}