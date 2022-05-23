package mainservice;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Audit {

    FileWriter scriere;
    final DateTimeFormatter formatare_dată = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public void log(String comandă) throws IOException {
        scriere.append(comandă);
        scriere.append(",");
        scriere.append(formatare_dată.format(LocalDateTime.now()));
        scriere.append("\n");
        scriere.flush();
    }

    public Audit() throws IOException {
        this.scriere = new FileWriter("date/audit.csv", true);
    }
}
