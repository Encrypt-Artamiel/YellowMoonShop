/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.users;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class UsersDTO implements Serializable {

    private String userID;
    private String password;
    private String name;
    private String phone;
    private String address;
    private int roleID;
    

    public UsersDTO(String userID, String password, String name, String phone, String address, int roleID) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.roleID = roleID;
    }

    public UsersDTO() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }
}
