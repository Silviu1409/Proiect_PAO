package client;

import card.*;
import cont.*;
import tranzacție.*;
import adresă.*;
import repository.*;

import java.sql.*;
import java.util.*;

public class Client {

    private final String CNP;
    private String nume, prenume, email, nr_tel;
    private Adresă adresă;
    private List<Cont> conturi = new ArrayList<>();
    private final Scanner input = new Scanner(System.in);
    private final CardRepo cardRepo = new CardRepo();
    private final ContRepo contRepo = new ContRepo();
    private final TranzacțieRepo tranzacțieRepo = new TranzacțieRepo();

    public Client(String nume, String prenume, String CNP, String email, String nr_tel, Adresă adresă) {
        this.nume = nume;
        this.prenume = prenume;
        this.CNP = CNP;
        this.email = email;
        this.nr_tel = nr_tel;
        this.adresă = adresă;
    }

    public Client(ResultSet res) throws SQLException {
        this.CNP = res.getString("CNP");
        this.nume = res.getString("nume");
        this.prenume = res.getString("prenume");
        this.email = res.getString("email");
        this.nr_tel = res.getString("nr_tel");
    }

    @Override
    public String toString(){
        String output = "Nume: "+ this.nume +"\n";
        output += "Prenume: "+ this.prenume +"\n";
        output += "CNP: "+ this.CNP +"\n";
        output += "Email: "+ this.email +"\n";
        output += "Nr. telefon: "+ this.nr_tel +"\n";
        if(this.adresă != null) {
            output += "Detalii adresă: \n";
            output += this.adresă;
        } else {
            output += "Nu se știu detalii despre adresa clientului.\n";
        }
        return output;
    }

    public String CSV(){
        return nume + "," + prenume + "," + CNP + "," + email + "," + nr_tel + "," + adresă.CSV();
    }

    public void adaugăcont(Cont cont){
        this.conturi.add(cont);
    }

    public void ștergecont(String IBAN){
        for(Cont cont: this.conturi){
            if(IBAN.equals(cont.getIBAN())){
                this.conturi.remove(cont);
                System.out.println("Contul a fost șters!");
                return;
            }
        }
        System.out.println("Contul nu există!");
    }

    public void ștergecontbd(String IBAN){
        for(Cont cont: this.conturi){
            if(IBAN.equals(cont.getIBAN())){
                this.conturi.remove(cont);
                contRepo.șterge(IBAN);
                System.out.println("Contul a fost șters!");
                return;
            }
        }
        System.out.println("Contul nu există!");
    }

