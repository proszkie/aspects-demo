package com.proszkie.aspectsdemo.web;

import com.proszkie.aspectsdemo.domain.LanguagesRepository;
import com.proszkie.aspectsdemo.domain.Word;
import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
class WordDto {

    @NonNull
    UUID id;

    @NonNull
    String value;

    @NonNull
    UUID languageId;

    static WordDto from(final Word word) {
        return new WordDto(word.getId(), word.getRaw(), word.getLanguage().getId());
    }

    Word mapToWord(final LanguagesRepository languagesRepository) {
        return languagesRepository.getById(languageId)
                .map(language -> new Word(id, value, language))
                .orElseThrow(() -> new RuntimeException("Language with given id not found. Given id: " + languageId));
    }
}
