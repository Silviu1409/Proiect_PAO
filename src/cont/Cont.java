package cont;

import card.*;
import repository.CardRepo;
import tranzacție.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Cont {

    protected String IBAN, CNP_client, BIC;
    protected double balanță;
    protected List<Card> carduri = new ArrayList<>();
    protected List<Tranzacție> tranzacții = new ArrayList<>();
    private final CardRepo cardRepo = new CardRepo();

    public Cont(String CNP) {
        this.CNP_client = CNP;
        this.IBAN = genereazăIBAN();
        this.BIC = "RNCROBU";
        this.balanță = 0.0;
    }

    public Cont(String CNP, String IBAN, String BIC, double balanță) {
        this.CNP_client = CNP;
        this.IBAN = IBAN;
        this.BIC = BIC;
        this.balanță = balanță;
    }

    public Cont(ResultSet res) throws SQLException {
        this.IBAN = res.getString("IBAN");
        this.CNP_client = res.getString("CNP_client");
        this.BIC = res.getString("BIC");
        this.balanță = res.getDouble("balanță");
    }

    @Override
    public String toString(){
        String output = "IBAN: "+ this.IBAN +"\n";
        output += "BIC: "+ this.BIC +"\n";
        output += "Balanță: "+ this.balanță + " lei" +"\n";
        return output;
    }

    public String CSV(){
        return CNP_client + "," + IBAN + "," + BIC + "," + balanță;
    }

    public String genereazăIBAN(){
        StringBuilder cod = new StringBuilder("RO");
        Random random = new Random();
        for(var i=0; i<2; i++){
            cod.append(random.nextInt(10));
        }
        cod.append("RNCB");
        for(var i=0; i<16; i++){
            cod.append(random.nextInt(10));
        }
        return cod.toString();
    }

    public void atașeazăcardsimplu(Card card){
        this.carduri.add(card);
    }

    public void atașeazăcardvisa(Visa card){
        this.carduri.add(card);
    }

    public void atașeazăcardmaster(MasterCard card){
        this.carduri.add(card);
    }

    public void afișeazăcarduri(){
        if(this.carduri.size() != 0) {
            int i = 0;
            for (Card card : carduri) {
                ++i;
                System.out.printf("\nCard %x\n\n", i);
                System.out.println(card.toString());
                System.out.println();
            }
        }else{
            System.out.println("\nContul nu are niciun card atașat.");
        }
    }

    public void actualizarepincard(String număr){
        for (Card card : carduri) {
            if(Objects.equals(card.getNumăr(), număr)){
                System.out.println();
                System.out.println(card);
                Scanner input = new Scanner(System.in);
                System.out.print("\nPIN nou card [format: XXXX]:");
                String PIN = input.nextLine();
                if(PIN.length() != 4){
                    System.out.println("\nFormat PIN nevalid!");
                    return;
                }
                card.setPIN(PIN);
                System.out.println("\nPIN actualizat cu succes");
                return;
            }
        }
        System.out.println("\nCardul nu există.");
    }

    public void actualizarepincardbd(String număr){
        for (Card card : carduri) {
            if(Objects.equals(card.getNumăr(), număr)){
                System.out.println();
                System.out.println(card);
                Scanner input = new Scanner(System.in);
                System.out.print("\nPIN nou card [format: XXXX]: ");
                String PIN = input.nextLine();
                if(PIN.length() != 4){
                    System.out.println("\nFormat PIN nevalid!");
                    return;
                }
                card.setPIN(PIN);
                cardRepo.actualizeazăPinCard(card);
                System.out.println("\nPIN actualizat cu succes");
                return;
            }
        }
        System.out.println("\nCardul nu există.");
    }

    public void adaugătranzacție(Tranzacție tranzacție){
        this.tranzacții.add(tranzacție);
    }

    public void afișeazătranzacții(){
        if(this.tranzacții.size() != 0){
            System.out.println("Istoric tranzacții:\n");
            for(Tranzacție tranzacție: tranzacții){
                System.out.println(tranzacție.toString());
                System.out.println();
            }
        } else {
            System.out.println("Contul nu are nicio tranzacție.");
        }
    }

    public void eliminăcard(String număr){
        boolean existăcard = this.carduri.removeIf(card -> Objects.equals(card.getNumăr(), număr));
        if(!existăcard){
            System.out.println("Cardul nu există!");
        } else {
            System.out.println("Cardul a fost eliminat cu succes!");
        }
    }

    public void eliminăcardbd(String număr){
        boolean existăcard = this.carduri.removeIf(card -> Objects.equals(card.getNumăr(), număr));
        cardRepo.șterge(număr);
        if(!existăcard){
            System.out.println("Cardul nu există!");
        } else {
            System.out.println("Cardul a fost eliminat cu succes!");
        }
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getCNP_client() {
        return CNP_client;
    }

    public void setCNP_client(String CNP_client) {
        this.CNP_client = CNP_client;
    }

    public String getBIC() {
        return BIC;
    }

    public void setBIC(String BIC) {
        this.BIC = BIC;
    }

    public double getBalanță() {
        return balanță;
    }

    public void setBalanță(double balanță) {
        this.balanță = balanță;
    }

    public List<Card> getCarduri() {
        return carduri;
    }

    public void setCarduri(List<Card> carduri) {
        this.carduri = carduri;
    }

    public List<Tranzacție> getTranzacții() {
        return tranzacții;
    }

    public void setTranzacții(List<Tranzacție> tranzacții) {
        this.tranzacții = tranzacții;
    }
}
