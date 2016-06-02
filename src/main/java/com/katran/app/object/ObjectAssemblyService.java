package com.katran.app.object;

/**
 * Created by Boris on 25.05.2016.
 */

public class ObjectAssemblyService {
    public ObjectAssemblyService(){
    }

    public WebObject assemblyOfObject(String quality, String material, String object) {
        return new WebObject(quality+" "+material+" "+object);
    }
}