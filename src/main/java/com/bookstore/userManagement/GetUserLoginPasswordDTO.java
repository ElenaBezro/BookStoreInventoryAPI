package com.bookstore.userManagement;

public class GetUserLoginPasswordDTO {
    private String userName;
    private String password;

    public GetUserLoginPasswordDTO() {
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
}
