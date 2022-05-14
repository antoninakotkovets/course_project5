package com.ajuncodes.coursework.controller;


import com.ajuncodes.coursework.model.ComparisonResultKotkovets;
import com.ajuncodes.coursework.service.ComparisonResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comparisonResult")
public class ComparisonResultController {

    @Autowired
    private ComparisonResultService comparisonResultService;

    @PostMapping("/add/{userId}")
    public int add(@RequestBody ComparisonResultKotkovets comparisonResultKotkovets, @PathVariable int userId){
        return comparisonResultService.saveComparisonResult(comparisonResultKotkovets, userId);
    }

    @GetMapping("/getAll/{userId}")
    public List<ComparisonResultKotkovets> getUserComparisons(@PathVariable Integer userId){
        return comparisonResultService.getUserComparisons(userId);
    }

    @GetMapping("/getAll")
    public List<ComparisonResultKotkovets> getUserComparisons(){
        return comparisonResultService.getUsersComparisons();
    }
}
