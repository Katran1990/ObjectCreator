package com.katran.app.database.clazzes;

/**
 * Created by Boris on 04.08.2016.
 */
public class Material {

    private Integer id;
    private String name;

    public Material(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Material(){

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
