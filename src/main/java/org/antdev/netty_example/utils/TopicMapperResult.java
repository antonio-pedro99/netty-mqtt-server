package org.antdev.netty_example.utils;

public class TopicMapperResult extends MapperResult<String, Object>{

    public TopicMapperResult(String topic, Object payload) {
        super(topic, payload);
    }

    @Override
    public void process() {

    }
}
