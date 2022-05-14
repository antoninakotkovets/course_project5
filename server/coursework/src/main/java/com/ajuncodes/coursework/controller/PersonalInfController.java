package com.ajuncodes.coursework.controller;


import com.ajuncodes.coursework.model.PesonalInfKotkovets;
import com.ajuncodes.coursework.service.PersonalInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personalInf")
public class PersonalInfController {
    @Autowired
    private PersonalInfService personalInfService;

    @PostMapping("/add/{userId}")
    public boolean add(@RequestBody PesonalInfKotkovets pesonalInfKotkovets, @PathVariable int userId){
        return personalInfService.savePersonalInf(pesonalInfKotkovets, userId);
    }

    @GetMapping("/getAll")
    public List<PesonalInfKotkovets> getAll() {
        return personalInfService.getAll();
    }

    @PutMapping("/edit/{id}")
    public PesonalInfKotkovets edinInfo(@PathVariable Integer id, @RequestBody PesonalInfKotkovets pesonalInfKotkovets){
        return personalInfService.editInfo(id, pesonalInfKotkovets);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteInfo(@PathVariable Integer id) {
        return personalInfService.deleteInfo(id);
    }
}
