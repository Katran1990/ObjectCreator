package com.katran.app.controllers;

import com.google.gson.Gson;
import com.katran.app.database.clazzes.Material;
import com.katran.app.object.AssemblyService;
import com.katran.app.object.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Boris on 04.08.2016.
 */

@RestController
@RequestMapping(value = "/materials")
public class RestCreatorController {

    @Autowired
    private AssemblyService assemblyService;

    @Autowired
    private Manager manager;

    @GetMapping(value = "/get")
    public String allMaterials(){
        return new Gson().toJson(manager.getAllMaterials());
    }

    @PostMapping(value = "/add/{materialName}")
    public HttpStatus addMaterial(@PathVariable String materialName){
        manager.addMaterial(materialName);
        return HttpStatus.OK;
    }

    @GetMapping(value = "/getbyid/{id}")
    public String getMaterialById(@PathVariable Integer id){
        return new Gson().toJson(manager.getMaterial(id));
    }

    @GetMapping(value = "/getbyname/{materialName}")
    public String getMaterialByName(@PathVariable String materialName){
        return new Gson().toJson(manager.getMaterial(materialName));
    }

    @DeleteMapping(value = "/deletebyid/{id}")
    public String deleteMaterialById(@PathVariable Integer id){
        return new Gson().toJson(manager.deleteMaterial(id));
    }

    @DeleteMapping(value = "/deletebyname/{materialName}")
    public String deleteMaterialByName(@PathVariable String materialName){
        return new Gson().toJson(manager.deleteMaterial(materialName));
    }
}
