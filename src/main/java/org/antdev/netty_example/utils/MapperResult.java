package org.antdev.netty_example.utils;

public abstract class MapperResult<T, U> {
    private final T topic;
    private final U payload;

    public MapperResult(T topic, U payload) {
        this.topic = topic;
        this.payload = payload;
    }

    public T getTopic() {
        return topic;
    }

    public U getPayload() {
        return payload;
    }

    public abstract void process();
}
