package repository;

import adresă.Adresă;
import configbd.*;
import client.*;

import java.sql.*;
import java.util.*;

public class ClientRepo {
    public void adaugă(Client client, int idadresă){
        String query = "insert into clienți values (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)) {
            statement.setString(1, client.getCNP());
            statement.setString(2, client.getNume());
            statement.setString(3, client.getPrenume());
            statement.setString(4, client.getEmail());
            statement.setString(5, client.getNr_tel());
            statement.setInt(6, idadresă);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Client> citește(){
        Set<Client> clienți = new HashSet<>(Collections.emptySet());
        String query = "select * from clienți";
        try(Statement statement = ConexiuneBD.getInstance().createStatement()){
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                Client client = new Client(res);
                clienți.add(client);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return clienți;
    }

    public Client citeșteclient(String CNP){
        Client client;
        String query = "select * from clienți where CNP = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, CNP);
            ResultSet res = statement.executeQuery();
            res.next();
            client = new Client(res);
            int id_adresă = res.getInt("id_adresă");
            AdresăRepo adresăRepo = new AdresăRepo();
            Adresă adresă = adresăRepo.citeșteadresă(id_adresă);
            client.setAdresă(adresă);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    public void actualizează(Client client){
        String query = "update clienți set nume = ?, prenume = ?, email = ?, nr_tel = ? where CNP = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, client.getNume());
            statement.setString(2, client.getPrenume());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getNr_tel());
            statement.setString(5, client.getCNP());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void șterge(String CNP){
        String query = "delete from clienți where CNP = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, CNP);
            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int obțineid_adresă(String CNP){
        String query = "select * from clienți where CNP = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, CNP);
            ResultSet res = statement.executeQuery();
            res.next();
            return res.getInt("id_adresă");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
