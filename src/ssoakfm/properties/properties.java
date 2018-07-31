/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssoakfm.properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import ssoakfm.connection.ConnectionMysql;

/**
 *
 * @author khoerulAbu
 */
public class properties {
    public ResultSet rs;
    public Statement st;
    public PreparedStatement ps;
    public final Connection con = ConnectionMysql.getConnection();
    
    
    public void succesMessage(String message){
        JOptionPane.showMessageDialog(null, "Succes ! "+message);
    }
    public void errorMessage(String message){
        JOptionPane.showMessageDialog(null, "Error ! "+message);
    }
    
}