    public void afișeazădetaliicont(String IBAN){
        for(var cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)){
                System.out.print(cont);
                return;
            }
        }
        System.out.println("Contul nu există!");
    }

    public void atașeazăcard(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)){
                System.out.println("Ce tip de card vrei să faci?");
                System.out.println("1 - simplu");
                System.out.println("2 - Visa");
                System.out.println("3 - MasterCard");
                int opt = Integer.parseInt(input.nextLine());
                switch (opt) {
                    case 1 -> {
                        Card card = new Card(this.getPrenume() + " " + this.getNume(), IBAN);
                        cont.atașeazăcardsimplu(card);
                        System.out.println("Card adăugat!");
                    }
                    case 2 -> {
                        System.out.println("Are asigurare de călătorie? [da/nu]");
                        String asig = input.nextLine();
                        Visa card;
                        if(Objects.equals(asig, "da")){
                            card = new Visa(this.getPrenume() + " " + this.getNume(), IBAN, true);
                        } else if (Objects.equals(asig, "nu")) {
                            card = new Visa(this.getPrenume() + " " + this.getNume(), IBAN, false);
                        } else{
                            System.out.println("Opțiune nevalidă.");
                            return;
                        }
                        cont.atașeazăcardvisa(card);
                        System.out.println("Card adăugat!");
                    }
                    case 3 -> {
                        System.out.println("Nivel [Standard/Gold/Platinum/World]");
                        String nivel = input.nextLine();
                        MasterCard card;
                        if(Objects.equals(nivel, "Standard") || Objects.equals(nivel, "Gold") || Objects.equals(nivel, "Platinum") || Objects.equals(nivel, "World")) {
                            card = new MasterCard(this.getPrenume() + " " + this.getNume(), IBAN, nivel);
                        }
                        else{
                            System.out.println("Opțiune nevalidă.");
                            return;
                        }
                        cont.atașeazăcardmaster(card);
                        System.out.println("Card adăugat!");
                    }
                }
                return;
            }
        }
        System.out.println("Contul nu există!");
    }

    public void atașeazăcardbd(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)){
                System.out.println("Ce tip de card vrei să faci?");
                System.out.println("1 - simplu");
                System.out.println("2 - Visa");
                System.out.println("3 - MasterCard");
                int opt = Integer.parseInt(input.nextLine());
                switch (opt) {
                    case 1 -> {
                        Card card = new Card(this.getPrenume() + " " + this.getNume(), IBAN);
                        cont.atașeazăcardsimplu(card);
                        cardRepo.adaugăCard(card);
                        System.out.println("Card adăugat!");
                    }
                    case 2 -> {
                        System.out.println("Are asigurare de călătorie? [da/nu]");
                        String asig = input.nextLine();
                        Visa card;
                        if(Objects.equals(asig, "da")){
                            card = new Visa(this.getPrenume() + " " + this.getNume(), IBAN, true);
                        } else if (Objects.equals(asig, "nu")) {
                            card = new Visa(this.getPrenume() + " " + this.getNume(), IBAN, false);
                        } else{
                            System.out.println("Opțiune nevalidă.");
                            return;
                        }
                        cont.atașeazăcardvisa(card);
                        cardRepo.adaugăVisa(card);
                        System.out.println("Card adăugat!");
                    }
                    case 3 -> {
                        System.out.println("Nivel [Standard/Gold/Platinum/World]:");
                        String nivel = input.nextLine();
                        MasterCard card;
                        if(Objects.equals(nivel, "Standard") || Objects.equals(nivel, "Gold") || Objects.equals(nivel, "Platinum") || Objects.equals(nivel, "World")) {
                            card = new MasterCard(this.getPrenume() + " " + this.getNume(), IBAN, nivel);
                        }
                        else{
                            System.out.println("Opțiune nevalidă.");
                            return;
                        }
                        cont.atașeazăcardmaster(card);
                        cardRepo.adaugăMasterCard(card);
                        System.out.println("Card adăugat!");
                    }
                }
                return;
            }
        }
        System.out.println("Contul nu există!");
    }

    public void afișeazăcarduri(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)){
                cont.afișeazăcarduri();
                return;
            }
        }
        System.out.println("Contul nu există!");
    }

    public void actualizarepincard(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)){
                System.out.print("Numărul cardului: ");
                String număr = input.nextLine();
                cont.actualizarepincard(număr);
                return;
            }
        }
        System.out.println("Contul nu există!");
    }

    public void actualizarepincardbd(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)){
                System.out.print("Numărul cardului: ");
                String număr = input.nextLine();
                cont.actualizarepincardbd(număr);
                return;
            }
        }
        System.out.println("Contul nu există!");
    }

    public void depunerenumerar(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)){
                System.out.println("\nCe sumă vrei să depui?");
                int sumă = Integer.parseInt(input.nextLine());
                cont.setBalanță(cont.getBalanță() + sumă);
                System.out.println("\nCont alimentat cu succes!");
                return;
            }
        }
        System.out.println("\nContul nu există!");
    }

    public void depunerenumerarbd(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)){
                System.out.println("\nCe sumă vrei să depui?");
                int sumă = Integer.parseInt(input.nextLine());
                cont.setBalanță(cont.getBalanță() + sumă);
                contRepo.actualizeazăBalanțăCont(cont);
                System.out.println("\nCont alimentat cu succes!");
                return;
            }
        }
        System.out.println("\nContul nu există!");
    }

    public void retragerenumerar(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)) {
                System.out.printf("\nBalanță: %.2f\n", cont.getBalanță());
                System.out.println("Ce sumă vrei să retragi?");
                int sumă = Integer.parseInt(input.nextLine());
                if (sumă > cont.getBalanță()){
                    System.out.println("\nSuma pe care vrei să o retragi este mai mare decât balanța ta!");
                }else {
                    cont.setBalanță(cont.getBalanță() - sumă);
                    System.out.println("\nRetragere făcută cu succes!");
                }
                return;
            }
        }
        System.out.println("\nContul nu există!");
    }

    public void retragerenumerarbd(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)) {
                System.out.printf("\nBalanță: %.2f\n", cont.getBalanță());
                System.out.println("Ce sumă vrei să retragi?");
                int sumă = Integer.parseInt(input.nextLine());
                if (sumă > cont.getBalanță()){
                    System.out.println("\nSuma pe care vrei să o retragi este mai mare decât balanța ta!");
                }else {
                    cont.setBalanță(cont.getBalanță() - sumă);
                    contRepo.actualizeazăBalanțăCont(cont);
                    System.out.println("\nRetragere făcută cu succes!");
                }
                return;
            }
        }
        System.out.println("\nContul nu există!");
    }

    public Tranzacție adăugaretranzacție(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)) {
                System.out.print("Tip(intrabanking/interbanking): ");
                String tip = input.nextLine();
                if(!Objects.equals(tip, "intrabanking") && !Objects.equals(tip, "interbanking")){
                    System.out.println("Opțiune incorectă.");
                    return null;
                }
                System.out.print("Codul IBAN al beneficiarului: ");
                String IBAN_ben = input.nextLine();
                System.out.print("Numele beneficiarului: ");
                String nume = input.nextLine();
                System.out.print("Sumă: ");
                double sumă = Double.parseDouble(input.nextLine());
                if (sumă > cont.getBalanță()){
                    System.out.print("Suma pe care vrei să o retragi este mai mare decât balanța ta!");
                    return null;
                }else {
                    System.out.println("Detalii:");
                    String detalii = input.nextLine();
                    Tranzacție tranzacție = new Tranzacție(tip, IBAN, IBAN_ben, nume, detalii, sumă);
                    cont.adaugătranzacție(tranzacție);
                    System.out.println("Tranzacție efectuată cu succes!");
                    cont.setBalanță(cont.getBalanță() - sumă);
                    return tranzacție;
                }
            }
        }
        System.out.println("Contul nu există!");
        return null;
    }

    public Tranzacție adăugaretranzacțiebd(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)) {
                System.out.print("Tip(intrabanking/interbanking): ");
                String tip = input.nextLine();
                if(!Objects.equals(tip, "intrabanking") && !Objects.equals(tip, "interbanking")){
                    System.out.println("Opțiune incorectă.");
                    return null;
                }
                System.out.print("Codul IBAN al beneficiarului: ");
                String IBAN_ben = input.nextLine();
                System.out.print("Numele beneficiarului: ");
                String nume = input.nextLine();
                System.out.print("Sumă: ");
                double sumă = Double.parseDouble(input.nextLine());
                if (sumă > cont.getBalanță()){
                    System.out.print("Suma pe care vrei să o retragi este mai mare decât balanța ta!");
                    return null;
                }else {
                    System.out.println("Detalii:");
                    String detalii = input.nextLine();
                    Tranzacție tranzacție = new Tranzacție(tip, IBAN, IBAN_ben, nume, detalii, sumă);
                    cont.adaugătranzacție(tranzacție);
                    tranzacțieRepo.adaugă(tranzacție);
                    System.out.println("Tranzacție efectuată cu succes!");
                    cont.setBalanță(cont.getBalanță() - sumă);
                    contRepo.actualizeazăBalanțăCont(cont);
                    return tranzacție;
                }
            }
        }
        System.out.println("Contul nu există!");
        return null;
    }

    public void afișaretranzacțiidesc(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)) {
                List<Tranzacție> l_tranzacții = cont.getTranzacții();
                if(l_tranzacții.size() > 0) {
                    Comparator<Tranzacție> sortare_sumă = Comparator.comparingDouble(Tranzacție::getSumă);
                    l_tranzacții.sort(Collections.reverseOrder(sortare_sumă));
                    System.out.println("Lista de tranzacții sortată descrescător după sumă:");
                    for (Tranzacție tranzacție : l_tranzacții) {
                        System.out.println();
                        System.out.println(tranzacție.toString());
                    }
                }else{
                    System.out.println("Contul nu are nicio tranzacție.");
                }
                return;
            }
        }
        System.out.println("Contul nu există!");
    }

    public void afișaretranzacțiidescbd(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)) {
                List<Tranzacție> l_tranzacții_sortată = tranzacțieRepo.citeșteTranzacțiiSortate();
                if(l_tranzacții_sortată.size() > 0) {
                    System.out.println("Lista de tranzacții sortată descrescător după sumă:");
                    for (Tranzacție tranzacție : l_tranzacții_sortată) {
                        System.out.println();
                        System.out.println(tranzacție.toString());
                    }
                }else{
                    System.out.println("Contul nu are nicio tranzacție.");
                }
                return;
            }
        }
        System.out.println("Contul nu există!");
    }

    public void extrasdecont(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)) {
                if(cont.getClass() == Cont.class){
                    System.out.printf("\nBalanță cont curent: %.2f\n\n", cont.getBalanță());
                } else if (cont.getClass() == Economii.class) {
                    System.out.printf("\nBalanță cont de economii: %.2f\n\n", cont.getBalanță());
                } else{
                    System.out.printf("\nBalanță depozit: %.2f\n\n", cont.getBalanță());
                }
                cont.afișeazătranzacții();
                return;
            }
        }
        System.out.println("\nContul nu există!");
    }

    public void ștergecard(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)) {
                System.out.println("\nCardurile atașate contului:");
                cont.afișeazăcarduri();
                System.out.print("Numărul cardului pe care vrei să il elimini: ");
                String număr = input.nextLine();
                cont.eliminăcard(număr);
                return;
            }
        }
        System.out.println("Contul nu există!");
    }

    public void ștergecardbd(String IBAN){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)) {
                System.out.println("\nCardurile atașate contului:");
                cont.afișeazăcarduri();
                System.out.print("Numărul cardului pe care vrei să il elimini: ");
                String număr = input.nextLine();
                cont.eliminăcardbd(număr);
                return;
            }
        }
        System.out.println("Contul nu există!");
    }

    public void adaugănumerar(String IBAN, double sumă){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)){
                cont.setBalanță(cont.getBalanță() + sumă);
                return;
            }
        }
    }

    public void adaugănumerarbd(String IBAN, double sumă){
        for(Cont cont: this.conturi){
            if(Objects.equals(cont.getIBAN(), IBAN)){
                cont.setBalanță(cont.getBalanță() + sumă);
                contRepo.actualizeazăBalanțăCont(cont);
                return;
            }
        }
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCNP() {
        return CNP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNr_tel() {
        return nr_tel;
    }

    public void setNr_tel(String nr_tel) {
        this.nr_tel = nr_tel;
    }

    public Adresă getAdresă() {
        return adresă;
    }

    public void setAdresă(Adresă adresă) {
        this.adresă = adresă;
    }

    public List<Cont> getConturi() {
        return conturi;
    }

    public void setConturi(List<Cont> conturi) {
        this.conturi = conturi;
    }
}
