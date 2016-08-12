package com.katran.objectcreator.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.katran.objectcreator.model.Material;
import com.katran.objectcreator.model.SimpleObject;
import com.katran.objectcreator.service.ObjectManager;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/objects")
public class SimpleObjectController {

    @Autowired
    private ObjectManager manager;

    @GetMapping(value = "/")
    public ResponseEntity<List<SimpleObject>> allObjects() {
        return new ResponseEntity<List<SimpleObject>>(manager.getListOfCompletedObjects(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SimpleObject> getSimpleObject(@PathVariable Integer id) {
        if (id<1||id>manager.getNumberOfRowsInTable("object_list")){
            return new ResponseEntity<SimpleObject>(HttpStatus.NOT_FOUND);
        }
        SimpleObject responseObject;
        try {
            responseObject = manager.getCompletedObjectByIndex(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<SimpleObject>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SimpleObject>(responseObject, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<SimpleObject> addSimpleObject(@RequestBody SimpleObject newObject){
        try {
            manager.saveObject(newObject);
        } catch (RuntimeException e) {
            return new ResponseEntity<SimpleObject>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<SimpleObject>(manager.getCompletedObjectByIndex(manager.getNumberOfRowsInTable("object_list")), HttpStatus.OK);
    }

    @PostMapping(value = "/list/")
    public ResponseEntity<List<SimpleObject>> addListObjects(@RequestBody List<SimpleObject> newObjects){
        try {
            for (int i=0; i<newObjects.size(); i++){
                manager.saveObject(newObjects.get(i));
                newObjects.set(i, manager.getCompletedObjectByIndex(manager.getNumberOfRowsInTable("object_list")));
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<List<SimpleObject>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<SimpleObject>>(newObjects, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SimpleObject> putSimpleObject(@PathVariable Integer id, @RequestBody SimpleObject newObject){
        if(id<1||id>manager.getNumberOfRowsInTable("object_list")){
            return new ResponseEntity<SimpleObject>(HttpStatus.NOT_FOUND);
        }
        try {
            manager.updateObject(id, newObject);
        } catch (RuntimeException e) {
            return new ResponseEntity<SimpleObject>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<SimpleObject>(manager.getCompletedObjectByIndex(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/")
    public ResponseEntity<SimpleObject> deleteAllObjects() {
        try {
            manager.deleteAllObjects();
        } catch (RuntimeException e) {
            return new ResponseEntity<SimpleObject>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<SimpleObject>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SimpleObject> deleteMaterialByName(@PathVariable Integer id) {
        if(id<1||id>manager.getNumberOfRowsInTable("object_list")){
            return new ResponseEntity<SimpleObject>(HttpStatus.NOT_FOUND);
        }
        try {
            manager.deleteObject(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<SimpleObject>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<SimpleObject>(HttpStatus.NO_CONTENT);
    }
}