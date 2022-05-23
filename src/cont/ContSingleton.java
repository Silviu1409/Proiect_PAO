package cont;

import java.io.*;
import java.util.*;

public class ContSingleton {

    private static ContSingleton instanță = null;
    private List<Cont> listă_conturi = new ArrayList<>();

    private ContSingleton() {}

    public static ContSingleton getInstanță(){
        if (instanță == null){
            instanță = new ContSingleton();
        }
        return instanță;
    }

    public List<Cont> getConturi() {
        return listă_conturi;
    }

    public void setConturi(List<Cont> listă_conturi) {
        this.listă_conturi = listă_conturi;
    }

    public void citeștefișier(){
        try(var input = new BufferedReader(new FileReader("date/conturi.csv"))) {
            String linie;

            while((linie = input.readLine()) != null){
                String[] câmpuri = linie.split(",");
                Cont cont = new Cont(câmpuri[0], câmpuri[1], câmpuri[2], Double.parseDouble(câmpuri[3]));
                listă_conturi.add(cont);
            }
        } catch (IOException e) {
            System.out.println("Niciun cont salvat în fișier!");
        }
    }

    public void scriefișier(){
        try(FileWriter scriere = new FileWriter("date/conturi.csv")) {
            for (Cont cont : listă_conturi) {
                scriere.write(cont.CSV());
                scriere.write("\n");
            }
        } catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
