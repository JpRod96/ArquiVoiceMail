import MailVoice.Message;
import Persistence.MailboxPersistenceService;
import Persistence.MessagePersistenceService;
import com.google.gson.Gson;
import MailVoice.Mailbox;
import com.mysql.jdbc.Connection;
import main.MailBoxRepository;
import main.MessageRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;

import static spark.Spark.*;
public class APIService {

    private static main.Connection connection;
    private static MessageRepository messageRepository;
    private static MailBoxRepository mailBoxRepository;

    public static void main(String[] args) throws URISyntaxException, SQLException {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);
        String driver="org.postgresql.Driver";
        /* Configuracion para conectar a una base de datos local */
        String connectionString="jdbc:postgresql://localhost:5432/postgres";
        String user="abel";
        String password="73441710bliokiN";

        /* Configuracion para conectar ala base de datos que proporciona heroku

        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String user= dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String connectionString = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        */
        MailboxPersistenceService mailboxPersistenceService = new MailboxPersistenceService(connectionString, user, password, driver);
        MessagePersistenceService messagePersistenceService = mailboxPersistenceService.getMessagePersistenceService();


        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));



        get("/", (req,res)->{
            return "" +
                    " <h1>Bienvenido a Voice Mail API</h1>" +
                    " <h2>Servicios disponibles: </h2" +
                    " <h3> <h3>obtener todos los mailbox(GET): </h3></h3> <p>/mailboxs</p>" +
                    " <h3>obtener mailbox por ID(GET): </h3> <p>/mailboxs/:id</p>" +
                    " <h3>editar mailbox(PUT): </h3> <p>/mailboxs/:id</p>" +
                    " <h3>crear mensajes(POST): </h3> <p>/messages</p>" +
                    " <h3>eliminar mensaje(DELETE): </h3> <p>/messages/:id</p>" +
                    "";
        });

        get("/mailboxs", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(mailboxPersistenceService.getAllMailBoxes());
        });
        get("/mailboxs/:id", (request, response) -> {
            response.type("application/json");
            Mailbox a=mailboxPersistenceService.getMailBoxById(Integer.parseInt(request.params(":id")));

            return new Gson().toJson(a);

        });
        post("/messages", (request, response) -> {
            response.type("application/json");
            Message me = new Gson().fromJson(request.body(), Message.class);
            messagePersistenceService.saveMessage(me);

            return 0;
        });
        post("/mailboxs", (request, response) -> {
            response.type("application/json");

            Mailbox ma = new Gson().fromJson(request.body(), Mailbox.class);
            mailboxPersistenceService.saveMailbox2(ma);

            return 0;
        });
        delete("/messages/:id", (request, response) -> {
            response.type("application/json");
            Message m= new Message("",Integer.parseInt(request.params(":id")));
            messagePersistenceService.deleteMessage(m);

            return 0;
        });
        put("/mailboxs/:id", (request, response) -> {
            response.type("application/json");
            Mailbox toEdit = new Gson().fromJson(request.body(), Mailbox.class);
            mailboxPersistenceService.updateMailbox(toEdit);

           return 0;
        });


    }

}

