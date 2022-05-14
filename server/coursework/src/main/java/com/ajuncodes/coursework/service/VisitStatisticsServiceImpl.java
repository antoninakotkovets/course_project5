package com.ajuncodes.coursework.service;


import com.ajuncodes.coursework.model.VisitStatisticsKotkovets;
import com.ajuncodes.coursework.repository.UsersRepository;
import com.ajuncodes.coursework.repository.VisitStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitStatisticsServiceImpl implements VisitStatisticsService {

    @Autowired
    private VisitStatisticsRepository visitStatisticsRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public boolean saveVisitStatistics(VisitStatisticsKotkovets visitStatisticsKotkovets, int userId){
        visitStatisticsKotkovets.setUser(usersRepository.getById(userId));
        visitStatisticsRepository.save(visitStatisticsKotkovets);
        return true;
    }
    @Override
    public List<VisitStatisticsKotkovets> getAllVisit(int offset, int limit){
        List<VisitStatisticsKotkovets> result = new ArrayList<>();
        Page<VisitStatisticsKotkovets> res = visitStatisticsRepository.findAll(PageRequest.of(offset, limit));
        result.addAll(res.getContent());
        return result;
    }

    @Override
    public int getVisitsCount() {
        return (int) visitStatisticsRepository.count();
    }
}
