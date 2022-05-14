package com.ajuncodes.coursework.service;

import com.ajuncodes.coursework.model.ComparisonResultKotkovets;
import com.ajuncodes.coursework.repository.ComparisonResultRepository;
import com.ajuncodes.coursework.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class ComparisonResultServiceImpl implements ComparisonResultService {

    @Autowired
    private ComparisonResultRepository comparisonResultRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<ComparisonResultKotkovets> getUsersComparisons() {
        return comparisonResultRepository.findAll();
    }

    @Override
    public int saveComparisonResult(ComparisonResultKotkovets comparisonResultKotkovets, int userId) {
        comparisonResultKotkovets.setUser(usersRepository.getById(userId));
        ComparisonResultKotkovets data = comparisonResultRepository.save(comparisonResultKotkovets);
        return data.getId();
    }

    @Override
    public List<ComparisonResultKotkovets> getUserComparisons(Integer userId) {
        return comparisonResultRepository.findAllByUserId(userId);
    }

    @Override
    public boolean saveAllDataInReport() {
        List<ComparisonResultKotkovets> all = comparisonResultRepository.findAll();
        String filePathToSave = "C:\\BSUIR\\CourseWork5\\coursework (1)\\coursework\\";

        try (PrintWriter printWriter = new PrintWriter(filePathToSave + "report.txt")) {
            for (ComparisonResultKotkovets ent : all) {
                printWriter.print("Проверял: " + ent.getUser().getLogin() + "\n");
                printWriter.print("Название сравнения: " + ent.getTimeComparison() + "\n");
                printWriter.print("Время: " + ent.getTimeComparison().toString() + "\n");
                Double truncatedDouble = BigDecimal.valueOf(ent.getPercentComparison())
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue();
                printWriter.print("Процент совпадения: " + truncatedDouble + " %\n");
                printWriter.print("\n\n");
                printWriter.flush();
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean saveAllDataInReportByUser(Integer id) {
        List<ComparisonResultKotkovets> all = comparisonResultRepository.findAllByUserId(id);
        String filePathToSave = "C:\\BSUIR\\CourseWork5\\coursework (1)\\coursework\\";

        try (PrintWriter printWriter = new PrintWriter(filePathToSave + "report_" + id + ".txt")) {
            for (ComparisonResultKotkovets ent : all) {
                printWriter.print("Название сравнения: " + ent.getTimeComparison() + "\n");
                printWriter.print("Время: " + ent.getTimeComparison().toString() + "\n");
                Double truncatedDouble = BigDecimal.valueOf(ent.getPercentComparison())
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue();
                printWriter.print("Процент совпадения: " + truncatedDouble + " %\n");
                printWriter.print("\n\n");
                printWriter.flush();
            }
            if (all.isEmpty()) {
                printWriter.print("Записей не найдено.\n");
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
}
