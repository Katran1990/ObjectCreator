package com.katran.app.object;

/**
 * Created by Boris on 02.07.2016.
 */
public class TestWebObject {

    private String subject;
    private String quality;
    private String material;

    public TestWebObject(String subject, String quality, String material) {
        this.subject = subject;
        this.quality = quality;
        this.material = material;
    }

    public TestWebObject() {
    }

    @Override
    public String toString() {
        return subject + " " + quality + " " + material;
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
}
