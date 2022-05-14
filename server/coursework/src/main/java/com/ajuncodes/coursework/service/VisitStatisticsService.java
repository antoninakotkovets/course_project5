package com.ajuncodes.coursework.service;

import com.ajuncodes.coursework.model.VisitStatisticsKotkovets;

import java.util.List;

public interface VisitStatisticsService {
    public boolean saveVisitStatistics(VisitStatisticsKotkovets visitStatisticsKotkovets, int userId);
    public List<VisitStatisticsKotkovets> getAllVisit(int offset, int limit);
    public int getVisitsCount();
}
