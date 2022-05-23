package bancă;

import client.*;
import adresă.*;
import repository.*;

import java.util.*;

public class Bancă {

    protected Set<Client> clienți;
    private Adresă adresă;
    private final ClientRepo clientRepo = new ClientRepo();
    private final AdresăRepo adresăRepo = new AdresăRepo();

    public Bancă() {
        clienți = new HashSet<>(Collections.emptySet());
        this.adresă = new Adresă("Șoseaua Olteniței", 50, "București", "041317");
    }

    public Bancă(Set<Client> clienti) {
        clienți = clienti;
        this.adresă = new Adresă("Șoseaua Olteniței", 50, "București", "041317");
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder("Detalii Bancă:\n\n");
        if (clienți.size() == 0){
            output.append("Banca nu are niciun client.\n");
        }else{
            output.append("Detalii despre clienții băncii:\n\n");
            for(Client client : clienți){
                output.append(client.toString()).append("\n\n");
            }
        }
        if (this.adresă.getStradă() == null){
            output.append("Momentan banca nu are o adresă.\n");
        }else{
            output.append("Adresă bancă: \n").append(this.adresă.toString()).append("\n");
        }
        return output.toString();
    }

    public void adaugăclient(Client client){
        clienți.add(client);
    }

    public void ștergeclient(String CNP){
        for(Client client: clienți){
            if(CNP.equals(client.getCNP())){
                clienți.remove(client);
                System.out.println("Clientul a fost șters!");
                return;
            }
        }
        System.out.println("Clientul nu există!");
    }

    public void ștergeclientbd(String CNP){
        for(Client client: clienți){
            if(CNP.equals(client.getCNP())){
                clienți.remove(client);
                clientRepo.șterge(CNP);
                System.out.println("Clientul a fost șters!");
                return;
            }
        }
        System.out.println("Clientul nu există!");
    }

    public String afișeazădateclient(String CNP){
        for(Client client: clienți){
            if(CNP.equals(client.getCNP())){
                return client.toString();
            }
        }
        return null;
    }

    public void actualizaredateclient(String CNP){
        for (Client client : clienți) {
            if (CNP.equals(client.getCNP())) {
                System.out.println("Detalii actuale ale clientului: \n");
                System.out.println(client);
                Scanner input = new Scanner(System.in);
                System.out.println("Actualizează datele clientului: \n");
                System.out.print("Nume: ");
                client.setNume(input.nextLine());
                System.out.print("Prenume: ");
                client.setPrenume(input.nextLine());
                System.out.print("Email: ");
                client.setEmail(input.nextLine());
                System.out.print("Număr de telefon: ");
                client.setNr_tel(input.nextLine());
                System.out.println("\nDatele clientului au fost actualizate cu succes!\n");
                return;
            }
        }
        System.out.println("\nClientul nu există!");
    }

    public void actualizaredateclientbd(String CNP){
        for (Client client : clienți) {
            if (CNP.equals(client.getCNP())) {
                System.out.println("Detalii actuale ale clientului: \n");
                System.out.println(client);
                Scanner input = new Scanner(System.in);
                System.out.println("Actualizează datele clientului: \n");
                System.out.print("Nume: ");
                client.setNume(input.nextLine());
                System.out.print("Prenume: ");
                client.setPrenume(input.nextLine());
                System.out.print("Email: ");
                client.setEmail(input.nextLine());
                System.out.print("Număr de telefon: ");
                client.setNr_tel(input.nextLine());
                clientRepo.actualizează(client);
                System.out.println("\nDatele clientului au fost actualizate cu succes!\n");
                return;
            }
        }
        System.out.println("\nClientul nu există!");
    }

    public void actualizareadresăclient(String CNP){
        for (Client client : clienți) {
            if (CNP.equals(client.getCNP())) {
                System.out.println("Detalii actuale ale adresei clientului: \n");
                System.out.println(client.getAdresă());
                Scanner input = new Scanner(System.in);
                System.out.println("Actualizează adresa clientului: \n");
                System.out.print("Strada: ");
                client.getAdresă().setStradă(input.nextLine());
                System.out.print("Număr: ");
                client.getAdresă().setNumăr(Integer.parseInt(input.nextLine()));
                System.out.print("Oraș: ");
                client.getAdresă().setOraș(input.nextLine());
                System.out.print("Cod poștal: ");
                client.getAdresă().setCod_poștal(input.nextLine());
                System.out.println("\nAdresa clientului a fost actualizată cu succes!\n");
                return;
            }
        }
        System.out.println("\nClientul nu există!");
    }

    public void actualizareadresăclientbd(String CNP){
        for (Client client : clienți) {
            if (CNP.equals(client.getCNP())) {
                System.out.println("Detalii actuale ale adresei clientului: \n");
                System.out.println(client.getAdresă());
                Scanner input = new Scanner(System.in);
                System.out.println("Actualizează adresa clientului: \n");
                System.out.print("Strada: ");
                client.getAdresă().setStradă(input.nextLine());
                System.out.print("Număr: ");
                client.getAdresă().setNumăr(Integer.parseInt(input.nextLine()));
                System.out.print("Oraș: ");
                client.getAdresă().setOraș(input.nextLine());
                System.out.print("Cod poștal: ");
                client.getAdresă().setCod_poștal(input.nextLine());

                int id_adresă = clientRepo.obțineid_adresă(CNP);
                adresăRepo.actualizează(id_adresă, client.getAdresă());
                System.out.println("\nAdresa clientului a fost actualizată cu succes!\n");
                return;
            }
        }
        System.out.println("\nClientul nu există!");
    }

    public Client cautăclient(String CNP){
        for(Client client: clienți){
            if(CNP.equals(client.getCNP())){
                return client;
            }
        }
        return null;
    }

    public Client cautăclient(String nume, String prenume){
        for(Client client: clienți){
            if(nume.equals(client.getNume()) && prenume.equals(client.getPrenume())){
                return client;
            }
        }
        return null;
    }

    public Set<Client> getClienți() {
        return clienți;
    }

    public void setClienți(Set<Client> clienți) {
        this.clienți = clienți;
    }

    public Adresă getAdresă() {
        return adresă;
    }

    public void setAdresă(Adresă adresă) {
        this.adresă = adresă;
    }
}
