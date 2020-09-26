package com.proszkie.aspectsdemo.logging;

import com.proszkie.aspectsdemo.domain.Word;

import java.util.Collection;

public enum Method {
    COLLECTION_SIZE {
        @Override
        public Object invoke(final Object o) {
            if (o instanceof Collection) {
                return ((Collection<?>) o).size();
            } else {
                throw new RuntimeException("Only Collection can be affected by SIZE operation");
            }
        }
    },

    GET_WORD_ID {
        @Override
        public Object invoke(final Object o) {
            if(o instanceof Word){
                return ((Word) o).getId();
            } else {
                throw new RuntimeException("Only Word can be affected by GET_WORD_ID operation");
            }
        }
    };


    public abstract Object invoke(Object o);
}
