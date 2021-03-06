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
    private final WordsRepository wordsRepository;

    public final Collection<WordWithTranslation> translate(final Collection<Word> words){
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final List<WordWithTranslation> wordsWithTranslations = words.stream()
                .map(this::getWordWithTranslation)
                .filter(wwt -> wwt.getTranslation().isPresent())
                .collect(Collectors.toList());

        stopWatch.stop();
        log.info("Words translated in {} ms. Pack size: {}", stopWatch.getTotalTimeMillis(), words.size());

        return wordsWithTranslations;
    }

    private WordWithTranslation getWordWithTranslation(final Word word) {
        final Word translation = wordsRepository.getTranslationForWordId(word.getId())
                .orElseGet(() -> translateAndCache(word));

        return WordWithTranslation.builder()
                .word(word)
                .translation(translation)
                .build();
    }

    private Word translateAndCache(final Word word) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final Word t = wordsTranslator.translate(word);

        stopWatch.stop();
        log.info("Word with id {} has been translated in {} ms.", word.getId(), stopWatch.getTotalTimeMillis());
        cache(word, t);
        return t;
    }

    private void cache(final Word word, final Word t) {
        if (!t.equals(Word.NULL_WORD)) {
            wordsRepository.saveWordWithTranslation(word, t);
        }
    }
}
