package com.phukety.bullshit.deprecated.generators;

import com.phukety.bullshit.deprecated.AbstractThreeStageGenerator;
import com.phukety.bullshit.deprecated.entities.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * 三段式评语 - 适配器
 * <p>
 * 提供默认实现
 */
public class ThreeStageGeneratorAdapter extends AbstractThreeStageGenerator {
    @Override
    protected String summary() {
        return "";
    }

    @Override
    protected List<String> specifics() {
        return new ArrayList<>();
    }

    @Override
    protected String conclusion() {
        return "";
    }

    @Override
    protected List<Word> terms() {
        return new ArrayList<>();
    }
}
