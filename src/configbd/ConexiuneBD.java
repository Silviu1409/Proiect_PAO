package configbd;

import java.sql.*;

public class ConexiuneBD {
    private static Connection conexiune;

    private ConexiuneBD () {}

    public static Connection getInstance() throws SQLException, ClassNotFoundException{
        if (conexiune == null){
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/proiect_pao_apl_bancara";
            String username = "root";
            String parolă = "Project2022PAO";

            conexiune = DriverManager.getConnection(url, username, parolă);
        }
        return conexiune;
    }
}
