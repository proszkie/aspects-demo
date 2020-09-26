package com.proszkie.aspectsdemo.db;

import com.proszkie.aspectsdemo.domain.Word;
import com.proszkie.aspectsdemo.domain.WordsRepository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryWordsRepository implements WordsRepository {

    final Map<UUID, Word> idToWordMapping = new ConcurrentHashMap<>();
    final Map<UUID, UUID> wordsTranslations = new ConcurrentHashMap<>();

    @Override
    public void saveWordWithTranslation(final Word word, final Word translation) {
        idToWordMapping.put(word.getId(), word);
        idToWordMapping.put(translation.getId(), translation);
        wordsTranslations.put(word.getId(), translation.getId());
    }

    @Override
    public Optional<Word> getTranslationForWordId(final UUID wordId) {
        return Optional.ofNullable(wordsTranslations.get(wordId))
                .map(idToWordMapping::get);
    }
}
