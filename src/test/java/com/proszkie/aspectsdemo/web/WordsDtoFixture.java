package com.proszkie.aspectsdemo.web;

import com.proszkie.aspectsdemo.web.WordDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordsDtoFixture {

    static List<WordDto> exampleWordsDtoList(final UUID languageId) {
        return IntStream.range(0, 10)
                .mapToObj(i -> wordWithRandomId(languageId))
                .collect(Collectors.toList());
    }

    private static WordDto wordWithRandomId(final UUID languageId) {
        return new WordDto(UUID.randomUUID(), "random value", languageId);
    }
}
