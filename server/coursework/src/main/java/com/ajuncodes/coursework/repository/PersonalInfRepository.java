package com.ajuncodes.coursework.repository;


import com.ajuncodes.coursework.model.PesonalInfKotkovets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfRepository  extends JpaRepository<PesonalInfKotkovets, Integer> {
    PesonalInfKotkovets deleteByUserId(Integer id);
}
