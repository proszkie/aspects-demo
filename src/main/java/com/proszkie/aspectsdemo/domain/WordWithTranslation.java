package com.proszkie.aspectsdemo.domain;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Optional;

@Value
@Builder
public class WordWithTranslation {

    @NonNull
    Word word;

    Word translation;

    public Optional<Word> getTranslation(){
        return Optional.ofNullable(translation);
    }
}
