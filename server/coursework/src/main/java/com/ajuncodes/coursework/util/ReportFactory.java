package com.ajuncodes.coursework.util;

import com.ajuncodes.coursework.model.report.*;

public class ReportFactory {

    private static ReportFactory instance;

    private ReportFactory() {}

    public static ReportFactory getInstance() {
        if (instance == null) {
            instance = new ReportFactory();
        }
        return instance;
    }

    public Reportable createReport(Reports reports) {
        return switch (reports) {
            case MULTI -> new MultyReport();
            case CUSTOM -> new CustomReport();
            case SINGLE -> new SingleReport();
        };
    }
}
