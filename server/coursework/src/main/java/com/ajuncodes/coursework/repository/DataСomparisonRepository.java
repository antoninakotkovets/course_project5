package com.ajuncodes.coursework.repository;


import com.ajuncodes.coursework.model.DataСomparisonKotkovets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DataСomparisonRepository  extends JpaRepository<DataСomparisonKotkovets, Integer> {

}
