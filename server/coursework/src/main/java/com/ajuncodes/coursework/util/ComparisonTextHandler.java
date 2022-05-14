package com.ajuncodes.coursework.util;

import com.ajuncodes.coursework.dto.ComparisonResultDto;
import com.ajuncodes.coursework.model.Language;
import com.ajuncodes.coursework.model.report.Reports;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ComparisonTextHandler {

    private final static String ABS_PATH = "C:\\BSUIR\\CourseWork5\\coursework (1)\\coursework\\";
    private final static String JAVA_FILE = ABS_PATH + "src\\main\\resources\\java-tokens.txt";
    private final static String PYTHON_FILE = ABS_PATH + "src\\main\\resources\\python-tokens.txt";
    private final static String C_FILE = ABS_PATH + "src\\main\\resources\\c++-tokens.txt";


    private Set<String> languageTokens = new HashSet<>();

    public ComparisonResultDto compareTexts(String firstText, String secondText, Language language) {
        Set<String> firstTextSet = transferTextInSet(firstText);
        Set<String> secondTextSet = transferTextInSet(secondText);

        if (firstText.hashCode() == secondText.hashCode()) {
            ComparisonResultDto obj = new ComparisonResultDto();
            obj.setResult(100.0);
            obj.setSimilarities(firstTextSet);
            return obj;
        }

        this.languageTokens = readTokensFromFile(determineLangFilePath(language));

        return calculateTextsSimilarities(firstTextSet, secondTextSet);
    }

    public Set<String> readTokensFromFile(String path) {

        Set<String> tokens = new HashSet<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String str;
            while ((str = bf.readLine()) != null) {
                tokens.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokens;
    }

    private String determineLangFilePath(Language lang) {
        return switch (lang) {
            case JAVA -> JAVA_FILE;
            case PYTHON -> PYTHON_FILE;
            case C -> C_FILE;
        };
    }

    private Set<String> transferTextInSet(String text) {
        String[] elements = text.toLowerCase().split(" ");
        return Arrays.stream(elements).collect(Collectors.toSet());
    }

    private ComparisonResultDto calculateTextsSimilarities(Set<String> firstText, Set<String> secondText) {
        Set<String> uniques = new HashSet<>() ;
        Set<String> duplicates = new HashSet<>() ;

        firstText.removeAll(languageTokens);
        secondText.removeAll(languageTokens);

        for (String s: firstText) {
            if (!uniques.add(s)) {
                duplicates.add(s);
            }
        }

        for (String s: secondText) {
            if (!uniques.add(s)) {
                duplicates.add(s);
            }
        }

//        duplicates.addAll(firstText);
//        duplicates.addAll(secondText);

        int textsTokensSize = firstText.size() + secondText.size();

        double similarityIndexResultFirstText = (textsTokensSize - duplicates.size()* 1.0)/firstText.size();
        double similarityIndexResultSecondText = (textsTokensSize - duplicates.size()* 1.0)/secondText.size();
        ComparisonResultDto obj = new ComparisonResultDto();
        obj.setResult((100 * (similarityIndexResultFirstText + similarityIndexResultSecondText) / 2) % 100);
        obj.setSimilarities(duplicates);

        return obj;
    }

    private void usefulFunction() {
        ReportFactory reportFactory = ReportFactory.getInstance();
        reportFactory.createReport(Reports.CUSTOM);
    }
}
