package client;

import adresă.*;

import java.io.*;
import java.util.*;

public class ClientSingleton {

    private static ClientSingleton instanță = null;
    private Set<Client> listă_clienți = new HashSet<>(Collections.emptySet());

    private ClientSingleton() {}

    public static ClientSingleton getInstanță(){
        if (instanță == null){
            instanță = new ClientSingleton();
        }
        return instanță;
    }

    public Set<Client> getClienți() {
        return listă_clienți;
    }

    public void setClienți(Set<Client> listă_clienți) {
        this.listă_clienți = listă_clienți;
    }

    public void citeștefișier(){
        try(var input = new BufferedReader(new FileReader("date/clienți.csv"))) {
            String linie;

            while((linie = input.readLine()) != null){
                String[] câmpuri = linie.split(",");
                Client client = new Client(câmpuri[0], câmpuri[1], câmpuri[2], câmpuri[3], câmpuri[4], new Adresă(câmpuri[5], Integer.parseInt(câmpuri[6]), câmpuri[7], câmpuri[8]));
                listă_clienți.add(client);
            }
        } catch (IOException e) {
            System.out.println("Niciun client salvat în fișier!");
        }
    }

    public void scriefișier(){
        try(FileWriter scriere = new FileWriter("date/clienți.csv")) {
            for (Client client : listă_clienți) {
                scriere.write(client.CSV());
                scriere.write("\n");
            }
        } catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
