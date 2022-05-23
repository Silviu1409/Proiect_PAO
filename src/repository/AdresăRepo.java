package repository;

import adresă.*;
import configbd.*;

import java.sql.*;
import java.util.*;

public class AdresăRepo {

    public void adaugă(Adresă adresă){
        String query = "insert into adrese values (null, ?, ?, ?, ?)";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)) {
            statement.setString(1, adresă.getStradă());
            statement.setInt(2, adresă.getNumăr());
            statement.setString(3, adresă.getOraș());
            statement.setString(4, adresă.getCod_poștal());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Adresă> citește(){
        List<Adresă> adrese = new ArrayList<>();
        String query = "select * from adrese";
        try(Statement statement = ConexiuneBD.getInstance().createStatement()){
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                Adresă adresă = new Adresă(res);
                adrese.add(adresă);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return adrese;
    }

    public Adresă citeșteadresă(int id_adresă){
        Adresă adresă;
        String query = "select * from adrese where id = " + id_adresă;
        try(Statement statement = ConexiuneBD.getInstance().createStatement()){
            ResultSet res = statement.executeQuery(query);
            res.next();
            adresă = new Adresă(res);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return adresă;
    }

    public void actualizează(int id_adresă, Adresă adresă){
        String query = "update adrese set stradă = ?, număr = ?, oraș = ?, cod_poștal = ? where id = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, adresă.getStradă());
            statement.setInt(2, adresă.getNumăr());
            statement.setString(3, adresă.getOraș());
            statement.setString(4, adresă.getCod_poștal());
            statement.setInt(5, id_adresă);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void șterge(Adresă adresă){
        String query = "delete from adrese where stradă = ? and număr = ? and oraș = ? and cod_poștal = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, adresă.getStradă());
            statement.setInt(2, adresă.getNumăr());
            statement.setString(3, adresă.getOraș());
            statement.setString(4, adresă.getCod_poștal());
            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int obțineid(Adresă adresă){
        String query = "select * from adrese where stradă = ? and număr = ? and oraș = ? and cod_poștal = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, adresă.getStradă());
            statement.setInt(2, adresă.getNumăr());
            statement.setString(3, adresă.getOraș());
            statement.setString(4, adresă.getCod_poștal());
            ResultSet res = statement.executeQuery();
            res.next();
            return res.getInt("id");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
