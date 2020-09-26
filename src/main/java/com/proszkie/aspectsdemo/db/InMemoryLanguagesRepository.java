package com.proszkie.aspectsdemo.db;

import com.proszkie.aspectsdemo.domain.Language;
import com.proszkie.aspectsdemo.domain.LanguagesRepository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryLanguagesRepository implements LanguagesRepository {
    final Map<UUID, Language> languages = new ConcurrentHashMap<>();

    @Override
    public void save(final Language language) {
        languages.put(language.getId(), language);
    }

    @Override
    public Optional<Language> getById(final UUID id) {
        return Optional.ofNullable(languages.get(id));
    }
}
