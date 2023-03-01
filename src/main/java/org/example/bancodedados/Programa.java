package org.example.bancodedados;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Programa {

    public static void main(String[] args) throws SQLException {
        Connection conn = null; //conectar o banco | variavel
        Statement st = null; // consulta sql
        ResultSet rs = null; // resultado
        try {
            conn = ConnectionFactory.getConnetion(); //conectar o banco

            st = conn.createStatement();

            rs = st.executeQuery("select * from department"); //consulta

            while (rs.next()) {//enquanto existir o proximo
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
