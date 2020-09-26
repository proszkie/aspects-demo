package com.proszkie.aspectsdemo.domain;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class Word {

    public static Word NULL_WORD = new Word(UUID.fromString("00000000-0000-0000-0000-000000000000"), "", Language.NULL_LANGUAGE);

    @NonNull
    UUID id;

    @NonNull
    String raw;
    
    @NonNull
    Language language;
}
