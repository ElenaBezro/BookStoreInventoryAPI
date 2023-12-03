package com.bookstore.userManagement;

public enum UserRole {
    ADMIN("admin"),
    USER("user");
    private String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
