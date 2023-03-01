package org.example.bancodedados;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static Connection conn = null; //objeto de conexão com o banco de dados do JDBC

    public static Connection getConnetion() {
            try {
                Properties props = loadProperties(); //método criado
                String url = props.getProperty("dburl"); //está definido no arquivo db.properties
                return DriverManager.getConnection(url, props); //conexão com o banco
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }


    //método para carregar as propriedades definidas no arquivo "db.properties"
    private static Properties loadProperties() {

        try (FileInputStream fs = new FileInputStream("/home/grozolin/pagseguro/repository/banco-de-dados/src/main/resources/db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
}
