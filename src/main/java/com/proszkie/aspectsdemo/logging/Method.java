package com.proszkie.aspectsdemo.logging;

import java.util.Collection;

public enum Method {
    SIZE{
        @Override
        public Object invoke(final Object o) {
            if(o instanceof Collection){
                return ((Collection<?>) o).size();
            }else {
                throw new RuntimeException("Only Collection can be affected by SIZE operation");
            }
        }
    };

    public abstract Object invoke(Object object);
}
