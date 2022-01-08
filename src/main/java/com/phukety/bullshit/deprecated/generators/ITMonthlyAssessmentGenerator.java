package com.phukety.bullshit.deprecated.generators;

import java.util.List;

/**
 * 互联网行业 - 月度考核评估
 */
public class ITMonthlyAssessmentGenerator extends ThreeStageGeneratorAdapter {
    @Override
    protected String summary() {
        return getSentence();
    }

    @Override
    protected List<String> specifics() {
        return super.specifics();
    }
}
