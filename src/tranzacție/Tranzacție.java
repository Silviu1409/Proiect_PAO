package tranzacție;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.*;
import java.util.*;

public class Tranzacție {
    private final String tip, cont, cont_beneficiar, nume_beneficiar, detalii, dată;
    private final double sumă;

    public Tranzacție(String tip, String cont, String cont_beneficiar, String nume_beneficiar, String detalii, double sumă) {
        this.tip = tip;
        this.cont = cont;
        this.cont_beneficiar = cont_beneficiar;
        this.nume_beneficiar = nume_beneficiar;
        this.detalii = detalii;
        this.sumă = sumă;
        DateFormat format_dată = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.dată = format_dată.format(new Date());
    }

    public Tranzacție(String tip, String cont, String cont_beneficiar, String nume_beneficiar, String detalii, double sumă, String dată) {
        this.tip = tip;
        this.cont = cont;
        this.cont_beneficiar = cont_beneficiar;
        this.nume_beneficiar = nume_beneficiar;
        this.detalii = detalii;
        this.sumă = sumă;
        this.dată = dată;
    }

    public Tranzacție(ResultSet res) throws SQLException {
        this.tip = res.getString("tip");
        this.cont = res.getString("cont_sursă");
        this.cont_beneficiar = res.getString("cont_beneficiar");
        this.nume_beneficiar = res.getString("nume_beneficiar");
        this.detalii = res.getString("detalii");
        this.sumă = res.getDouble("sumă");
        this.dată = res.getString("dată");
    }

    @Override
    public String toString(){
        String output = "Detalii tranzacție:\n";
        output += "Tip: "+ this.tip +"\n";
        output += "Cont sursă: "+ this.cont +"\n";
        output += "Cont beneficiar: "+ this.cont_beneficiar +"\n";
        output += "Nume beneficiar: "+ this.nume_beneficiar +"\n";
        output += "Detalii: "+ this.detalii +"\n";
        output += "Sumă: "+ this.sumă +"\n";
        output += "Dată: "+ this.dată +"\n";
        return output;
    }

    public String CSV(){
        return tip + "," + cont + "," + cont_beneficiar + "," + nume_beneficiar + "," + detalii + "," + sumă + "," + dată;
    }

    public String getTip() {
        return tip;
    }

    public String getCont() {
        return cont;
    }

    public String getCont_beneficiar() {
        return cont_beneficiar;
    }

    public String getNume_beneficiar() {
        return nume_beneficiar;
    }

    public String getDetalii() {
        return detalii;
    }

    public double getSumă() {
        return sumă;
    }

    public String getDată() {
        return dată;
    }
}
