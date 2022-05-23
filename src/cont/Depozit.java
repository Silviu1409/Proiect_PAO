package cont;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.*;
import java.util.*;

public class Depozit extends Economii{

    protected final String dată_început, dată_final;

    public Depozit(String nume) {
        super(nume);
        DateFormat format_dată = new SimpleDateFormat("dd/MM/yyyy");
        Date dată = new Date();
        this.dată_început = format_dată.format(dată);
        Calendar c = Calendar.getInstance();
        c.setTime(dată);
        c.add(Calendar.YEAR, 1);
        this.dată_final = format_dată.format(c.getTime());
    }


    public Depozit(String nume, double dobândă, String dată_început, String dată_final) {
        super(nume, dobândă);
        this.dată_început = dată_început;
        this.dată_final = dată_final;
    }

    public Depozit(String IBAN, String nume, String BIC, double balanță, double dobândă, String dată_început, String dată_final) {
        super(IBAN, nume, BIC, balanță, dobândă);
        this.dată_început = dată_început;
        this.dată_final = dată_final;
    }

    public Depozit(ResultSet res) throws SQLException {
        super(res.getString("IBAN"), res.getString("CNP_client"), res.getString("BIC"), res.getDouble("balanță"), res.getDouble("dobândă"));
        this.dată_început = res.getString("dată_început");
        this.dată_final = res.getString("dată_final");
    }

    @Override
    public String toString() {
        String output = super.toString();
        output += "Dată_început: "+ this.dată_început +"\n";
        output += "Dată_final: "+ this.dată_final +"\n";
        return output;
    }

    @Override
    public String CSV(){
        return super.CSV() + "," + dată_început + "," + dată_final;
    }

    public String getDată_început() {
        return dată_început;
    }

    public String getDată_final() {
        return dată_final;
    }
}
