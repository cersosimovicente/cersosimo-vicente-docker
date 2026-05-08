package com.miempresa.miapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    // Hostname = nombre del contenedor MySQL en la red java-net
    private static final String HOST = "mysql-container";
    private static final String PORT = "3306";
    private static final String DATABASE = "appdb";
    private static final String USER = "appuser";
    private static final String PASSWORD = "apppass";

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    public static Connection obtener() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}
