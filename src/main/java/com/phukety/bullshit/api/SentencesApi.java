package com.phukety.bullshit.api;

import com.phukety.bullshit.approve.MarkovChainProcessService;
import com.phukety.bullshit.approve.SentenceGenerator;
import com.phukety.bullshit.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 句子生成API
 */
@Slf4j
@RestController
public class SentencesApi {

    private final MarkovChainProcessService markovChainProcess;

    @Autowired
    public SentencesApi(MarkovChainProcessService markovChainProcess) {
        this.markovChainProcess = markovChainProcess;
    }

    @GetMapping("sentence/random")
    public String sentence(String min, String max) {
        log.info("[api:/sentence/random][min:{}][max={}]", min, max);
        if (StringUtils.isEmpty(min) || StringUtils.isEmpty(max)) {
            return SentenceGenerator.sentence(markovChainProcess.getDict());
        }
        return SentenceGenerator.sentence(markovChainProcess.getDict(), Integer.parseInt(min), Integer.parseInt(max));
    }

    @GetMapping("sentence")
    public String sentence(String count) {
        log.info("[api:/sentence][count:{}]", count);
        if (StringUtils.isEmpty(count)) {
            return SentenceGenerator.sentence(markovChainProcess.getDict());
        }
        return SentenceGenerator.sentence(markovChainProcess.getDict(), Integer.parseInt(count));
    }

    @GetMapping("paragraph/random")
    public String paragraph(String min, String max) {
        log.info("[api:/paragraph/random][min:{}][max:{}]", min, max);
        if (StringUtils.isEmpty(min) || StringUtils.isEmpty(max)) {
            return SentenceGenerator.paragraph(markovChainProcess.getDict());
        }
        return SentenceGenerator.paragraph(markovChainProcess.getDict(), Integer.parseInt(min), Integer.parseInt(max));
    }

    @GetMapping("paragraph")
    public String paragraph(String count) {
        log.info("[api:/paragraph][count:{}]", count);
        if (StringUtils.isEmpty(count)) {
            return SentenceGenerator.paragraph(markovChainProcess.getDict());
        }
        return SentenceGenerator.paragraph(markovChainProcess.getDict(), Integer.parseInt(count));
    }


}
