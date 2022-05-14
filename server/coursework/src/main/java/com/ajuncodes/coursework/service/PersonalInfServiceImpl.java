package com.ajuncodes.coursework.service;

import com.ajuncodes.coursework.model.PesonalInfKotkovets;
import com.ajuncodes.coursework.repository.PersonalInfRepository;
import com.ajuncodes.coursework.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalInfServiceImpl implements PersonalInfService{

    @Autowired
    private PersonalInfRepository personalInfRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public boolean savePersonalInf(PesonalInfKotkovets pesonalInfKotkovets, int userId) {
        pesonalInfKotkovets.setUser(usersRepository.getById(userId));
        personalInfRepository.save(pesonalInfKotkovets);
        return true;
    }

    @Override
    public List<PesonalInfKotkovets> getAll() {
        return personalInfRepository.findAll();
    }

    @Override
    public boolean deleteInfo(Integer id) {
        personalInfRepository.deleteById(id);
        return true;
    }

    @Override
    public PesonalInfKotkovets editInfo(Integer id, PesonalInfKotkovets pesonalInfKotkovets) {
        PesonalInfKotkovets found = personalInfRepository.getById(id);
        found.setGender(pesonalInfKotkovets.getGender());
        found.setLastName(pesonalInfKotkovets.getLastName());
        found.setSurname(pesonalInfKotkovets.getSurname());
        found.setName(pesonalInfKotkovets.getName());

        return personalInfRepository.save(found);
    }

}
