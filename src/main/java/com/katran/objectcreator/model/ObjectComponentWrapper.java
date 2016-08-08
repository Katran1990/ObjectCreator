package com.katran.objectcreator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boris on 07.08.2016.
 */
public class ObjectComponentWrapper {

    private List<ObjectComponent> componentList;

    public ObjectComponentWrapper(){
    }

    public ObjectComponentWrapper(List<ObjectComponent> componentList){
        this.componentList = componentList;
    }

    public List<ObjectComponent> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<ObjectComponent> componentList) {
        this.componentList = componentList;
    }
}
