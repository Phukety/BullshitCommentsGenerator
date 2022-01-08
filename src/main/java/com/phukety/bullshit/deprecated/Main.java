package com.phukety.bullshit.deprecated;

import com.phukety.bullshit.deprecated.generators.ITMonthlyAssessmentGenerator;

public class Main {
    public static void main(String[] args) {
        Generator generator = new ITMonthlyAssessmentGenerator();
        System.out.println(generator.generate());

    }
}
