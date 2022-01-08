package com.phukety.bullshit.approve;

import java.util.List;
import java.util.Map;

import com.phukety.bullshit.utils.RandomUtils;

/**
 * 句子生成器
 */
public class SentenceGenerator {

    private static final String START_TAG = "START";
    private static final String END_TAG = "END";

    /**
     * 从字典中随机选择对应tag的词语
     *
     * @param tag tag
     * @return 随机词语
     */
    private static String pickOneRandomWord(Map<String, List<String>> dict, String tag) {
        List<String> readyToPickWords = dict.get(tag);
        if (readyToPickWords == null || readyToPickWords.isEmpty()) {
            return "";
        }
        if (readyToPickWords.size() == 1) {
            return readyToPickWords.get(0);
        }
        return readyToPickWords.get(RandomUtils.getRandom(0, readyToPickWords.size() - 1));
    }


    /**
     * 默认随机词语个数(3-10)的句子
     *
     * @return
     */
    public static String sentence(Map<String, List<String>> dict) {
        return sentence(dict, 3, 10);
    }

    /**
     * 随机词语个数的句子
     *
     * @param min 最小
     * @param max 最大
     * @return
     */
    public static String sentence(Map<String, List<String>> dict, int min, int max) {
        if (min <= 0 || min > max) {
            return "";
        }
        return sentence(dict, RandomUtils.getRandom(min, max));
    }

    /**
     * 句子生成
     *
     * @param wordCount 词语个数
     * @return 并不通顺的句子
     */
    public static String sentence(Map<String, List<String>> dict, int wordCount) {
        StringBuilder sentence = new StringBuilder();
        // START词
        String pickedStartWord = pickOneRandomWord(dict, START_TAG);
        sentence.append(pickedStartWord);
        // 获取START词后的第一个词
        String randomWord = pickOneRandomWord(dict, pickedStartWord);
        int count = 1;
        while (!"".equals(randomWord) && count < wordCount) {
            sentence.append(randomWord);
            count += 1;
            randomWord = pickOneRandomWord(dict, randomWord);
        }
        if (count > wordCount) {
            sentence.append(pickOneRandomWord(dict, END_TAG));
        } else {
            sentence.append(randomWord);
        }
        return sentence.toString();
    }

    /**
     * 段落生成,由默认随机个(2-5)句子组成
     *
     * @return 并不通顺的段落
     */
    public static String paragraph(Map<String, List<String>> dict) {
        return paragraph(dict, 2, 5);
    }

    /**
     * 段落生成,由指定随机个句子组成
     *
     * @param min 句子最小个数
     * @param max 句子最大个数
     * @return 并不通顺的段落
     */
    public static String paragraph(Map<String, List<String>> dict, int min, int max) {
        if (min <= 0 || min > max) {
            return "";
        }
        return paragraph(dict, RandomUtils.getRandom(min, max));
    }


    /**
     * 段落生成,由指定个数句子组成
     *
     * @param sentenceCount 词语个数
     * @return 并不通顺的段落
     */
    public static String paragraph(Map<String, List<String>> dict, int sentenceCount) {
        StringBuilder sentences = new StringBuilder();
        for (int i = 0; i < sentenceCount; i++) {
            sentences.append(sentence(dict));
            if (i != sentenceCount - 1) {
                sentences.append("，");
            } else {
                sentences.append("。");
            }
        }
        return sentences.toString();
    }

}
