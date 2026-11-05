package com.devPortes.gallery.model;

import java.time.LocalDate;
import java.util.List;
 // MODELO DE DOMINIO → DEFINE LA LÓGICA PURA DEL NEGOCIO
public class Post {
    private Long id;
    private String name;
    private String description;
    private List<String> urlPictures;
    private LocalDate eventDate;
    public Post() {
    }

    public Post(Long id, String name, String description, List<String> urlPictures, LocalDate eventDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.urlPictures = urlPictures;
        this.eventDate = eventDate;
    }

     public static Post create(String name, String description, List<String> urlPictures, LocalDate eventDate){
         return new Post(null, name, description, urlPictures, eventDate);
     }
     public static Post edit (String name, String description, List<String> urlPictures, LocalDate eventDate){
         return new Post(null, name, description, urlPictures, eventDate);
     }
    public static Post reconstitute(Long id, String name, String description, List<String> urlPictures, LocalDate eventDate){
        return new Post(id,name,description, urlPictures, eventDate);
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

     public List<String> getUrlPictures() {
         return urlPictures;
     }

     public LocalDate getEventDate() {
         return eventDate;
     }
 }
