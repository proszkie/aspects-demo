package com.proszkie.aspectsdemo.web;

import com.proszkie.aspectsdemo.domain.WordWithTranslation;
import lombok.NonNull;
import lombok.Value;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Value
class WordsWithTranslationsDto {

    @NonNull
    List<WordWithTranslationDto> wordsWithTranslations;

    static WordsWithTranslationsDto from(final Collection<WordWithTranslation> wordsWithTranslations) {
        final List<WordWithTranslationDto> wordWithTranslationDtos = wordsWithTranslations.stream()
                .map(WordWithTranslationDto::from)
                .collect(Collectors.toList());

        return new WordsWithTranslationsDto(wordWithTranslationDtos);
    }
}
