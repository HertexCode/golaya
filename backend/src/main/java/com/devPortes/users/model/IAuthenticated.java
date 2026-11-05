package com.devPortes.users.model;

public interface IAuthenticated {
    Long getId();
    String getName();
    String getEmail();
    String getPasswordHash();
    RoleEnum getRole();
    String getIdentityDocument();
    String getPhoneNumber();
}
