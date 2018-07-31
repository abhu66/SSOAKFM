/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssoakfm.dao;

import ssoakfm.model.User;

/**
 *
 * @author Osvaldo
 */
public interface UserDao {
    public void login(User user);
    public void addUser(User user);
    public void deleteUser(User user);
    public void editUser(User user);
}
