package com.devPortes.location.model;

public class Location {

    private Long id;
    private String name;
    private String headquarters;
    private String address;
    private String urlQrAddress;
    private String urlAddress;
    private String description;
    private boolean state;
    private boolean visible;

    public Location() {
    }

    public Location(Long id, String name,String headquarters, String address, String urlQrAddress, String urlAddress, String description, boolean state) {
        this(id, name, headquarters, address, urlQrAddress, urlAddress, description, state, true);
    }

    public Location(Long id, String name,String headquarters, String address, String urlQrAddress, String urlAddress, String description, boolean state, boolean visible) {
        this.id = id;
        this.name = name;
        this.headquarters = headquarters;
        this.address = address;
        this.urlQrAddress = urlQrAddress;
        this.urlAddress = urlAddress;
        this.description = description;
        this.state = state;
        this.visible = visible;
    }

    public static Location create(String name, String headquarters, String address, String urlQrAddress, String urlAddress, String description){
        return new Location(null, name.trim(), headquarters, address, urlQrAddress, urlAddress, description, true);
    }
    public static Location edit(String name, String headquarters, String address, String urlQrAddress, String urlAddress, String description){
        return new Location(null, name.trim(), headquarters, address, urlQrAddress, urlAddress, description, true);
    }

    public static Location changeState(Long id, String name, String headquarters, String address, String urlQrAddress, String urlAddress, String description, boolean state){
        return new Location(id, name, headquarters, address, urlQrAddress, urlAddress, description, !state);
    }

    public static Location reconstitute(Long id,String name ,String headquarters, String address, String urlQrAddress, String urlAddress, String description, boolean state){
        return new Location(id ,name,headquarters, address, urlQrAddress, urlAddress, description, state);
    }

    public static Location reconstitute(Long id,String name ,String headquarters, String address, String urlQrAddress, String urlAddress, String description, boolean state, boolean visible){
        return new Location(id ,name,headquarters, address, urlQrAddress, urlAddress, description, state, visible);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public String getAddress() {
        return address;
    }

    public String getUrlQrAddress() {
        return urlQrAddress;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public String getDescription() {
        return description;
    }

    public boolean isState() {
        return state;
    }

    public boolean isVisible() {
        return visible;
    }
}
