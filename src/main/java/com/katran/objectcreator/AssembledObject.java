package com.katran.objectcreator;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Boris on 26.05.2016.
 */
public class AssembledObject {

    private String assembledObject;

    public AssembledObject(String assembledObject) {
        this.assembledObject = assembledObject;
    }

    public AssembledObject() {
    }

    public String getAssembledObject() {
        return assembledObject;
    }

    public void setAssembledObject(String assembledObject) {
        this.assembledObject = assembledObject;
    }

    @Override
    public String toString() {
        return assembledObject;
    }
}
