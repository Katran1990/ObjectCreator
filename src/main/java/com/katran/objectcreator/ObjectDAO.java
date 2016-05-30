package com.katran.objectcreator;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Created by Boris on 30.05.2016.
 */
public class ObjectDAO {

    @Autowired
    private DatabaseConnectionService connectionService;

    public List getObjects() {
        return null;
    }

    public AssembledObject getObject(int id) {
        return null;
    }

    public void updateObject(AssembledObject object) {

    }

    public void deleteObject(AssembledObject object) {

    }

    public void addObject(AssembledObject object) {

    }
}
