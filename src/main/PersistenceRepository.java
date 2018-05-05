package main;

import java.util.List;

/**
 * Created by Jp on 05/05/2018.
 */
public interface PersistenceRepository {

    MailSystem getMailSystem();

    void saveMailSystem();
}
