package com.katran.objectcreator;

/**
 * Created by Boris on 25.05.2016.
 */

public class ObjectAssemblyService {
    public ObjectAssemblyService(){
    }

    public AssembledObject assemblyOfObject(String quality, String material, String object) {
        return new AssembledObject(quality+" "+material+" "+object);
    }
}