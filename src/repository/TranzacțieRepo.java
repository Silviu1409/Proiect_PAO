package repository;

import configbd.*;
import tranzacție.*;

import java.sql.*;
import java.util.*;

public class TranzacțieRepo {
    public void adaugă(Tranzacție tranzacție){
        String query = "insert into tranzacții values (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)) {
            statement.setString(1, tranzacție.getTip());
            statement.setString(2, tranzacție.getCont());
            statement.setString(3, tranzacție.getCont_beneficiar());
            statement.setString(4, tranzacție.getNume_beneficiar());
            statement.setString(5, tranzacție.getDetalii());
            statement.setDouble(6, tranzacție.getSumă());
            statement.setString(7, tranzacție.getDată());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tranzacție> citește(){
        List<Tranzacție> tranzacții = new ArrayList<>();
        String query = "select * from tranzacții";
        try(Statement statement = ConexiuneBD.getInstance().createStatement()){
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                Tranzacție tranzacție = new Tranzacție(res);
                tranzacții.add(tranzacție);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return tranzacții;
    }

    public List<Tranzacție> citeșteTranzacțiiSortate() {
        List<Tranzacție> tranzacții = new ArrayList<>();
        String query = "select * from tranzacții order by sumă desc";
        try(Statement statement = ConexiuneBD.getInstance().createStatement()){
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                Tranzacție tranzacție = new Tranzacție(res);
                tranzacții.add(tranzacție);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return tranzacții;
    }

    public void actualizează(Tranzacție tranzacție){
        String query = "update tranzacții set tip = ?, detalii = ?, sumă = ? where cont_sursă = ? and cont_beneficiar = ? and dată = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, tranzacție.getTip());
            statement.setString(2, tranzacție.getDetalii());
            statement.setDouble(3, tranzacție.getSumă());
            statement.setString(4, tranzacție.getCont());
            statement.setString(5, tranzacție.getCont_beneficiar());
            statement.setString(6, tranzacție.getDată());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void șterge(Tranzacție tranzacție){
        String query = "delete from tranzacții where cont_sursă = ? and cont_beneficiar = ? and dată = ?";
        try(PreparedStatement statement = ConexiuneBD.getInstance().prepareStatement(query)){
            statement.setString(1, tranzacție.getCont());
            statement.setString(2, tranzacție.getCont_beneficiar());
            statement.setString(3, tranzacție.getDată());
            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
