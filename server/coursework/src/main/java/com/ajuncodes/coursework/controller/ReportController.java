package com.ajuncodes.coursework.controller;

import com.ajuncodes.coursework.service.ComparisonResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

@Controller
@RequestMapping("/viewHistory")
public class ReportController {

    @Autowired
    private ComparisonResultService comparisonResultService;

    @GetMapping("/all")
    public void saveReport() {
        comparisonResultService.saveAllDataInReport();
    }

    @GetMapping("/{id}")
    public void saveReportByUser(@PathVariable Integer id) {
        comparisonResultService.saveAllDataInReportByUser(id);
    }
}
