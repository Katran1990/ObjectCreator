package com.katran.app.object;

/**
 * Created by Boris on 26.05.2016.
 */
public class WebObject {

    private String object;

    public WebObject(String object) {
        this.object = object;
    }

    public WebObject() {
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return object;
    }
}
