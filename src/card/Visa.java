package card;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Visa extends Card{

    private boolean asigurarecălătorie;

    public Visa(String nume, String IBAN, boolean asigurarecălătorie) {
        super(nume, IBAN);
        this.asigurarecălătorie = asigurarecălătorie;
    }

    public Visa(String nume, String IBAN, String număr, String CVV, String dată_expirare, String PIN, boolean asigurarecălătorie) {
        super(nume, IBAN, număr, CVV, dată_expirare, PIN);
        this.asigurarecălătorie = asigurarecălătorie;
    }

    public Visa(ResultSet res) throws SQLException {
        super(res.getString("nume"), res.getString("IBAN"), res.getString("număr"), res.getString("CVV"), res.getString("dată_expirare"), res.getString("PIN"));
        this.asigurarecălătorie = res.getBoolean("asigurarecălătorie");
    }

    @Override
    public String toString(){
        String output = super.toString();
        output += "Are asigurare de călătorie: "+ this.asigurarecălătorie +"\n";
        return output;
    }

    @Override
    public String CSV(){
        return super.CSV() + "," + asigurarecălătorie;
    }

    public boolean getAsigurarecălătorie() {
        return asigurarecălătorie;
    }

    public void setAsigurarecălătorie(boolean asigurarecălătorie) {
        this.asigurarecălătorie = asigurarecălătorie;
    }

}
