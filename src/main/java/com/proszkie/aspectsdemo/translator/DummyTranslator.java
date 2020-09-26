package com.proszkie.aspectsdemo.translator;

import com.proszkie.aspectsdemo.domain.Word;
import com.proszkie.aspectsdemo.domain.WordsTranslator;

class DummyTranslator implements WordsTranslator {

    @Override
    public Word translate(final Word word) {

        try {
            sleepRandomTime();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        return word;
    }

    private void sleepRandomTime() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 1000));
    }
}
