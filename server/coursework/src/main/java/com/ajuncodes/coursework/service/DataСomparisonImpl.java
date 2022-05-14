package com.ajuncodes.coursework.service;


import com.ajuncodes.coursework.model.DataСomparisonKotkovets;
import com.ajuncodes.coursework.repository.ComparisonResultRepository;
import com.ajuncodes.coursework.repository.DataСomparisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataСomparisonImpl implements DataСomparisonService{

    @Autowired
    private DataСomparisonRepository dataСomparisonRepository;
    @Autowired
    private ComparisonResultRepository comparisonResultRepository;

    @Override
    public boolean saveDataСomparison(DataСomparisonKotkovets dataСomparisonKotkovets, int comparisonId){
        dataСomparisonKotkovets.setComparison(comparisonResultRepository.getById(comparisonId));
        dataСomparisonRepository.save(dataСomparisonKotkovets);
        return true;
    }

    @Override
    public List<DataСomparisonKotkovets> getAllDataСomparison(){
        return dataСomparisonRepository.findAll();
    }
}
