package com.phukety.bullshit.deprecated.entities;


import lombok.Getter;
import lombok.Setter;

/**
 * 词语
 */
@Getter
@Setter
public class Word {
    /**
     * 内容
     */
    private String content;
    /**
     * 词性(partOfSpeech)
     */
    private Pos pos;
    /**
     * 类型
     */
    private Direction type;

    /**
     * 是否是名词词性
     *
     * @return true: noun
     */
    public boolean isNoun() {
        return Pos.NOUN == pos;
    }

    /**
     * 是否是动词词性
     *
     * @return true: verb
     */
    public boolean isVerb() {
        return Pos.VERB == pos;
    }

    /**
     * 不涉及词性
     *
     * @return true: none
     */
    public boolean isNonePos() {
        return Pos.NONE == pos;
    }

}
