package com.proszkie.aspectsdemo.web;

import com.proszkie.aspectsdemo.domain.Word;
import com.proszkie.aspectsdemo.domain.WordWithTranslation;
import lombok.Value;

@Value
class WordWithTranslationDto {
    WordDto word;
    WordDto translation;

    static WordWithTranslationDto from(final WordWithTranslation wordWithTranslation) {

        final WordDto word = WordDto.from(wordWithTranslation.getWord());

        final WordDto translation = wordWithTranslation.getTranslation()
                .map(WordDto::from)
                .orElse(WordDto.from(Word.NULL_WORD));

        return new WordWithTranslationDto(word, translation);
    }
}
