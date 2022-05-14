package com.ajuncodes.coursework.service;

import com.ajuncodes.coursework.model.DataСomparisonKotkovets;
import com.ajuncodes.coursework.model.PesonalInfKotkovets;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonalInfServiceImplTest {

    @Autowired
    private PersonalInfService service;

    @Test
    @Rollback
    public void testAddInformationAboutExistingPerson() {
        PesonalInfKotkovets inf = new PesonalInfKotkovets();
        inf.setName("Name");
        inf.setLastName("lastName");
        boolean isSaved = service.savePersonalInf(inf, 1);
        Assert.assertTrue(isSaved);
    }

    @Test(expected = Exception.class)
    @Rollback
    public void testAddInformationAboutNotExistingPerson() {
        PesonalInfKotkovets inf = new PesonalInfKotkovets();
        inf.setName("Name");
        boolean isSaved = service.savePersonalInf(inf, 0);
    }
}