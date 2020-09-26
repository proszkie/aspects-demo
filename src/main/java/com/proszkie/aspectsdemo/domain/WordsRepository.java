package com.proszkie.aspectsdemo.domain;

import java.util.Optional;
import java.util.UUID;

public interface WordsRepository {

    void saveWordWithTranslation(final Word word, final Word translation);

    Optional<Word> getTranslationForWordId(final UUID wordId);
}
