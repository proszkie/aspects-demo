package com.proszkie.aspectsdemo.db;

import com.proszkie.aspectsdemo.domain.LanguagesRepository;
import com.proszkie.aspectsdemo.domain.WordsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DbConfig {

    @Bean
    WordsRepository wordsRepository() {
        return new InMemoryWordsRepository();
    }

    @Bean
    LanguagesRepository languagesRepository() {
        return new InMemoryLanguagesRepository();
    }
}
