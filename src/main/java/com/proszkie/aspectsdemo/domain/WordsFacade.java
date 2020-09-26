package com.proszkie.aspectsdemo.domain;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
public class WordsFacade {

    private final WordsTranslator wordsTranslator;

    public final Collection<WordWithTranslation> translate(final Collection<Word> words){
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final List<WordWithTranslation> wordsWithTranslations = words.stream()
                .map(word -> WordWithTranslation.builder()
                        .word(word)
                        .translation(wordsTranslator.translate(word))
                        .build())
                .filter(wwt -> wwt.getTranslation().isPresent())
                .collect(Collectors.toList());

        stopWatch.stop();
        log.info("Words translated in {} ms. Pack size: {}", stopWatch.getTotalTimeMillis(), words.size());

        return wordsWithTranslations;
    }
}
