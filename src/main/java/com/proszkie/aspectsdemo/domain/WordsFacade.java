package com.proszkie.aspectsdemo.domain;

import com.proszkie.aspectsdemo.logging.Method;
import com.proszkie.aspectsdemo.logging.TimeMeasurement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
public class WordsFacade {

    private final WordsTranslator wordsTranslator;
    private final WordsRepository wordsRepository;

    @TimeMeasurement(value = "Words translated in {} ms. Pack size: {}", argIndexes = {0}, methods = {Method.COLLECTION_SIZE})
    public Collection<WordWithTranslation> translate(final Collection<Word> words){
        return words.stream()
                .map(this::getWordWithTranslation)
                .filter(wwt -> wwt.getTranslation().isPresent())
                .collect(Collectors.toList());
    }

    private WordWithTranslation getWordWithTranslation(final Word word) {
        final Word translation = wordsRepository.getTranslationForWordId(word.getId())
                .orElseGet(() -> translateAndCache(word));

        return WordWithTranslation.builder()
                .word(word)
                .translation(translation)
                .build();
    }

    @TimeMeasurement(value = "Word with id {} has been translated in {} ms.", argIndexes = {0}, methods = {Method.GET_WORD_ID})
    private Word translateAndCache(final Word word) {
        final Word t = wordsTranslator.translate(word);
        cache(word, t);
        return t;
    }

    private void cache(final Word word, final Word t) {
        if (!t.equals(Word.NULL_WORD)) {
            wordsRepository.saveWordWithTranslation(word, t);
        }
    }
}
