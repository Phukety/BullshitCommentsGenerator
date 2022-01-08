package com.phukety.bullshit;

import org.apdplat.word.WordSegmenter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        WordSegmenter.segWithStopWords("初始化分词组件");
        SpringApplication.run(App.class, args);
    }
}
