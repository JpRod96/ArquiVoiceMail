import Persistence.MailboxPersistenceService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import main.Mailbox;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;
public class main2 {


    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);
        String connectionString="jdbc:postgresql://localhost:5432/postgres";
        String user="abel";
        String password="73441710bliokiN";
        String driver="org.postgresql.Driver";

        get("/hello", (req, res) -> {
            MailboxPersistenceService m=new MailboxPersistenceService(connectionString,user,password,driver);
            Mailbox a= m.getMailBoxById(1);
            return a.getGreeting();
        });
        get("/hello/:name", (req,res)->{
            return "Hello, "+ req.params(":name");
        });

        get("/mailboxs", (request, response) -> {
            response.type("application/json");
            MailboxPersistenceService m=new MailboxPersistenceService("jdbc:sqlite:db.db");
            return new Gson().toJson(m.getAllMailboxes2());


        });

    }

}

