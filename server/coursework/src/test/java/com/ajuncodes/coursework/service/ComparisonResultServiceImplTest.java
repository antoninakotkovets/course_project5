package com.ajuncodes.coursework.service;

import com.ajuncodes.coursework.model.ComparisonResultKotkovets;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ComparisonResultServiceImplTest {

    @Autowired
    private ComparisonResultService service;

    @Test
    @Rollback
    public void testSavingUserComparisonResult() {
        ComparisonResultKotkovets comparison = new ComparisonResultKotkovets();
        comparison.setTimeComparison(new Date());
        int i = service.saveComparisonResult(comparison, 2);
        Assert.assertTrue(i != 0);
    }

    @Test(expected = Exception.class)
    @Rollback
    public void testTrySavingUserComparisonResultWhenUserNotExists() {
        ComparisonResultKotkovets comparison = new ComparisonResultKotkovets();
        comparison.setTimeComparison(new Date());
        int i = service.saveComparisonResult(comparison, 0);
        Assert.assertTrue(i == 0);
    }

    @Test
    public void testFindUsersComparisonsWhenUserNotExists() {
        List<ComparisonResultKotkovets> userComparisons = service.getUserComparisons(0);
        Assert.assertTrue(userComparisons.isEmpty());
    }

    @Test
    public void testFindUsersComparisonsWhenUserExistsAndHaveComparisons() {
        List<ComparisonResultKotkovets> userComparisons = service.getUserComparisons(2);
        Assert.assertFalse(userComparisons.isEmpty());
    }

    @Test
    public void testGetComparisonsForAdmin() {
        List<ComparisonResultKotkovets> usersComparisons = service.getUsersComparisons();
        Assert.assertFalse(usersComparisons.isEmpty());
    }
}