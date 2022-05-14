package com.ajuncodes.coursework.service;

import com.ajuncodes.coursework.model.PesonalInfKotkovets;

import java.util.List;

public interface PersonalInfService {
    public boolean savePersonalInf(PesonalInfKotkovets pesonalInfKotkovets, int userId);
    public List<PesonalInfKotkovets> getAll();
    public boolean deleteInfo(Integer id);
    PesonalInfKotkovets editInfo(Integer id, PesonalInfKotkovets pesonalInfKotkovets);
}
