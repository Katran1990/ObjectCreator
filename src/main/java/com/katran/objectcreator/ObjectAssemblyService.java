package com.katran.objectcreator;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Boris on 25.05.2016.
 */

public class ObjectAssemblyService {

    public String assemblyOfObject(String quality, String material, String object) {
        return quality + " " + material + " " + object;
    }
}
