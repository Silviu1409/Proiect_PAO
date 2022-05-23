package card;

import java.io.*;
import java.util.*;

public class VisaSingleton {

    private static VisaSingleton instanță = null;
    private List<Visa> listă_carduri = new ArrayList<>();

    private VisaSingleton() {}

    public static VisaSingleton getInstanță(){
        if (instanță == null){
            instanță = new VisaSingleton();
        }
        return instanță;
    }

    public List<Visa> getCarduri() {
        return listă_carduri;
    }

    public void setCarduri(List<Visa> listă_carduri) {
        this.listă_carduri = listă_carduri;
    }

    public void citeștefișier(){
        try(var input = new BufferedReader(new FileReader("date/carduriVisa.csv"))) {
            String linie;

            while((linie = input.readLine()) != null){
                String[] câmpuri = linie.split(",");
                Visa card = new Visa(câmpuri[0], câmpuri[1], câmpuri[2], câmpuri[3], câmpuri[4], câmpuri[5], Boolean.parseBoolean(câmpuri[6]));
                listă_carduri.add(card);
            }
        } catch (IOException e) {
            System.out.println("Niciun card Visa salvat în fișier!");
        }
    }

    public void scriefișier(){
        try(FileWriter scriere = new FileWriter("date/carduriVisa.csv")) {
            for (Visa card : listă_carduri) {
                scriere.write(card.CSV());
                scriere.write("\n");
            }
        } catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
