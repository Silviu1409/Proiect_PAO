package mainservice;

import card.*;
import client.ClientSingleton;
import cont.*;
import tranzacție.TranzacțieSingleton;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    /*
    // Etapa 2 - persistență folosind fișiere CSV
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Serviciu serviciu = new Serviciu();
        Audit audit = new Audit();

        ClientSingleton.getInstanță().citeștefișier();
        TranzacțieSingleton.getInstanță().citeștefișier();
        ContSingleton.getInstanță().citeștefișier();
        EconomiiSingleton.getInstanță().citeștefișier();
        DepozitSingleton.getInstanță().citeștefișier();
        CardSingleton.getInstanță().citeștefișier();
        MasterCardSingleton.getInstanță().citeștefișier();
        VisaSingleton.getInstanță().citeștefișier();

        serviciu.setclienți(ClientSingleton.getInstanță().getClienți());
        serviciu.setconturi(ContSingleton.getInstanță().getConturi());
        serviciu.seteconomii(EconomiiSingleton.getInstanță().getEconomii());
        serviciu.setdepozite(DepozitSingleton.getInstanță().getDepozite());
        serviciu.setcarduri(CardSingleton.getInstanță().getCarduri());
        serviciu.setcarduriMaster(MasterCardSingleton.getInstanță().getCarduri());
        serviciu.setcarduriVisa(VisaSingleton.getInstanță().getCarduri());
        serviciu.settranzacții(TranzacțieSingleton.getInstanță().getTranzacții());

        int opt = 0;
        while(opt != 19) {
            String nume_comandă = "";

            System.out.println("\nListă opțiuni:\n");
            System.out.println("1. Afișare detalii bancă");
            System.out.println("2. Adăugare client nou");
            System.out.println("3. Afișare date client");
            System.out.println("4. Actualizare date client");
            System.out.println("5. Actualizare adresă client");
            System.out.println("6. Adăugare cont pentru un client");
            System.out.println("7. Afișare date cont client");
            System.out.println("8. Atașare card la cont");
            System.out.println("9. Afișare date carduri atașate unui cont");
            System.out.println("10. Actualizare PIN card client");
            System.out.println("11. Adăugare numerar");
            System.out.println("12. Retragere numerar");
            System.out.println("13. Efectuare tranzacție");
            System.out.println("14. Afișare tranzacții sortate descrescător(după sumă)");
            System.out.println("15. Afișare extras de cont pentru un client");
            System.out.println("16. Ștergere client");
            System.out.println("17. Închidere și ștergere cont");
            System.out.println("18. Eliminare card atașat unui cont");
            System.out.println("19. EXIT\n");

            System.out.print("\nOpțiune: ");
            opt = Integer.parseInt(input.nextLine());
            System.out.println();

            switch(opt){
                case 1 -> {nume_comandă = "Afișare detalii bancă"; serviciu.afișaredetaliibancă();}
                case 2 -> {nume_comandă = "Adăugare client nou"; serviciu.adăugareclient();}
                case 3 -> {nume_comandă = "Afișare date client"; serviciu.afișaredateclient();}
                case 4 -> {nume_comandă = "Actualizare date client"; serviciu.actualizaredateclient();}
                case 5 -> {nume_comandă = "Actualizare adresă client"; serviciu.actualizareadresăclient();}
                case 6 -> {nume_comandă = "Adăugare cont pentru un client"; serviciu.adăugarecont();}
                case 7 -> {nume_comandă = "Afișare date cont client"; serviciu.afișaredetaliicont();}
                case 8 -> {nume_comandă = "Atașare card la cont"; serviciu.atașarecard();}
                case 9 -> {nume_comandă = "Afișare date carduri atașate unui cont"; serviciu.afișarecarduri();}
                case 10 -> {nume_comandă = "Actualizare PIN card client"; serviciu.actualizarepincard();}
                case 11 -> {nume_comandă = "Adăugare numerar"; serviciu.adăugarenumerar();}
                case 12 -> {nume_comandă = "Retragere numerar"; serviciu.retragerenumerar();}
                case 13 -> {nume_comandă = "Efectuare tranzacție"; serviciu.efectuaretranzacție();}
                case 14 -> {nume_comandă = "Afișare tranzacții sortate descrescător(după sumă)"; serviciu.afișaretranzacțiidesc();}
                case 15 -> {nume_comandă = "Afișare extras de cont pentru un client"; serviciu.extrasdecont();}
                case 16 -> {nume_comandă = "Ștergere client"; serviciu.ștergereclient();}
                case 17 -> {nume_comandă = "Închidere și ștergere cont"; serviciu.ștergerecont();}
                case 18 -> {nume_comandă = "Eliminare card atașat unui cont"; serviciu.ștergerecard();}
                case 19 -> System.out.print("Închidere program...\n");
                default -> System.out.print("\n!Comandă greșită!\n");
            }
            if (!nume_comandă.isEmpty()){
                audit.log(nume_comandă);
            }
            if (opt != 19) {
                input.nextLine();
            }
        }

        ClientSingleton.getInstanță().setClienți(serviciu.getclienți());
        TranzacțieSingleton.getInstanță().setTranzacții(serviciu.gettranzacții());
        CardSingleton.getInstanță().setCarduri(serviciu.getcarduri());
        MasterCardSingleton.getInstanță().setCarduri(serviciu.getcarduriMaster());
        VisaSingleton.getInstanță().setCarduri(serviciu.getcarduriVisa());
        ContSingleton.getInstanță().setConturi(serviciu.getconturi());
        EconomiiSingleton.getInstanță().setEconomii(serviciu.geteconomii());
        DepozitSingleton.getInstanță().setDepozite(serviciu.getdepozite());

        ClientSingleton.getInstanță().scriefișier();
        TranzacțieSingleton.getInstanță().scriefișier();
        CardSingleton.getInstanță().scriefișier();
        MasterCardSingleton.getInstanță().scriefișier();
        VisaSingleton.getInstanță().scriefișier();
        ContSingleton.getInstanță().scriefișier();
        EconomiiSingleton.getInstanță().scriefișier();
        DepozitSingleton.getInstanță().scriefișier();
    }
    */


    // Etapa 3 - persistență folosind JDBC cu o bază de date în MySQL
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        ServiciuBD serviciuBD = new ServiciuBD();
        Audit audit = new Audit();

        serviciuBD.setclienți(serviciuBD.obțineclienți());
        serviciuBD.setconturi(serviciuBD.obțineconturi());
        serviciuBD.setcarduri(serviciuBD.obținecarduri());
        serviciuBD.settranzacții(serviciuBD.obținetranzacții());

        int opt = 0;
        while(opt != 19) {
            String nume_comandă = "";

            System.out.println("\nListă opțiuni:\n");
            System.out.println("1. Afișare detalii bancă");
            System.out.println("2. Adăugare client nou");
            System.out.println("3. Afișare date client");
            System.out.println("4. Actualizare date client");
            System.out.println("5. Actualizare adresă client");
            System.out.println("6. Adăugare cont pentru un client");
            System.out.println("7. Afișare date cont client");
            System.out.println("8. Atașare card la cont");
            System.out.println("9. Afișare date carduri atașate unui cont");
            System.out.println("10. Actualizare PIN card client");
            System.out.println("11. Adăugare numerar");
            System.out.println("12. Retragere numerar");
            System.out.println("13. Efectuare tranzacție");
            System.out.println("14. Afișare tranzacții sortate descrescător(după sumă)");
            System.out.println("15. Afișare extras de cont pentru un client");
            System.out.println("16. Ștergere client");
            System.out.println("17. Închidere și ștergere cont");
            System.out.println("18. Eliminare card atașat unui cont");
            System.out.println("19. EXIT\n");

            System.out.print("\nOpțiune: ");
            opt = Integer.parseInt(input.nextLine());
            System.out.println();

            switch(opt){
                case 1 -> {nume_comandă = "Afișare detalii bancă"; serviciuBD.afișaredetaliibancă();}
                case 2 -> {nume_comandă = "Adăugare client nou"; serviciuBD.adăugareclient();}
                case 3 -> {nume_comandă = "Afișare date client"; serviciuBD.afișaredateclient();}
                case 4 -> {nume_comandă = "Actualizare date client"; serviciuBD.actualizaredateclient();}
                case 5 -> {nume_comandă = "Actualizare adresă client"; serviciuBD.actualizareadresăclient();}
                case 6 -> {nume_comandă = "Adăugare cont pentru un client"; serviciuBD.adăugarecont();}
                case 7 -> {nume_comandă = "Afișare date cont client"; serviciuBD.afișaredetaliicont();}
                case 8 -> {nume_comandă = "Atașare card la cont"; serviciuBD.atașarecard();}
                case 9 -> {nume_comandă = "Afișare date carduri atașate unui cont"; serviciuBD.afișarecarduri();}
                case 10 -> {nume_comandă = "Actualizare PIN card client"; serviciuBD.actualizarepincard();}
                case 11 -> {nume_comandă = "Adăugare numerar"; serviciuBD.adăugarenumerar();}
                case 12 -> {nume_comandă = "Retragere numerar"; serviciuBD.retragerenumerar();}
                case 13 -> {nume_comandă = "Efectuare tranzacție"; serviciuBD.efectuaretranzacție();}
                case 14 -> {nume_comandă = "Afișare tranzacții sortate descrescător(după sumă)"; serviciuBD.afișaretranzacțiidesc();}
                case 15 -> {nume_comandă = "Afișare extras de cont pentru un client"; serviciuBD.extrasdecont();}
                case 16 -> {nume_comandă = "Ștergere client"; serviciuBD.ștergereclient();}
                case 17 -> {nume_comandă = "Închidere și ștergere cont"; serviciuBD.ștergerecont();}
                case 18 -> {nume_comandă = "Eliminare card atașat unui cont"; serviciuBD.ștergerecard();}
                case 19 -> System.out.print("Închidere program...\n");
                default -> System.out.print("\n!Comandă greșită!\n");
            }
            if (!nume_comandă.isEmpty()){
                audit.log(nume_comandă);
            }
            if (opt != 19)
                input.nextLine();
        }
    }

}
