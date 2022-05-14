package com.ajuncodes.coursework.util;

import com.ajuncodes.coursework.dto.ComparisonResultDto;
import com.ajuncodes.coursework.model.Language;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class ComparisonTextHandlerTest {

    private final static String CORRECT_JAVA_FILE = "src/main/resources/java-tokens.txt";
    private final static String TEXT_A = "Hello world !";
    private final static String TEXT_B = "Hello ! ";
    private final static String TEXT_C = "Hello world text ! ";
    private final static Double DELTA = 0.001;

    private final ComparisonTextHandler textHandler = new ComparisonTextHandler();

    @Test
    public void testReadTokensFromFileShouldReadTokensWhenFileExists() {
        Set<String> tokens = textHandler.readTokensFromFile(CORRECT_JAVA_FILE);
        Assert.assertNotNull(tokens);
    }

    @Test
    public void testCompareTextsShouldCompareTextsWhenTextEquals() {
        ComparisonResultDto comparisonResultDto = textHandler.compareTexts(TEXT_A, TEXT_A, Language.JAVA);
        Assert.assertEquals(comparisonResultDto.getResult(), 100.0,DELTA);
    }

    @Test
    public void testCompareTextsShouldCompareTextsWhenTextDifferByHalf() {
        ComparisonResultDto comparisonResultDto = textHandler.compareTexts(TEXT_A, TEXT_B, Language.JAVA);
        Assert.assertNotEquals(comparisonResultDto.getResult(), 100.0,DELTA);
    }

    @Test
    public void testReadTokensFromFileShouldReadTokenWhenFileExists() {
        Set<String> tokens = textHandler.readTokensFromFile(CORRECT_JAVA_FILE);
        Assert.assertNotNull(tokens);
    }

    @Test
    public void testCompareTextsShouldCompareTextsWhenTextPartlyEquals() {
        ComparisonResultDto comparisonResultDto = textHandler.compareTexts(TEXT_A, TEXT_C, Language.JAVA);
        Assert.assertEquals(comparisonResultDto.getResult(), 87.5,DELTA);
    }

}