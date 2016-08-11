package com.katran.objectcreator.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.katran.objectcreator.model.Material;
import com.katran.objectcreator.model.SimpleObject;
import com.katran.objectcreator.service.ObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/objects")
public class SimpleObjectController {

    @Autowired
    private ObjectManager manager;

    @GetMapping(value = "/")
    public List<SimpleObject> allObjects() {
        return manager.getListOfCompletedObjects();
    }

    @GetMapping(value = "/{id}")
    public SimpleObject getMaterialById(@PathVariable Integer id) {
        return manager.getCompletedObjectByIndex(id);
    }

    @GetMapping
    public String getMaterialByName(@RequestParam(required = true) String name) {
        return new Gson().toJson(manager.getMaterial(name));
    }

//    PostMapping
//    public Material addMaterial(@RequestParam String name){
//        manager.addMaterial(name);
//        return
//    }

//    @PostMapping(value = "/")
//    public Material addMaterial(@RequestParam(required = true) String materialName) {
//        try {
//            manager.addMaterial(materialName);
//        } catch (DuplicateKeyException dke) {
//            JsonObject json = new JsonObject();
//            json.addProperty("name", dke.getClass().getSimpleName());
//            json.addProperty("message", "duplicate entry");
//            return json;
//        }
//        return manager.getMaterial(materialName);
//    }

    @DeleteMapping(value = "/delete/id:{id}")
    public String deleteMaterialById(@PathVariable Integer id) {
        try {
            return new Gson().toJson(manager.deleteMaterial(id));
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping(value = "/delete/name:{materialName}")
    public String deleteMaterialByName(@PathVariable String materialName) {
        try {
            return new Gson().toJson(manager.deleteMaterial(materialName));
        } catch (Exception e) {
            return null;
        }
    }
}