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
public class RestCreatorController {

    @Autowired
    private AssemblyService assemblyService;

    @Autowired
    private Manager manager;

    @GetMapping(value = "/materials")
    public String allMaterials(){
        return new Gson().toJson(manager.getAllMaterials());
    }

    @PostMapping(value = "/{materialName}")
    public HttpStatus addMaterial(@PathVariable String materialName){
        manager.addMaterial(materialName);
        return HttpStatus.OK;
    }


}
