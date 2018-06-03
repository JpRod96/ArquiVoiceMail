import MailVoice.Message;
import Persistence.MailboxPersistenceService;
import Persistence.MessagePersistenceService;
import com.google.gson.Gson;
import MailVoice.Mailbox;
import com.mysql.jdbc.Connection;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;

import static spark.Spark.*;
public class main2 {


    public static void main(String[] args) throws URISyntaxException, SQLException {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);
        //String connectionString="jdbc:postgresql://localhost:5432/postgres";
        //String user="abel";
        //String password="73441710bliokiN";
        String driver="org.postgresql.Driver";
        /* Configuracion para conectar ala base de datos que proporciona heroku */

        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String user= dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String connectionString = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        MailboxPersistenceService mailboxPersistenceService = new MailboxPersistenceService(connectionString, user, password, driver);
        MessagePersistenceService messagePersistenceService= mailboxPersistenceService.getMessagePersistenceService();

        get("/hello", (req, res) -> {
            //MailboxPersistenceService m=new MailboxPersistenceService(connectionString,user,password,driver);
            Mailbox a= mailboxPersistenceService.getMailBoxById(1);
            return a.getGreeting();
        });
        get("/hello/:name", (req,res)->{
            return "Hello, "+ req.params(":name");
        });

        get("/mailboxs", (request, response) -> {
            response.type("application/json");
            //MailboxPersistenceService m=new MailboxPersistenceService(connectionString,user,password,driver);
            return new Gson().toJson(mailboxPersistenceService.getAllMailBoxes());
        });
        get("/mailboxs/:id", (request, response) -> {
            response.type("application/json");
           // MailboxPersistenceService m=new MailboxPersistenceService("jdbc:sqlite:db.db");
            Mailbox a=mailboxPersistenceService.getMailBoxById(1);
            return new Gson().toJson(a);

        });
        post("/messages", (request, response) -> {
            response.type("application/json");

            Message me = new Gson().fromJson(request.body(), Message.class);
            messagePersistenceService.saveMessage(me);

            return "exito";
        });
        post("/mailboxs", (request, response) -> {
            response.type("application/json");
           // MailboxPersistenceService mailboxPersistenceService = new MailboxPersistenceService(connectionString, user, password, driver);
            Mailbox ma = new Gson().fromJson(request.body(), Mailbox.class);
            mailboxPersistenceService.saveMailbox2(ma);

            return "exito";
        });


    }

}

