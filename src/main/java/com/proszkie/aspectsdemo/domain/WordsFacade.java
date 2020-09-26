package com.proszkie.aspectsdemo.domain;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class WordsFacade {

    private final WordsTranslator wordsTranslator;

    public final Collection<WordWithTranslation> translate(final Collection<Word> words){
        return words.stream()
                .map(word -> WordWithTranslation.builder()
                        .word(word)
                        .translation(wordsTranslator.translate(word))
                        .build())
                .filter(wwt -> wwt.getTranslation().isPresent())
                .collect(Collectors.toList());
    }
}
