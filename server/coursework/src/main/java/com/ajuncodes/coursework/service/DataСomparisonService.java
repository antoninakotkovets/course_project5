package com.ajuncodes.coursework.service;

import com.ajuncodes.coursework.model.DataСomparisonKotkovets;

import java.util.List;

public interface DataСomparisonService {
    public boolean saveDataСomparison(DataСomparisonKotkovets dataСomparisonKotkovets, int comparisonId);
    public List<DataСomparisonKotkovets> getAllDataСomparison();
}
