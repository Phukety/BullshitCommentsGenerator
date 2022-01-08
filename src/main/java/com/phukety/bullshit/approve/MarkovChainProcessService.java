package com.phukety.bullshit.approve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.phukety.bullshit.utils.FileUtils;
import com.phukety.bullshit.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 马尔科夫链构建处理器
 */
@Slf4j
@Service
public class MarkovChainProcessService {
    private static final String START_TAG = "START";
    private static final String END_TAG = "END";
    private static final String SENTENCE_SPLIT = "[。，、；, ”（）：\\\\.]";
    private static final String DEFAULT_LEARN_FILE = "learn.txt";
    private static Map<String, List<String>> DICT = new HashMap<>();

    @Value("${config.dict-path:dict.txt}")
    private String dictFilePath;
    @Value("${config.learn-path:learn.txt}")
    private String learnFilePath;

    @PostConstruct
    private void init() {
        log.info("字典文件路径：{}", dictFilePath);
        List<String> dictTxt = FileUtils.readFile(dictFilePath, false);
        if (dictTxt != null && !dictTxt.isEmpty()) {
            log.info("加载已存在的字典文件");
            String dict = dictTxt.get(0);
            DICT = JSON.parseObject(dict, new TypeReference<Map<String, List<String>>>() {
            });
        } else {
            log.info("不存在字典文件，通过学习生成字典");
            log.info("字典学习文件路径：{}", learnFilePath);
            DICT.put(START_TAG, new ArrayList<>());
            DICT.put(END_TAG, new ArrayList<>());
            // 学习文本
            List<String> studySentences = FileUtils.readFile(learnFilePath, true);
            if (studySentences == null || studySentences.isEmpty()) {
                log.info("不存在学习文件：{},加载默认学习文件", learnFilePath);
                studySentences = FileUtils.readResourceFile(DEFAULT_LEARN_FILE);
            }
            log.info("学习文本:{}", JSON.toJSONString(studySentences));
            learn(studySentences);
        }
    }


    /**
     * 更新字典
     *
     * @param words 新的、分词后的、一个句子的词组
     */
    private void update(List<String> words) {
        List<String> start = DICT.get(START_TAG);
        if (!start.contains(words.get(0))) {
            start.add(words.get(0));
        }
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            List<String> wordDict = DICT.get(word);
            if (wordDict == null) {
                wordDict = new ArrayList<>();
            }
            if (i + 1 < words.size()) {
                String nextWord = words.get(i + 1);
                if (!wordDict.contains(nextWord)) {
                    wordDict.add(nextWord);
                    DICT.put(word, wordDict);
                }
            } else {
                if (!DICT.get(END_TAG).contains(word)) {
                    DICT.get(END_TAG).add(word);
                }
            }
        }
    }

    /**
     * 将生成的字典保存到文件中
     */
    private void saveToFile() {
        log.info("字典保存,路径:{}", dictFilePath);
        FileUtils.saveFile(dictFilePath, JSON.toJSONString(DICT));
    }

    /**
     * 单句子学习,添加到字典
     *
     * @param sentence
     */
    public void learn(String sentence) {
        if (!StringUtils.isEmpty(sentence)) {
            List<String> splitSentences = Arrays.asList(sentence.replaceAll(SENTENCE_SPLIT, " ").split(" "));
            update(WordParticiple.part(splitSentences));
        }
        printDict();
        saveToFile();
    }

    /**
     * 多句子学习,添加到字典
     *
     * @param sentences
     */
    public void learn(List<String> sentences) {
        if (sentences != null) {
            for (String sentence : sentences) {
                List<String> splitSentences = Arrays.asList(sentence.replaceAll(SENTENCE_SPLIT, " ").split(" "));
                update(WordParticiple.part(splitSentences));
            }
        }
        printDict();
        saveToFile();
    }


    /**
     * 打印字典
     */
    public String printDict() {
        return JSON.toJSONString(DICT);
    }


    public Map<String, List<String>> getDict() {
        return DICT;
    }

    //    public static void main(String[] args) {
//        // 学习文本
//        List<String> studySentences = FileUtils.readFile(Objects.requireNonNull(SentenceLearnService.class.getClassLoader().getResource(STUDY_FILE)).getPath());
//        if (studySentences == null) {
//            return;
//        }
//        learn(studySentences);
//    }
}
