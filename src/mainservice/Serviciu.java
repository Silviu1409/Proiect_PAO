package mainservice;

import bancă.*;
import card.*;
import client.*;
import cont.*;
import adresă.*;
import tranzacție.*;

import java.util.*;

public class Serviciu {
    private final Bancă bancă;
    private final Scanner input;

    public Serviciu() {
        this.bancă = new Bancă();
        this.input = new Scanner(System.in);
    }

    public void afișaredetaliibancă(){
        System.out.print(bancă);
    }

    public void adăugareclient(){
        System.out.println("Introdu detaliile clientului.");
        System.out.print("Nume: ");
        String nume = input.nextLine();
        System.out.print("Prenume: ");
        String prenume = input.nextLine();
        System.out.print("CNP: ");
        String CNP = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Număr de telefon: ");
        String nr_tel = input.nextLine();

        System.out.println("Introdu detaliile adresei clientului.");
        System.out.print("Strada: ");
        String stradă = input.nextLine();
        System.out.print("Număr: ");
        int număr = Integer.parseInt(input.nextLine());
        System.out.print("Oraș: ");
        String oraș = input.nextLine();
        System.out.print("Cod poștal: ");
        String cod_poștal = input.nextLine();

        Adresă adresă = new Adresă(stradă, număr, oraș, cod_poștal);
        Client client = new Client(nume, prenume, CNP, email, nr_tel, adresă);
        this.bancă.adaugăclient(client);

        System.out.println("Clientul a fost adăugat!");
    }

    public void afișaredateclient(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        String date = bancă.afișeazădateclient(CNP);
        if(date == null){
            System.out.println("\nClientul nu există!");
        }else{
            System.out.println();
            System.out.print(date);
        }
    }

    public void actualizaredateclient(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        bancă.actualizaredateclient(CNP);
    }

    public void actualizareadresăclient(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        bancă.actualizareadresăclient(CNP);
    }

    public void adăugarecont(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        Client client = bancă.cautăclient(CNP);
        if(client == null){
            System.out.println("Clientul nu există, nu se poate adăuga un cont!");
        }else{

            System.out.print("Ce tip de cont vrei să adaugi?\n1 - Cont curent\n2 - Cont de economii\n3 - Cont de depozit.\n");
            int opt = Integer.parseInt(input.nextLine());
            switch (opt) {
                case 1 -> {
                    Cont cont = new Cont(client.getCNP());
                    client.adaugăcont(cont);
                    System.out.println("\nContul curent a fost adăugat!");
                    System.out.println("\nCodul IBAN al contului: " + cont.getIBAN());
                }
                case 2 -> {
                    Economii cont = new Economii(client.getCNP());
                    client.adaugăcont(cont);
                    System.out.println("\nContul de economii a fost adăugat!");
                    System.out.println("\nCodul IBAN al contului: " + cont.getIBAN());
                }
                case 3 -> {
                    Depozit cont = new Depozit(client.getCNP());
                    client.adaugăcont(cont);
                    System.out.println("\nContul de depozit a fost adăugat!");
                    System.out.println("\nCodul IBAN al contului: " + cont.getIBAN());
                }
                default -> {
                    System.out.println("\nOpțiune greșită. Contul nu a fost adăugat!");
                    return;
                }
            }
            System.out.println("\nContul a fost creat cu succes!");
        }
    }

    public void afișaredetaliicont(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        Client client = bancă.cautăclient(CNP);
        if(client == null){
            System.out.println("Clientul nu există!");
        }else{
            System.out.print("Codul IBAN al contului: ");
            String IBAN = input.nextLine();
            System.out.println();
            client.afișeazădetaliicont(IBAN);
        }
    }

    public void atașarecard(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        Client client = bancă.cautăclient(CNP);
        if(client == null){
            System.out.println("Clientul nu există!");
        }else{
            System.out.print("Codul IBAN al contului: ");
            String IBAN = input.nextLine();
            client.atașeazăcard(IBAN);
        }
    }

    public void afișarecarduri(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        Client client = bancă.cautăclient(CNP);
        if(client == null){
            System.out.println("Clientul nu există!");
        }else{
            System.out.print("Codul IBAN al contului: ");
            String IBAN = input.nextLine();
            System.out.println();
            client.afișeazăcarduri(IBAN);
        }
    }

