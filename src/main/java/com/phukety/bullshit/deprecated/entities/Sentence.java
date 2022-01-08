package com.phukety.bullshit.deprecated.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * 句子，套话，连接句 TODO Polite
 */
@Getter
@Setter
public class Sentence {
    /** 内容 */
    private String content;
    /** 类型 TODO 递进、转折？ */
    private String type;
}
