/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssoakfm.daoImpl;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.relation.Role;
import javax.swing.table.DefaultTableModel;
import ssoakfm.dao.UserDao;
import ssoakfm.model.user.Roles;
import ssoakfm.properties.properties;
import ssoakfm.view.login.LoginForm;
import ssoakfm.view.user.FormNewUser;
import ssoakfm.view.user.UserPage;

/**
 *
 * @author khoerulAbu
 */
public class UserDaoImpl extends properties implements UserDao {
    
    private final String sqlAddUser = "INSERT INTO User values(null,?,?,?,?,?)";
    private final String sqlDisabledUser = "UPDATE User set status = ? WHERE NIK = ?";

    private DefaultTableModel tabelModel;

    @Override
    public void login(LoginForm loginForm) {
        try {
            String sqlLogin = "SELECT * FROM User where nik = '"+loginForm.jTextField1.getText()+"'"
                    + " and password = '"+loginForm.jPasswordField1.getText()+"' ";
            st = con.createStatement();
            rs = st.executeQuery(sqlLogin);
            if(rs.next()){
                loginForm.formUtama.jPanel4.setVisible(true);
                if(rs.getString("role").equalsIgnoreCase(Roles.Super_admin.name())){
                    loginForm.formUtama.jTabbedPane1.add(loginForm.formUtama.jPanel2);
                    loginForm.formUtama.jLabel4.setText(rs.getString("nik"));
                    loginForm.formUtama.jLabel7.setText(rs.getString("name"));
                    loginForm.formUtama.jLabel10.setText(rs.getString("role"));
                    loginForm.dispose();
                    System.out.print("ts : "+rs.getString("role"));
                }
                else if(rs.getString("role").equalsIgnoreCase(Roles.Admin_admin.name())){
                    loginForm.formUtama.jTabbedPane1.remove(loginForm.formUtama.jPanel2);
                    loginForm.formUtama.jLabel4.setText(rs.getString("nik"));
                    loginForm.formUtama.jLabel7.setText(rs.getString("name"));
                    loginForm.formUtama.jLabel10.setText(rs.getString("role"));
                    loginForm.dispose();
                    System.out.print("ts2 : "+rs.getString("role"));
                }
                else if(rs.getString("role").equalsIgnoreCase(Roles.Admin_gudang.name())){
                    loginForm.formUtama.jTabbedPane1.remove(loginForm.formUtama.jPanel2);
                    loginForm.formUtama.jLabel4.setText(rs.getString("nik"));
                    loginForm.formUtama.jLabel7.setText(rs.getString("name"));
                    loginForm.formUtama.jLabel10.setText(rs.getString("role"));
                    loginForm.dispose();
                    System.out.print("ts3 : "+rs.getString("role"));
                }
                System.out.print("ts4 : "+rs.getString("role"));
                succesMessage("Login succes");
            }
            else {
                errorMessage("User name or Password is wrong !");
            }
           
            
        } catch (SQLException ex) {
            errorMessage( ex.getMessage());
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addUser(FormNewUser formNewUser) {
        try {
            ps = con.prepareStatement(sqlAddUser);
            ps.setString(1,formNewUser.jTextField1.getText());
            ps.setString(2,formNewUser.jTextField2.getText());
            ps.setString(3,formNewUser.jPasswordField1.getText());
            ps.setString(4,formNewUser.jComboBox1.getSelectedItem().toString());
            ps.setString(5, null);
            ps.setString(5, "Enable");
            ps.executeUpdate();
            succesMessage(" Add User");
            dataTableUser(formNewUser.userPage);
            formNewUser.dispose();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void disabledUser(UserPage userPage) {
        try {
            int row = userPage.jTable1.getSelectedRow();
            ps = con.prepareStatement(sqlDisabledUser);
            ps.setString(1,"Disable");
            ps.setString(2,userPage.jTable1.getValueAt(row, 0).toString());
           
            ps.executeUpdate();
            succesMessage(" Disbaled User");
            dataTableUser(userPage);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editUser(FormNewUser formNewUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dataTableUser(UserPage userPage) {
        
        try {
            Object [] Header = {"NIK","NAMA","ROLE","STATUS"};
            tabelModel = new DefaultTableModel(null,Header);
            userPage.jTable1.setModel(tabelModel);
            String listUser = "SELECT * FROM User where nik = '"+userPage.jTextField1.getText()+"' or name like '%"+userPage.jTextField1.getText()+"%'";
            st = con.createStatement();
            rs = st.executeQuery(listUser);
            while(rs.next()){
                tabelModel.addRow(new Object[]{
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(5),
                    rs.getString(7)
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