    public void actualizarepincard(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        Client client = bancă.cautăclient(CNP);
        if(client == null){
            System.out.println("Clientul nu există!");
        }else{
            System.out.print("Codul IBAN al contului: ");
            String IBAN = input.nextLine();
            client.actualizarepincard(IBAN);
        }
    }

    public void adăugarenumerar(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        Client client = bancă.cautăclient(CNP);
        if(client == null){
            System.out.println("Clientul nu există!");
        }else{
            System.out.print("Codul IBAN al contului: ");
            String IBAN = input.nextLine();
            client.depunerenumerar(IBAN);
        }
    }

    public void retragerenumerar(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        Client client = bancă.cautăclient(CNP);
        if(client == null){
            System.out.println("Clientul nu există!");
        }else{
            System.out.print("Codul IBAN al contului: ");
            String IBAN = input.nextLine();
            client.retragerenumerar(IBAN);
        }
    }

    public void efectuaretranzacție(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        Client client = bancă.cautăclient(CNP);
        if(client == null){
            System.out.println("Clientul nu există!");
        }else{
            System.out.print("Codul IBAN al contului sursă: ");
            String IBAN = input.nextLine();
            Tranzacție tr = client.adăugaretranzacție(IBAN);
            if(tr != null) {
                String nume = tr.getNume_beneficiar().split(" ")[0];
                String prenume = tr.getNume_beneficiar().split(" ")[1];
                client = bancă.cautăclient(nume, prenume);
                IBAN = tr.getCont_beneficiar();
                client.adaugănumerar(IBAN, tr.getSumă());
            }
        }
    }

    public void afișaretranzacțiidesc(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        Client client = bancă.cautăclient(CNP);
        if(client == null){
            System.out.println("Clientul nu există!");
        }else{
            System.out.print("Codul IBAN al contului: ");
            String IBAN = input.nextLine();
            client.afișaretranzacțiidesc(IBAN);
        }
    }

    public void extrasdecont(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        Client client = bancă.cautăclient(CNP);
        if(client == null){
            System.out.println("Clientul nu există!");
        }else{
            System.out.print("Codul IBAN al contului: ");
            String IBAN = input.nextLine();
            client.extrasdecont(IBAN);
        }
    }

    public void ștergereclient(){
        System.out.print("CNP-ul clientului: ");
        String CNP = input.nextLine();
        bancă.ștergeclient(CNP);
    }

    public void ștergerecont(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        Client client = bancă.cautăclient(CNP);
        if(client == null){
            System.out.println("Clientul nu există!");
        }else{
            System.out.print("Codul IBAN al contului: ");
            String IBAN = input.nextLine();
            client.ștergecont(IBAN);
        }
    }

    public void ștergerecard(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        Client client = bancă.cautăclient(CNP);
        if(client == null){
            System.out.println("Clientul nu există!");
        }else{
            System.out.print("Codul IBAN al contului: ");
            String IBAN = input.nextLine();
            client.ștergecard(IBAN);
        }
    }

    public void setclienți(Set<Client> clienți){
        bancă.setClienți(clienți);
    }

    public void settranzacții(List<Tranzacție> tranzacții){
        for(Tranzacție tranzacție: tranzacții){
            Set<Client> clienți = bancă.getClienți();
            boolean cont_găsit = false;
            for(Client client: clienți){
                for(Cont cont: client.getConturi()){
                    if(Objects.equals(cont.getIBAN(), tranzacție.getCont())){
                        cont_găsit = true;
                        Tranzacție tranzacție_nouă = new Tranzacție(tranzacție.getTip(), tranzacție.getCont(), tranzacție.getCont_beneficiar(), tranzacție.getNume_beneficiar(), tranzacție.getDetalii(), tranzacție.getSumă(), tranzacție.getDată());
                        cont.adaugătranzacție(tranzacție_nouă);
                        break;
                    }
                }
                if(cont_găsit){
                    break;
                }
            }
        }
    }

    public void setcarduri(List<Card> carduri){
        for(Card card: carduri){
            boolean card_găsit = false;
            for(Client client: bancă.getClienți()){
                for(Cont cont: client.getConturi()){
                    if(Objects.equals(cont.getIBAN(), card.getIBAN())){
                        card_găsit = true;
                        List<Card> carduri_cont = cont.getCarduri();
                        carduri_cont.add(card);
                        cont.setCarduri(carduri_cont);
                        break;
                    }
                }
                if(card_găsit){
                    break;
                }
            }
        }
    }

