package cont;

import java.io.*;
import java.util.*;

public class DepozitSingleton {

    private static DepozitSingleton instanță = null;
    private List<Depozit> listă_depozite = new ArrayList<>();

    private DepozitSingleton() {}

    public static DepozitSingleton getInstanță(){
        if (instanță == null){
            instanță = new DepozitSingleton();
        }
        return instanță;
    }

    public List<Depozit> getDepozite() {
        return listă_depozite;
    }

    public void setDepozite(List<Depozit> listă_depozite) {
        this.listă_depozite = listă_depozite;
    }

    public void citeștefișier(){
        try(var input = new BufferedReader(new FileReader("date/depozite.csv"))) {
            String linie;

            while((linie = input.readLine()) != null){
                String[] câmpuri = linie.split(",");
                Depozit depozit = new Depozit(câmpuri[0], câmpuri[1], câmpuri[2], Double.parseDouble(câmpuri[3]), Double.parseDouble(câmpuri[4]), câmpuri[5], câmpuri[6]);
                listă_depozite.add(depozit);
            }
        } catch (IOException e) {
            System.out.println("Niciun depozit salvat în fișier!");
        }
    }

    public void scriefișier(){
        try(FileWriter scriere = new FileWriter("date/depozite.csv")) {
            for (Depozit depozit : listă_depozite) {
                scriere.write(depozit.CSV());
                scriere.write("\n");
            }
        } catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
