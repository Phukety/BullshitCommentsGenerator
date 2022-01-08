package com.phukety.bullshit.deprecated;

import com.alibaba.fastjson.JSON;
import com.phukety.bullshit.deprecated.entities.Word;
import com.phukety.bullshit.deprecated.entities.Sentence;
import com.phukety.bullshit.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 抽象生成类
 */
public abstract class AbstractGenerator implements Generator, RandomOperations {
    protected static final String COMMA = "，";
    protected static final String PERIOD = "。";
    /**
     * 名词文件
     */
    protected static String BASE_WORDS_N_FILE;
    /**
     * 动词文件
     */
    protected static String BASE_WORDS_VI_FILE;
    /**
     * 套话短语
     */
    protected static String BASE_SENTENCES_FILE;

    protected List<Word> baseWordsN;
    protected List<Word> baseWordsVI;

    protected List<Sentence> baseSentences;

    static {
        BASE_WORDS_N_FILE = Objects.requireNonNull(AbstractGenerator.class.getClassLoader().getResource("deprecated/base-words-n.json")).getPath();
        BASE_WORDS_VI_FILE = Objects.requireNonNull(AbstractGenerator.class.getClassLoader().getResource("deprecated/base-words-vi.json")).getPath();
        BASE_SENTENCES_FILE = Objects.requireNonNull(AbstractGenerator.class.getClassLoader().getResource("deprecated/base-sentences.json")).getPath();
    }

    /**
     * 初始化本地词库
     * 读取词库文件中type=0的word
     *
     * @return 初始化结果: true成功
     */
    public boolean setup() {
        baseWordsN = retainNoneDirection(Objects.requireNonNull(JSON.parseArray(FileUtils.readJsonFile(BASE_WORDS_N_FILE), Word.class)));
        baseWordsVI = retainNoneDirection(Objects.requireNonNull(JSON.parseArray(FileUtils.readJsonFile(BASE_WORDS_VI_FILE), Word.class)));

        baseSentences = Objects.requireNonNull(JSON.parseArray(FileUtils.readJsonFile(BASE_SENTENCES_FILE), Sentence.class));
        return true;
    }

    @Override
    public String getN() {
        return baseWordsN.get(getRandom(baseWordsN.size())).getContent();
    }

    @Override
    public String getVi() {
        return baseWordsVI.get(getRandom(baseWordsVI.size())).getContent();
    }

    @Override
    public String getSentence() {
        return baseSentences.get(getRandom(baseSentences.size())).getContent();
    }

    @Override
    public List<String> getSentences(int num) {
        List<String> sentences = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            sentences.add(getSentence());
        }
        return sentences;
    }

    /**
     * 保留Direction.NONE的词
     *
     * @param words 词列表
     * @return type=Direction.NONE的词列表
     */
    private List<Word> retainNoneDirection(List<Word> words) {
        return words.stream().filter(Word::isNonePos).collect(Collectors.toList());
    }

    private int getRandom(int length) {
        return new Random().nextInt(length);
    }

    /**
     * 添加术语,行业词汇
     *
     * @return 特有的术语列表
     */
    protected abstract List<Word> terms();
}
