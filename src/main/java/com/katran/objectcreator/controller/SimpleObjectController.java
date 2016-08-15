package com.katran.objectcreator.controller;

import com.katran.objectcreator.model.SimpleObject;
import com.katran.objectcreator.service.ObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/objects")
public class SimpleObjectController {

    @Autowired
    private ObjectManager manager;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SimpleObject> getSimpleObject(@PathVariable Integer id){
        return new ResponseEntity<>(manager.getCreatedObjectByID(id), HttpStatus.OK);
    }

    //Ready
    @PostMapping(value = "/")
    public ResponseEntity<SimpleObject> addSimpleObject(@RequestBody SimpleObject newObject) {
        return new ResponseEntity<>(manager.getCreatedObjectByID(manager.saveObject(newObject)), HttpStatus.OK);
    }

    //Ready
    @PutMapping(value = "/{id}")
    public ResponseEntity<SimpleObject> putSimpleObject(@PathVariable Integer id, @RequestBody SimpleObject newObject) {
        return new ResponseEntity<>(manager.getCreatedObjectByID(manager.updateObject(id, newObject)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SimpleObject> deleteMaterialByName(@PathVariable Integer id) {
        manager.deleteObject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}