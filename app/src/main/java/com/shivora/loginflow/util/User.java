package com.shivora.loginflow.util;

public class User {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String secretKey;

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
