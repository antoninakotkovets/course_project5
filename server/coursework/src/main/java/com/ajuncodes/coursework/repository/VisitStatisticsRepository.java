package com.ajuncodes.coursework.repository;


import com.ajuncodes.coursework.model.VisitStatisticsKotkovets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitStatisticsRepository extends JpaRepository<VisitStatisticsKotkovets, Integer> {

}

