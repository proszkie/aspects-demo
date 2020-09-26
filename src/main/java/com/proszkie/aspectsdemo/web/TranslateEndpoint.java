package com.proszkie.aspectsdemo.web;

import com.proszkie.aspectsdemo.domain.LanguagesRepository;
import com.proszkie.aspectsdemo.domain.Word;
import com.proszkie.aspectsdemo.domain.WordsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
class TranslateEndpoint {

    @Autowired
    private WordsFacade wordsFacade;

    @Autowired
    private LanguagesRepository languagesRepository;

    @PostMapping(value = "/translate", produces = "application/json")
    WordsWithTranslationsDto translate(final @RequestBody List<WordDto> wordsToTranslate) {
        final List<Word> words = wordsToTranslate.stream()
                .map(wordDto -> wordDto.mapToWord(languagesRepository))
                .collect(Collectors.toList());

        return WordsWithTranslationsDto.from(wordsFacade.translate(words));
    }
}
