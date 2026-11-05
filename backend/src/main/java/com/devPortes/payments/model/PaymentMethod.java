package com.devPortes.payments.model;

public class PaymentMethod {

    private Long id;
    private String name;
    private String description;
    private boolean state;

    public PaymentMethod() {
    }

    public PaymentMethod(Long id, String name, String description, boolean state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
    }

    public PaymentMethod create( String name, String description, boolean state){
        return new PaymentMethod(null, name, description, state);
    }
    public PaymentMethod reconstitute(Long id, String name, String description, boolean state){
        return new PaymentMethod(id, name, description, state);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isState() {
        return state;
    }
}
