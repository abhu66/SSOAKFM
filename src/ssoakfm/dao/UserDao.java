/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssoakfm.dao;

import ssoakfm.model.user.User;
import ssoakfm.view.login.LoginForm;
import ssoakfm.view.user.FormNewUser;
import ssoakfm.view.user.UserPage;

/**
 *
 * @author Osvaldo
 */
public interface UserDao {
    public void login(LoginForm loginForm);
    public void addUser(FormNewUser formNewUser);
    public void disabledUser(UserPage userPage);
    public void editUser(FormNewUser formNewUser);
    public void dataTableUser(UserPage userPage);
}
