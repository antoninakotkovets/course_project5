package com.ajuncodes.coursework.controller;


import com.ajuncodes.coursework.model.VisitStatisticsKotkovets;
import com.ajuncodes.coursework.service.VisitStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitStatistics")
public class VisitStatisticsController {

    @Autowired
    private VisitStatisticsService visitStatisticsService;

    @PostMapping("/add/{userId}")
    public boolean add(@RequestBody VisitStatisticsKotkovets visitStatisticsKotkovets, @PathVariable int userId){
        return visitStatisticsService.saveVisitStatistics(visitStatisticsKotkovets, userId);
    }
    @GetMapping("/getAll")
    public List<VisitStatisticsKotkovets> getAll(@RequestParam int offset,@RequestParam int limit){
        return visitStatisticsService.getAllVisit(offset, limit);
    }

    @GetMapping("/count")
    public int getVisitsCount() {
        return visitStatisticsService.getVisitsCount();
    }
}
