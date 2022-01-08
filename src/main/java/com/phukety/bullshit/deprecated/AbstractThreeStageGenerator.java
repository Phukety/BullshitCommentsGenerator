package com.phukety.bullshit.deprecated;

import com.phukety.bullshit.deprecated.entities.Word;
import com.phukety.bullshit.utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 抽象 - 三段式评语
 * <p>
 * 综述(快速融入团队...) - 内容详情(列举...) - 结论(推荐评优...)
 */
public abstract class AbstractThreeStageGenerator extends AbstractGenerator {

    @Override
    public String generate() {
        if (setup()) {
            baseWordsN.addAll(terms().stream().filter(Word::isNoun).collect(Collectors.toList()));
            baseWordsVI.addAll(terms().stream().filter(Word::isVerb).collect(Collectors.toList()));
        }
        StringBuilder comment = new StringBuilder();
        String tempSummary = summary();
        List<String> tempSpecifics = specifics();
        String tempConclusion = conclusion();

        if (!StringUtils.isEmpty(tempSummary)) {
            comment.append(tempSummary).append(PERIOD);
        }
        if (!tempSpecifics.isEmpty()) {
            comment.append(String.join(COMMA, tempSpecifics)).append(PERIOD);
        }
        if (!StringUtils.isEmpty(tempConclusion)) {
            comment.append(tempConclusion).append(PERIOD);
        }
        return comment.toString();
    }

    /**
     * 综述
     */
    protected abstract String summary();

    /**
     * 内容详情
     */
    protected abstract List<String> specifics();

    /**
     * 结论
     */
    protected abstract String conclusion();
}
