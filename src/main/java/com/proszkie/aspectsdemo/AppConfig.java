package com.proszkie.aspectsdemo;

import com.proszkie.aspectsdemo.domain.WordsFacade;
import com.proszkie.aspectsdemo.domain.WordsRepository;
import com.proszkie.aspectsdemo.domain.WordsTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppConfig {

    @Bean
    WordsFacade wordsFacade(final WordsTranslator wordsTranslator, final WordsRepository wordsRepository) {
        return new WordsFacade(wordsTranslator, wordsRepository);
    }
}
