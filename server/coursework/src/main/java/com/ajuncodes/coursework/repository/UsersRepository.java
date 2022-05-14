package com.ajuncodes.coursework.repository;

import com.ajuncodes.coursework.model.UsersKotkovets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersKotkovets, Integer> {
    UsersKotkovets findByLogin(String login);
}
