package com.proszkie.aspectsdemo.domain;

import java.util.Optional;
import java.util.UUID;

public interface LanguagesRepository {
    void save(final Language language);

    Optional<Language> getById(final UUID id);
}
