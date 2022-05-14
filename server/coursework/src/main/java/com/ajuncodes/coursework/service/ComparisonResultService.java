package com.ajuncodes.coursework.service;

import com.ajuncodes.coursework.model.ComparisonResultKotkovets;

import java.util.List;

public interface ComparisonResultService {
    public List<ComparisonResultKotkovets> getUsersComparisons();
    public int saveComparisonResult(ComparisonResultKotkovets comparisonResultKotkovets, int userId);
    public List<ComparisonResultKotkovets> getUserComparisons(Integer userId);

    boolean saveAllDataInReport();

    boolean saveAllDataInReportByUser(Integer id);
}
