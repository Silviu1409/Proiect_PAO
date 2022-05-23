package card;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MasterCard extends Card{

    private String nivel;   //Standard, Gold, Platinum, World

    public MasterCard(String nume, String IBAN, String nivel) {
        super(nume, IBAN);
        this.nivel = nivel;
    }

    public MasterCard(String nume, String IBAN, String număr, String CVV, String dată_expirare, String PIN, String nivel) {
        super(nume, IBAN, număr, CVV, dată_expirare, PIN);
        this.nivel = nivel;
    }

    public MasterCard(ResultSet res) throws SQLException {
        super(res.getString("nume"), res.getString("IBAN"), res.getString("număr"), res.getString("CVV"), res.getString("dată_expirare"), res.getString("PIN"));
        this.nivel = res.getString("nivel");
    }

    @Override
    public String toString(){
        String output = super.toString();
        output += "Nivel: "+ this.nivel +"\n";
        return output;
    }

    @Override
    public String CSV(){
        return super.CSV() + "," + nivel;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
