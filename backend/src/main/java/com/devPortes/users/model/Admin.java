package com.devPortes.users.model;

import com.devPortes.users.exceptions.EmailInvalidException;
import com.devPortes.users.exceptions.IdentityDocumentInvalidException;
import com.devPortes.users.exceptions.PasswordHashInvalidException;

public class Admin implements IAuthenticated{
    private Long id;
    private String name;
    private String identityDocument;
    private String phoneNumber;
    private String email;
    private String passwordHash;
    private RoleEnum role;
    private boolean state;

    public Admin() {
    }

    public Admin(Long id, String name, String identityDocument, String phoneNumber, String email, String passwordHash, RoleEnum role, boolean state) {
        this.id = id;
        this.name = name;
        this.identityDocument = identityDocument;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.state = state;
    }

    //Este metodo crea un nuevo model el cual sera usado como principal, hace validaciones y logica pura de Java
    public static Admin create(String name, String identityDocument, String phoneNumber, String email, String passwordHash, RoleEnum role) {

        if (identityDocument.length() < 10) {
            identityDocument = identityDocument.toLowerCase();
        }else {
            throw new IdentityDocumentInvalidException(identityDocument);
        }

        if (!email.contains("@") && !email.contains(".com")) {
            throw new EmailInvalidException(email);
        }else {
            email = email.toLowerCase();
        }
        if (passwordHash.isEmpty()) {
            throw new PasswordHashInvalidException();
        }

        if (role == null){
            role = RoleEnum.CLIENTE;
        }

        return new Admin(null,name, identityDocument, phoneNumber, email,passwordHash, role, true);
    }

    //Este metodo construye un model que viene de base de datos, ya que no necesita validaciones ni nada porque se supone que ya esta bien construido y guardado en db
    public static Admin reconstitute(Long id,String name, String identityDocument, String phoneNumber, String email, String passwordHash, RoleEnum role, boolean state) {
        return new Admin(id, name, identityDocument, phoneNumber, email, passwordHash,  role, state);
    }

    @Override
    public Long getId() {
        return id;
    }
    @Override
    public String getName() {
        return name;
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public String getPasswordHash() {
        return passwordHash;
    }
    @Override
    public RoleEnum getRole() {
        return role;
    }

    public boolean isState() {
        return state;
    }
}
