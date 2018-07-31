/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssoakfm.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author khoerulAbu
 */
public class ConnectionMysql {
    private static Connection mysqlConnection;
    public static Connection getConnection(){
        try{ 
            String url = "jdbc:mysql://localhost/ssoakfm_db";
            String user = "root";
            String password = "";
            mysqlConnection = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi sukses  !");
        }
        catch(SQLException x){
            JOptionPane.showMessageDialog(null, x.getMessage());
        }
        return mysqlConnection;
    }
    
}
