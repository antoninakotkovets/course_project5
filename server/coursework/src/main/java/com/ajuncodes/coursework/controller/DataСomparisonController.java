package com.ajuncodes.coursework.controller;


import com.ajuncodes.coursework.model.DataСomparisonKotkovets;
import com.ajuncodes.coursework.service.DataСomparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dataСomparison")
public class DataСomparisonController {

    @Autowired
    private DataСomparisonService dataСomparisonService;

    @PostMapping("/add/{comparisonId}")
    public boolean add(@RequestBody DataСomparisonKotkovets dataСomparisonKotkovets, @PathVariable int comparisonId){
        return dataСomparisonService.saveDataСomparison(dataСomparisonKotkovets, comparisonId);

    }
    @GetMapping("/getAll")
    public List<DataСomparisonKotkovets> getAllDataСomparison(){
        return dataСomparisonService.getAllDataСomparison();
    }
}
