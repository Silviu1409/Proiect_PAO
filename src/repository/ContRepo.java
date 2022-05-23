package repository;

import configbd.*;
import cont.*;

import java.sql.*;
import java.util.*;

public class ContRepo {
    public void adaugăContCurent(Cont cont){
        String query = "insert into conturi (IBAN, CNP_client, BIC, balanță) values (?, ?, ?, ?)";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)) {
            statement.setString(1, cont.getIBAN());
            statement.setString(2, cont.getCNP_client());
            statement.setString(3, cont.getBIC());
            statement.setDouble(4, cont.getBalanță());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void adaugăContEconomii(Economii cont){
        String query = "insert into conturi (IBAN, CNP_client, BIC, balanță, dobândă) values (?, ?, ?, ?, ?)";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)) {
            statement.setString(1, cont.getIBAN());
            statement.setString(2, cont.getCNP_client());
            statement.setString(3, cont.getBIC());
            statement.setDouble(4, cont.getBalanță());
            statement.setDouble(5, cont.getDobândă());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void adaugăContDepozit(Depozit cont){
        String query = "insert into conturi values (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)) {
            statement.setString(1, cont.getIBAN());
            statement.setString(2, cont.getCNP_client());
            statement.setString(3, cont.getBIC());
            statement.setDouble(4, cont.getBalanță());
            statement.setDouble(5, cont.getDobândă());
            statement.setString(6, cont.getDată_început());
            statement.setString(7, cont.getDată_final());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cont> citește(){
        List<Cont> conturi = new ArrayList<>();
        String query = "select * from conturi";
        try(Statement statement = ConexiuneBD.getInstance().createStatement()){
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                var val1 = res.getDouble("dobândă");
                if(res.wasNull()) {
                    Cont cont = new Cont(res);
                    conturi.add(cont);
                } else {
                    var val2 = res.getString("dată_început");
                    if(res.wasNull()){
                        Economii cont = new Economii(res);
                        conturi.add(cont);
                    } else {
                        Depozit cont = new Depozit(res);
                        conturi.add(cont);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conturi;
    }

    public Cont citeștecont(String IBAN){
        Cont cont;
        String query = "select * from conturi where IBAN = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, IBAN);
            ResultSet res = statement.executeQuery();
            res.next();
            var val1 = res.getDouble("dobândă");
            if(res.wasNull()) {
                cont = new Cont(res);
            } else {
                var val2 = res.getString("dată_început");
                if(res.wasNull()){
                    cont = new Economii(res);
                } else {
                    cont = new Depozit(res);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cont;
    }

    public void actualizeazăBalanțăCont(Cont cont){
        String query = "update conturi set balanță = ? where IBAN = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setDouble(1, cont.getBalanță());
            statement.setString(2, cont.getIBAN());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizeazăEconomii(Economii cont){
        String query = "update conturi set balanță = ?, dobândă = ? where IBAN = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setDouble(1, cont.getBalanță());
            statement.setDouble(2, cont.getDobândă());
            statement.setString(3, cont.getIBAN());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizeazăDepozit(Depozit cont){
        String query = "update conturi set balanță = ?, dobândă = ?, dată_început = ?, dată_final = ? where IBAN = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setDouble(1, cont.getBalanță());
            statement.setDouble(2, cont.getDobândă());
            statement.setString(3, cont.getDată_început());
            statement.setString(4, cont.getDată_final());
            statement.setString(5, cont.getIBAN());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void șterge(String IBAN){
        String query = "delete from conturi where IBAN = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, IBAN);
            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
