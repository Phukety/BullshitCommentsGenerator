package com.phukety.bullshit.approve;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;

/**
 * 分词器
 */
public class WordParticiple {
    /**
     * 单个句子分词
     * @param sentence
     * @return
     */
    public static List<String> part(String sentence) {
        return WordSegmenter.segWithStopWords(sentence).stream().map(Word::getText).collect(Collectors.toList());
    }

    /**
     * 批量句子分词
     * @param sentences
     * @return
     */
    public static List<String> part(List<String> sentences) {
        List<String> wordList = new ArrayList<>();
        for (String sentence : sentences) {
            wordList.addAll(WordSegmenter.segWithStopWords(sentence).stream().map(Word::getText).collect(Collectors.toList()));
        }
        return wordList;
    }
}
