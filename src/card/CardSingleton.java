package card;

import java.io.*;
import java.util.*;

public class CardSingleton {

    private static CardSingleton instanță = null;
    private List<Card> listă_carduri = new ArrayList<>();
    private CardSingleton() {}

    public static  CardSingleton getInstanță(){
        if (instanță == null){
            instanță = new CardSingleton();
        }
        return instanță;
    }

    public List<Card> getCarduri() {
        return listă_carduri;
    }

    public void setCarduri(List<Card> listă_carduri) {
        this.listă_carduri = listă_carduri;
    }

    public void citeștefișier(){
        try(var input = new BufferedReader(new FileReader("date/carduri.csv"))) {
            String linie;

            while((linie = input.readLine()) != null){
                String[] câmpuri = linie.split(",");
                Card card = new Card(câmpuri[0], câmpuri[1], câmpuri[2], câmpuri[3], câmpuri[4], câmpuri[5]);
                listă_carduri.add(card);
            }
        } catch (IOException e) {
            System.out.println("Niciun card salvat în fișier!");
        }
    }

    public void scriefișier(){
        try(FileWriter scriere = new FileWriter("date/carduri.csv")) {
            for (Card card : listă_carduri) {
                scriere.write(card.CSV());
                scriere.write("\n");
            }
        } catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
