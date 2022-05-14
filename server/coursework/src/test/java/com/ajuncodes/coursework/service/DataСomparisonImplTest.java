package com.ajuncodes.coursework.service;


import com.ajuncodes.coursework.model.DataСomparisonKotkovets;
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
public class DataСomparisonImplTest {

    @Autowired
    private DataСomparisonService service;

    @Test
    @Rollback
    public void testAddHandledDateWhenComparisonExists() {
        DataСomparisonKotkovets dataСomparisonKotkovets = new DataСomparisonKotkovets();
        dataСomparisonKotkovets.setLanguage("JAVA");
        boolean isSaved = service.saveDataСomparison(dataСomparisonKotkovets, 2);
        Assert.assertTrue(isSaved);
    }

    @Test(expected = Exception.class)
    @Rollback
    public void testAddHandledDateWhenComparisonNotExists() {
        DataСomparisonKotkovets dataСomparisonKotkovets = new DataСomparisonKotkovets();
        boolean isSaved = service.saveDataСomparison(dataСomparisonKotkovets, 0);
    }

}