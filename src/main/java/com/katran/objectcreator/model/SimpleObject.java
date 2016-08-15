package com.katran.objectcreator.model;

public class SimpleObject {

    private int id;
    private String subject;
    private String quality;
    private String material;

    public SimpleObject(int id, String subject, String quality, String material) {
        this(subject,quality,material);
        this.id = id;
    }

    public SimpleObject(String subject, String quality, String material) {
        this.subject = subject;
        this.quality = quality;
        this.material = material;
    }

    public SimpleObject() {
    }

    @Override
    public String toString() {
        //TODO string utils
        return quality + " " + material + " " + subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
