package cont;

import java.io.*;
import java.util.*;

public class EconomiiSingleton {

    private static EconomiiSingleton instanță = null;
    private List<Economii> listă_economii = new ArrayList<>();

    private EconomiiSingleton() {}

    public static EconomiiSingleton getInstanță(){
        if (instanță == null){
            instanță = new EconomiiSingleton();
        }
        return instanță;
    }

    public List<Economii> getEconomii() {
        return listă_economii;
    }

    public void setEconomii(List<Economii> listă_economii) {
        this.listă_economii = listă_economii;
    }

    public void citeștefișier(){
        try(var input = new BufferedReader(new FileReader("date/economii.csv"))) {
            String linie;

            while((linie = input.readLine()) != null){
                String[] câmpuri = linie.split(",");
                Economii economii = new Economii(câmpuri[0], câmpuri[1], câmpuri[2], Double.parseDouble(câmpuri[3]), Double.parseDouble(câmpuri[4]));
                listă_economii.add(economii);
            }
        } catch (IOException e) {
            System.out.println("Niciun cont de economii salvat în fișier!");
        }
    }

    public void scriefișier(){
        try(FileWriter scriere = new FileWriter("date/economii.csv")) {
            for (Economii economii : listă_economii) {
                scriere.write(economii.CSV());
                scriere.write("\n");
            }
        } catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
