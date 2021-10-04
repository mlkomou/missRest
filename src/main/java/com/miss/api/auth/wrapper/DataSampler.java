package com.miss.api.auth.wrapper;

import java.io.Serializable;
import java.util.List;

public abstract class DataSampler<T,E> implements Serializable {

    public abstract List<T> sample(List<T> data, E entity);

    protected int getRandomNumber() {
        return (int) (Math.round(Math.random() * 9) + 1);
    }

}
