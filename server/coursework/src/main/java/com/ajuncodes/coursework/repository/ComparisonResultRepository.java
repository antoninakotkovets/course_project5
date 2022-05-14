package com.ajuncodes.coursework.repository;

import com.ajuncodes.coursework.model.ComparisonResultKotkovets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ComparisonResultRepository extends JpaRepository<ComparisonResultKotkovets, Integer> {
    List<ComparisonResultKotkovets> findAllByUserId(Integer userId);
}
