package com.katran.objectcreator.model;

/**
 * Created by Boris on 07.08.2016.
 */
public class ObjectComponent {
    private String material;
    private String quality;

    public ObjectComponent(){

    }

    public ObjectComponent(String material, String quality) {
        this.material = material;
        this.quality = quality;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
