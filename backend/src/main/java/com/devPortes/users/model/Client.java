package com.devPortes.users.model;

import com.devPortes.users.exceptions.EmailInvalidException;
import com.devPortes.users.exceptions.IdentityDocumentInvalidException;
import com.devPortes.users.exceptions.PasswordHashInvalidException;

public class Client implements IAuthenticated{
    private Long id;
    private String urlPicture;
    private String name;
    private String identityDocument;
    private String phoneNumber;
    private String email;
    private String passwordHash;
    private ClasificationEnum classification;
    private int reservationAmount;
    private RoleEnum role;
    private boolean state;

    public Client() {
    }

    private Client(Long id, String urlPicture,String name, String identityDocument, String phoneNumber, String email, String passwordHash, ClasificationEnum classification, int reservationAmount, RoleEnum role, boolean state) {
        this.id = id;
        this.urlPicture=urlPicture;
        this.name = name;
        this.identityDocument = identityDocument;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passwordHash = passwordHash;
        this.classification = classification;
        this.reservationAmount = reservationAmount;
        this.role = role;
        this.state = state;
    }

    //Este metodo crea un nuevo model el cual sera usado como principal, hace validaciones y logica pura de Java
    public static Client create(String name,String identityDocument, String phoneNumber, String email, String passwordHash, RoleEnum role) {

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

        return new Client(null,"",name, identityDocument, phoneNumber, email,passwordHash, ClasificationEnum.ESTANDAR,0, role, true);
    }

    public static Client edit( String urlPicture,String name, String identityDocument, String phoneNumber, String email, String passwordHash, ClasificationEnum classification, int reservationAmount, RoleEnum role, boolean state) {
        return new Client(null,urlPicture, name, identityDocument, phoneNumber, email, passwordHash,  classification,  reservationAmount, role, state);
    }
    //Este metodo construye un model que viene de base de datos, ya que no necesita validaciones ni nada porque se supone que ya esta bien construido y guardado en db
    public static Client reconstitute(Long id,  String urlPicture,String name, String identityDocument, String phoneNumber, String email, String passwordHash, ClasificationEnum classification, int reservationAmount, RoleEnum role, boolean state) {
        return new Client(id,urlPicture, name, identityDocument, phoneNumber, email, passwordHash,  classification,  reservationAmount, role, state);
    }
    @Override
    public Long getId() {
        return id;
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
    @Override
    public String getName() {
        return name;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ClasificationEnum getClassification() {
        return classification;
    }

    public int getReservationAmount() {
        return reservationAmount;
    }

    public boolean isState() {
        return state;
    }
}
