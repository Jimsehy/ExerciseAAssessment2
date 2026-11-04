package com.ryan.exercisea;

public class Comic {
    String id;
    String name;
    String subtitle;
    String author;
    String category;

    public Comic() {

    }

    public Comic(String id, String name, String subtitle, String author, String category) {
        this.id = id;
        this.name = name;
        this.subtitle = subtitle;
        this.author = author;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

