package com.phukety.bullshit.api;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.phukety.bullshit.approve.MarkovChainProcessService;
import com.phukety.bullshit.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典API
 */
@Slf4j
@RestController
@RequestMapping("/dict")
public class DictApi {

    private final MarkovChainProcessService markovChainProcess;

    @Autowired
    public DictApi(MarkovChainProcessService markovChainProcess) {
        this.markovChainProcess = markovChainProcess;
    }


    @GetMapping("/print")
    public String dict(String type) {
        log.info("[api:/dict/print][type:{}]", type);
        if (StringUtils.isEmpty(type) || !"json".equalsIgnoreCase(type)) {
            return markovChainProcess.getDict().toString();
        }
        return markovChainProcess.printDict();
    }

    @GetMapping("/update")
    public String updateDict(String sentence) {
        log.info("[api:/dict/update][sentence:{}]", sentence);
        markovChainProcess.learn(sentence);
        return markovChainProcess.printDict();
    }


    @GetMapping("/updateBatch")
    public String updateDict(@RequestBody List<String> sentences) {
        log.info("[api:/dict/updateBatch][sentences:{}]", JSON.toJSONString(sentences));
        markovChainProcess.learn(sentences);
        return markovChainProcess.printDict();
    }
}
