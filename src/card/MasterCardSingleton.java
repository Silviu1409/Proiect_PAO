package card;

import java.io.*;
import java.util.*;

public class MasterCardSingleton {

    private static MasterCardSingleton instanță = null;
    private List<MasterCard> listă_carduri = new ArrayList<>();

    private MasterCardSingleton() {}

    public static MasterCardSingleton getInstanță(){
        if (instanță == null){
            instanță = new MasterCardSingleton();
        }
        return instanță;
    }

    public List<MasterCard> getCarduri() {
        return listă_carduri;
    }

    public void setCarduri(List<MasterCard> listă_carduri) {
        this.listă_carduri = listă_carduri;
    }

    public void citeștefișier(){
        try(var input = new BufferedReader(new FileReader("date/carduriMaster.csv"))) {
            String linie;

            while((linie = input.readLine()) != null){
                String[] câmpuri = linie.split(",");
                MasterCard card = new MasterCard(câmpuri[0], câmpuri[1], câmpuri[2], câmpuri[3], câmpuri[4], câmpuri[5], câmpuri[6]);
                listă_carduri.add(card);
            }
        } catch (IOException e) {
            System.out.println("Niciun card MasterCard salvat în fișier!");
        }
    }

    public void scriefișier(){
        try(FileWriter scriere = new FileWriter("date/carduriMaster.csv")) {
            for (MasterCard card : listă_carduri) {
                scriere.write(card.CSV());
                scriere.write("\n");
            }
        } catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
