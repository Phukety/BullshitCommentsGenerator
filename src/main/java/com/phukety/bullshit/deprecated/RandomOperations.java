package com.phukety.bullshit.deprecated;

import java.util.List;

/**
 * 对词语、句子的随机操作
 */
public interface RandomOperations {
    /**
     * 词语、句子初始化
     * @return verb
     */
    boolean setup();
    /**
     * 获取一个动词
     * @return verb
     */
    String getVi();

    /**
     * 获取一个名词
     * @return noun
     */
    String getN();

    /**
     * 获取一个短句
     * @return short sentence
     */
    String getSentence();

    /**
     * 获取多个短句
     * @return get nums of short sentences
     */
    List<String> getSentences(int num);
}
