package cont;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Economii extends Cont{

    protected double dobândă;

    public Economii(String nume) {
        super(nume);
        this.dobândă = 2;
    }

    public Economii(String nume, double dobândă) {
        super(nume);
        this.dobândă = dobândă;
    }

    public Economii(String IBAN, String nume, String BIC, double balanță, double dobândă) {
        super(IBAN, nume, BIC, balanță);
        this.dobândă = dobândă;
    }

    public Economii(ResultSet res) throws SQLException {
        super(res.getString("IBAN"), res.getString("CNP_client"), res.getString("BIC"), res.getDouble("balanță"));
        this.dobândă = res.getDouble("dobândă");
    }

    @Override
    public String toString() {
        String output = super.toString();
        output += "Dobândă: "+ this.dobândă + "%" +"\n";
        return output;
    }

    @Override
    public String CSV(){
        return super.CSV() + "," + dobândă;
    }

    public double getDobândă() {
        return dobândă;
    }

    public void setDobândă(double dobândă) {
        this.dobândă = dobândă;
    }
}
