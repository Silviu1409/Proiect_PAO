package adresă;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Adresă {

    private String stradă, oraș, cod_poștal;
    private int număr;
    public Adresă(){}

    public Adresă(String stradă, int număr, String oraș, String cod_poștal){
        this.stradă = stradă;
        this.număr = număr;
        this.oraș = oraș;
        this.cod_poștal = cod_poștal;
    }

    public Adresă(ResultSet res) throws SQLException {
        this.stradă = res.getString("stradă");
        this.număr = res.getInt("număr");
        this.oraș = res.getString("oraș");
        this.cod_poștal = res.getString("cod_poștal");
    }

    public String getStradă() {
        return stradă;
    }

    public String getOraș() {
        return oraș;
    }


    public int getNumăr() {
        return număr;
    }

    public String getCod_poștal() {
        return cod_poștal;
    }

    public void setStradă(String stradă) {
        this.stradă = stradă;
    }

    public void setOraș(String oraș) {
        this.oraș = oraș;
    }


    public void setNumăr(int număr) {
        this.număr = număr;
    }

    public void setCod_poștal(String cod_poștal) {
        this.cod_poștal = cod_poștal;
    }

    @Override
    public String toString(){
        String output = "Stradă: "+ this.stradă +"\n";
        output += "Număr: "+ this.număr +"\n";
        output += "Oraș: "+ this.oraș +"\n";
        output += "Cod poștal: "+ this.cod_poștal +"\n";
        return output;
    }

    public String CSV(){
        return stradă + "," + număr + "," + oraș  + "," + cod_poștal;
    }
}

