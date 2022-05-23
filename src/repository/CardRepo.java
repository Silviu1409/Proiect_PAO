package repository;

import configbd.*;
import card.*;

import java.sql.*;
import java.util.*;

public class CardRepo {

    public void adaugăCard(Card card){
        String query = "insert into carduri (număr, nume, CVV, dată_expirare, PIN, IBAN) values (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)) {
            statement.setString(1, card.getNumăr());
            statement.setString(2, card.getNume());
            statement.setString(3, card.getCVV());
            statement.setString(4, card.getDată_expirare());
            statement.setString(5, card.getPIN());
            statement.setString(6, card.getIBAN());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void adaugăMasterCard(MasterCard card){
        String query = "insert into carduri (număr, nume, CVV, dată_expirare, PIN, IBAN, nivel) values (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)) {
            statement.setString(1, card.getNumăr());
            statement.setString(2, card.getNume());
            statement.setString(3, card.getCVV());
            statement.setString(4, card.getDată_expirare());
            statement.setString(5, card.getPIN());
            statement.setString(6, card.getIBAN());
            statement.setString(7, card.getNivel());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void adaugăVisa(Visa card){
        String query = "insert into carduri (număr, nume, CVV, dată_expirare, PIN, IBAN, asigurarecălătorie) values (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)) {
            statement.setString(1, card.getNumăr());
            statement.setString(2, card.getNume());
            statement.setString(3, card.getCVV());
            statement.setString(4, card.getDată_expirare());
            statement.setString(5, card.getPIN());
            statement.setString(6, card.getIBAN());
            statement.setBoolean(7, card.getAsigurarecălătorie());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Card> citește(){
        List<Card> carduri = new ArrayList<>();
        String query = "select * from carduri";
        try(Statement statement = ConexiuneBD.getInstance().createStatement()){
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                var val1 = res.getString("nivel");
                var val2 = res.getString("asigurarecălătorie");
                if(val1 == null && val2 == null){
                    Card card = new Card(res);
                    carduri.add(card);
                } else if (val1 != null && val2 == null) {
                    MasterCard card = new MasterCard(res);
                    carduri.add(card);
                } else {
                    Visa card = new Visa(res);
                    carduri.add(card);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return carduri;
    }

    public void actualizeazăPinCard(Card card){
        String query = "update carduri set PIN = ? where număr = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, card.getPIN());
            statement.setString(2, card.getNumăr());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizeazăMasterCard(MasterCard card){
        String query = "update carduri set CVV = ? where număr = ? and nume = ? and dată_expirare = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, card.getCVV());
            statement.setString(2, card.getNumăr());
            statement.setString(3, card.getNumăr());
            statement.setString(4, card.getDată_expirare());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizeazăVisa(Visa card){
        String query = "update carduri set CVV = ? where număr = ? and nume = ? and dată_expirare = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, card.getCVV());
            statement.setString(2, card.getNumăr());
            statement.setString(3, card.getNumăr());
            statement.setString(4, card.getDată_expirare());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void șterge(String număr){
        String query = "delete from carduri where număr = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, număr);
            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
