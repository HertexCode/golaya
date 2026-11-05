package com.devPortes.fields.model;

import com.devPortes.location.model.Location;

import java.math.BigDecimal;
import java.util.List;

public class Field {

    private Long id;
    private Location location;
    private List<String> urlPictures;
    private String name;
    private String capacity;
    private String sport;
    private String surface;
    private String description;
    private List<String> details;
    private BigDecimal hourlyRate;
    private FieldStateEnum state;
    private boolean visible;

    public Field() {
    }

    public Field(Long id, Location location, List<String> urlPictures, String name, String capacity, String sport, String surface,
                 String description, List<String> details, BigDecimal hourlyRate, FieldStateEnum state) {
        this(id, location, urlPictures, name, capacity, sport, surface, description, details, hourlyRate, state, true);
    }

    public Field(Long id, Location location, List<String> urlPictures, String name, String capacity, String sport, String surface,
                 String description, List<String> details, BigDecimal hourlyRate, FieldStateEnum state, boolean visible) {
        this.id = id;
        this.location = location;
        this.urlPictures = urlPictures;
        this.name = name;
        this.capacity = capacity;
        this.sport = sport;
        this.surface = surface;
        this.description = description;
        this.details = details;
        this.hourlyRate = hourlyRate;
        this.state = state;
        this.visible = visible;
    }

    public static Field create(Location location, List<String> urlPictures, String name, String capacity, String sport, String surface,
                                 String description, List<String> details, BigDecimal hourlyRate) {

        FieldStateEnum state = FieldStateEnum.DISPONIBLE;
        return new Field(null, location, urlPictures, name, capacity, sport, surface,
                description, details, hourlyRate, state);
    }

    public static Field edit(Location location, List<String> urlPictures, String name, String capacity, String sport, String surface,
                                     String description, List<String> details, BigDecimal hourlyRate, FieldStateEnum state) {
        return new Field(null, location, urlPictures, name, capacity, sport, surface,
                description, details, hourlyRate, state);
    }
    public static Field reconstitute(Long id, Location location, List<String> urlPictures, String name, String capacity, String sport, String surface,
                                     String description, List<String> details, BigDecimal hourlyRate, FieldStateEnum state) {
        return new Field(id, location, urlPictures, name, capacity, sport, surface,
                description, details, hourlyRate, state);
    }

    public static Field reconstitute(Long id, Location location, List<String> urlPictures, String name, String capacity, String sport, String surface,
                                     String description, List<String> details, BigDecimal hourlyRate, FieldStateEnum state, boolean visible) {
        return new Field(id, location, urlPictures, name, capacity, sport, surface,
                description, details, hourlyRate, state, visible);
    }

    public Long getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public List<String> getUrlPictures() {
        return urlPictures;
    }

    public String getName() {
        return name;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getSport() {
        return sport;
    }

    public String getSurface() {
        return surface;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDetails() {
        return details;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public FieldStateEnum getState() {
        return state;
    }

    public boolean isVisible() {
        return visible;
    }
}
