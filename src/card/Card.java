package card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Card {

    protected final String IBAN, număr, CVV, nume, dată_expirare;

    protected String PIN;

    public Card(String nume, String IBAN) {
        this.nume = nume;
        this.IBAN = IBAN;
        this.număr = this.GenereazăNumăr();
        this.CVV = this.GenereazăCVV();
        this.dată_expirare = this.GenereazăDată();
        this.PIN = this.GenereazăPin();
    }

    public Card(String nume, String IBAN, String număr, String CVV, String dată_expirare, String PIN) {
        this.nume = nume;
        this.IBAN = IBAN;
        this.număr = număr;
        this.CVV = CVV;
        this.dată_expirare = dată_expirare;
        this.PIN = PIN;
    }

    public Card(ResultSet res) throws SQLException {
        this.număr = res.getString("număr");
        this.nume = res.getString("nume");
        this.CVV = res.getString("CVV");
        this.dată_expirare = res.getString("dată_expirare");
        this.PIN = res.getString("PIN");
        this.IBAN = res.getString("IBAN");
    }

    @Override
    public String toString(){
        String output = "Detalii card:\n";
        output += "Nume: "+ this.nume +"\n";
        output += "IBAN: "+ this.IBAN +"\n";
        output += "Număr: "+ this.număr +"\n";
        output += "CVV: "+ this.CVV +"\n";
        output += "Dată expirare: "+ this.dată_expirare +"\n";
        output += "PIN: "+ this.PIN +"\n";
        return output;
    }

    public String CSV(){
        return nume + "," + IBAN + "," + număr + "," + CVV + "," + dată_expirare + "," + PIN;
    }

    private String GenereazăNumăr(){
        Random rand = new Random();
        StringBuilder număr = new StringBuilder();
        for(int i=0; i<16; i++){
            număr.append(rand.nextInt(10));
        }
        return număr.toString();
    }

    private String GenereazăCVV(){
        Random rand = new Random();
        StringBuilder cvv = new StringBuilder();
        for(int i=0; i<3; i++){
            cvv.append(rand.nextInt(10));
        }
        return cvv.toString();
    }

    private String GenereazăDată(){
        DateFormat format_dată = new SimpleDateFormat("MM/yy");
        Calendar dată = Calendar.getInstance();
        dată.setTime(new Date());
        dată.add(Calendar.YEAR, 5);
        return format_dată.format(dată.getTime());
    }

    private String GenereazăPin(){
        Random rand = new Random();
        StringBuilder pin = new StringBuilder();
        for(int i=0; i<4; i++){
            pin.append(rand.nextInt(10));
        }
        return pin.toString();
    }

    public String getDată_expirare() {
        return dată_expirare;
    }

    public String getNumăr() {
        return număr;
    }

    public String getCVV() {
        return CVV;
    }

    public String getNume() {
        return nume;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }
}
