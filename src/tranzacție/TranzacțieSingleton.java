package tranzacție;

import java.io.*;
import java.util.*;

public class TranzacțieSingleton {
    private static TranzacțieSingleton instanță = null;
    private List<Tranzacție> listă_tranzacții = new ArrayList<>();

    private TranzacțieSingleton() {}

    public static  TranzacțieSingleton getInstanță(){
        if (instanță == null){
            instanță = new TranzacțieSingleton();
        }
        return instanță;
    }

    public List<Tranzacție> getTranzacții() {
        return listă_tranzacții;
    }

    public void setTranzacții(List<Tranzacție> listă_tranzacții) {
        this.listă_tranzacții = listă_tranzacții;
    }

    public void citeștefișier(){
        try(var input = new BufferedReader(new FileReader("date/tranzacții.csv"))) {
            String linie;

            while((linie = input.readLine()) != null){
                String[] câmpuri = linie.split(",");
                Tranzacție tranzacție = new Tranzacție(câmpuri[0], câmpuri[1], câmpuri[2], câmpuri[3], câmpuri[4], Double.parseDouble(câmpuri[5]), câmpuri[6]);
                listă_tranzacții.add(tranzacție);
            }
        } catch (IOException e) {
            System.out.println("Nicio tranzacție salvată în fișier!");
        }
    }

    public void scriefișier(){
        try(FileWriter scriere = new FileWriter("date/tranzacții.csv")) {
            for (Tranzacție tranzacții : listă_tranzacții) {
                scriere.write(tranzacții.CSV());
                scriere.write("\n");
            }
        } catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
