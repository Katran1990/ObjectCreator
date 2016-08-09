package com.katran.objectcreator.controller;

import com.google.gson.Gson;
import com.katran.objectcreator.service.ObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/materials")
public class RestCreatorController {

    @Autowired
    private ObjectManager manager;

    @GetMapping(value = "/get")
    public String allMaterials() {
        return new Gson().toJson(manager.getAllMaterials());
    }

    @GetMapping(value = "/get/id:{id}")
    public String getMaterialById(@PathVariable Integer id) {
        try {
            return new Gson().toJson(manager.getMaterial(id));
        } catch (Exception e) {
            return null;
        }

    }

    @GetMapping(value = "/get/name:{materialName}")
    public String getMaterialByName(@PathVariable String materialName) {
        try {
            return new Gson().toJson(manager.getMaterial(materialName));
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(value = "/post/name:{materialName}")
    public HttpStatus addMaterial(@PathVariable String materialName) {
        manager.addMaterial(materialName);
        return HttpStatus.OK;
    }

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