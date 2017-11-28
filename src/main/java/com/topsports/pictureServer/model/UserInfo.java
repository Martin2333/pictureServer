package com.topsports.pictureServer.model;

/**
 * Created by huang.cj on 2017/11/22.
 */
public class UserInfo {
    private int id;
    private String userCode;
    private String userName;
    private String password;
    private String role;

    public UserInfo(String userCode,String userName,String password,String role){
        this.userCode = userCode;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private String brandCode;
    private boolean enabled;
}
