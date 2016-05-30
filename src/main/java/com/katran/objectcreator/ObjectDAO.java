package com.katran.objectcreator;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Boris on 30.05.2016.
 */
public interface ObjectDAO {
    List getObjects();
    AssembledObject getObject(int id);
    void updateObject(AssembledObject object);
    void deleteObject(AssembledObject object);
    void addObject(AssembledObject object);
}
