package com.proszkie.aspectsdemo.translator;

import com.proszkie.aspectsdemo.domain.WordsTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TranslatorConfig {

    @Bean
    WordsTranslator wordsTranslator() {
        return new DummyTranslator();
    }
}