    public void setcarduriMaster(List<MasterCard> carduri){
        for(MasterCard card: carduri){
            boolean card_găsit = false;
            for(Client client: bancă.getClienți()){
                for(Cont cont: client.getConturi()){
                    if(Objects.equals(cont.getIBAN(), card.getIBAN())){
                        card_găsit = true;
                        List<Card> carduri_cont = cont.getCarduri();
                        carduri_cont.add(card);
                        cont.setCarduri(carduri_cont);
                        break;
                    }
                }
                if(card_găsit){
                    break;
                }
            }
        }
    }

    public void setcarduriVisa(List<Visa> carduri){
        for(Visa card: carduri){
            boolean card_găsit = false;
            for(Client client: bancă.getClienți()){
                for(Cont cont: client.getConturi()){
                    if(Objects.equals(cont.getIBAN(), card.getIBAN())){
                        card_găsit = true;
                        List<Card> carduri_cont = cont.getCarduri();
                        carduri_cont.add(card);
                        cont.setCarduri(carduri_cont);
                        break;
                    }
                }
                if(card_găsit){
                    break;
                }
            }
        }
    }

    public void setconturi(List<Cont> conturi){
        for(Cont cont: conturi){
            for(Client client: bancă.getClienți()){
                if(client.getCNP().equals(cont.getCNP_client())) {
                    client.adaugăcont(cont);
                    break;
                }
            }
        }
    }

    public void seteconomii(List<Economii> economii){
        for(Economii cont: economii){
            for(Client client: bancă.getClienți()){
                if(client.getCNP().equals(cont.getCNP_client())) {
                    client.adaugăcont(cont);
                    break;
                }
            }
        }
    }

    public void setdepozite(List<Depozit> depozite){
        for(Depozit cont: depozite){
            for(Client client: bancă.getClienți()){
                if(client.getCNP().equals(cont.getCNP_client())) {
                    client.adaugăcont(cont);
                    break;
                }
            }
        }
    }

    public Set<Client> getclienți(){
        return bancă.getClienți();
    }

    public List<Tranzacție> gettranzacții(){
        List<Tranzacție> tranzacții = new ArrayList<>();
        for(Client client: bancă.getClienți()){
            for(Cont cont: client.getConturi()){
                tranzacții.addAll(cont.getTranzacții());
            }
        }
        return tranzacții;
    }

    public List<Card> getcarduri(){
        List<Card> carduri = new ArrayList<>();
        for(Client client: bancă.getClienți()){
            for(Cont cont: client.getConturi()){
                for(Card card: cont.getCarduri()){
                    if(card.getClass().getName().equals(Card.class.getName())){
                        carduri.add(card);
                    }
                }
            }
        }
        return carduri;
    }

    public List<MasterCard> getcarduriMaster(){
        List<MasterCard> carduri = new ArrayList<>();
        for(Client client: bancă.getClienți()){
            for(Cont cont: client.getConturi()){
                for(Card card: cont.getCarduri()){
                    if(card.getClass().getName().equals(MasterCard.class.getName())){
                        carduri.add((MasterCard) card);
                    }
                }
            }
        }
        return carduri;
    }

    public List<Visa> getcarduriVisa(){
        List<Visa> carduri = new ArrayList<>();
        for(Client client: bancă.getClienți()){
            for(Cont cont: client.getConturi()){
                for(Card card: cont.getCarduri()){
                    if(card.getClass().getName().equals(Visa.class.getName())){
                        carduri.add((Visa) card);
                    }
                }
            }
        }
        return carduri;
    }

    public List<Cont> getconturi(){
        List<Cont> conturi = new ArrayList<>();
        for(Client client: bancă.getClienți()){
            for(Cont cont: client.getConturi()){
                if(cont.getClass().getName().equals(Cont.class.getName())){
                    conturi.add(cont);
                }
            }
        }
        return conturi;
    }

    public List<Economii> geteconomii(){
        List<Economii> conturi = new ArrayList<>();
        for(Client client: bancă.getClienți()){
            for(Cont cont: client.getConturi()){
                if(cont.getClass().equals(Economii.class)){
                    conturi.add((Economii) cont);
                }
            }
        }
        return conturi;
    }

    public List<Depozit> getdepozite(){
        List<Depozit> conturi = new ArrayList<>();
        for(Client client: bancă.getClienți()){
            for(Cont cont: client.getConturi()){
                if(cont.getClass().equals(Depozit.class)){
                    conturi.add((Depozit) cont);
                }
            }
        }
        return conturi;
    }
}
