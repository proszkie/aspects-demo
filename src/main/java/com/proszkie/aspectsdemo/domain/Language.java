package com.proszkie.aspectsdemo.domain;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class Language {

    public static Language NULL_LANGUAGE = new Language(UUID.fromString("00000000-0000-0000-0000-000000000000"), "");

    @NonNull
    UUID id;

    @NonNull
    String name;
}
