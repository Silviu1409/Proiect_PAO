package mainservice;

import adresă.Adresă;
import bancă.*;
import card.*;
import client.*;
import cont.*;
import repository.*;
import tranzacție.Tranzacție;

import java.util.*;

public class ServiciuBD {

    private final Bancă bancă;
    private final Scanner input;
    private final ClientRepo clientRepo;
    private final ContRepo contRepo;
    private final CardRepo cardRepo;
    private final TranzacțieRepo tranzacțieRepo;
    private final AdresăRepo adresăRepo;

    public ServiciuBD() {
        this.bancă = new Bancă();
        this.input = new Scanner(System.in);
        this.clientRepo = new ClientRepo();
        this.contRepo = new ContRepo();
        this.cardRepo = new CardRepo();
        this.tranzacțieRepo = new TranzacțieRepo();
        this.adresăRepo = new AdresăRepo();
    }

    public Set<Client> obțineclienți() {
        Set<Client> clienți = clientRepo.citește();
        for(Client client: clienți){
            int id_adresă = clientRepo.obțineid_adresă(client.getCNP());
            Adresă adresă = adresăRepo.citeșteadresă(id_adresă);
            client.setAdresă(adresă);
        }
        return clienți;
    }

    public List<Cont> obțineconturi() {
        return contRepo.citește();
    }

    public List<Card> obținecarduri() {
        return cardRepo.citește();
    }

    public List<Tranzacție> obținetranzacții() {
        return tranzacțieRepo.citește();
    }

    public void setclienți(Set<Client> clienți){
        bancă.setClienți(clienți);
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
        adresăRepo.adaugă(adresă);
        int id_adresă = adresăRepo.obțineid(adresă);

        Client client = new Client(nume, prenume, CNP, email, nr_tel, adresă);
        this.bancă.adaugăclient(client);
        clientRepo.adaugă(client, id_adresă);

        System.out.println("Clientul a fost adăugat!");
    }

    public void afișaredateclient(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        System.out.println();
        Client client = clientRepo.citeșteclient(CNP);
        if(client != null) {
            System.out.println(client);
        } else {
            System.out.println("Clientul nu există");
        }
    }

    public void actualizaredateclient(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        bancă.actualizaredateclientbd(CNP);
    }

    public void actualizareadresăclient(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        bancă.actualizareadresăclientbd(CNP);
    }

    public void adăugarecont(){
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        Client client = bancă.cautăclient(CNP);
        if(client == null){
            System.out.println("Clientul nu există, nu se poate adăuga un cont!");
        }else{
            String CNP_client = client.getCNP();
            System.out.print("Ce tip de cont vrei să adaugi?\n1 - Cont curent\n2 - Cont de economii\n3 - Cont de depozit.\n");
            int opt = Integer.parseInt(input.nextLine());
            switch (opt) {
                case 1 -> {
                    Cont cont = new Cont(CNP_client);
                    client.adaugăcont(cont);
                    contRepo.adaugăContCurent(cont);
                    System.out.println("\nContul curent a fost adăugat!");
                    System.out.println("\nCodul IBAN al contului: " + cont.getIBAN());
                }
                case 2 -> {
                    Economii cont = new Economii(CNP_client);
                    client.adaugăcont(cont);
                    contRepo.adaugăContEconomii(cont);
                    System.out.println("\nContul de economii a fost adăugat!");
                    System.out.println("\nCodul IBAN al contului: " + cont.getIBAN());
                }
                case 3 -> {
                    Depozit cont = new Depozit(CNP_client);
                    client.adaugăcont(cont);
                    contRepo.adaugăContDepozit(cont);
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
            Cont cont = contRepo.citeștecont(IBAN);
            if(cont != null) {
                if(cont.getClass() == Cont.class){
                    System.out.println(cont);
                } else if (cont.getClass() == Economii.class) {
                    Economii cont_aux = (Economii) cont;
                    System.out.println(cont_aux);
                } else {
                    Depozit cont_aux = (Depozit) cont;
                    System.out.println(cont_aux);
                }
            } else {
                System.out.println("Contul nu există");
            }
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
            System.out.println();
            client.atașeazăcardbd(IBAN);
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
            client.actualizarepincardbd(IBAN);
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
            client.depunerenumerarbd(IBAN);
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
            client.retragerenumerarbd(IBAN);
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
            Tranzacție tr = client.adăugaretranzacțiebd(IBAN);
            if(tr != null) {
                String nume = tr.getNume_beneficiar().split(" ")[0];
                String prenume = tr.getNume_beneficiar().split(" ")[1];
                client = bancă.cautăclient(nume, prenume);
                IBAN = tr.getCont_beneficiar();
                client.adaugănumerarbd(IBAN, tr.getSumă());
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
            System.out.println();
            client.afișaretranzacțiidescbd(IBAN);
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
        System.out.print("CNP client: ");
        String CNP = input.nextLine();
        bancă.ștergeclientbd(CNP);
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
            client.ștergecontbd(IBAN);
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
            client.ștergecardbd(IBAN);
        }
    }
}
