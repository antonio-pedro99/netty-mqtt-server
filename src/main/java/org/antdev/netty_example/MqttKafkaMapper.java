/***
 *
 *
 *
 * ***/
package org.antdev.netty_example;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import org.antdev.netty_example.utils.MappingRunnable;

public class MqttKafkaMapper {
    private static MqttKafkaMapper instance;

    // Private constructor to prevent instantiation from outside the class
    private MqttKafkaMapper() {
    }

    public static synchronized MqttKafkaMapper getInstance() {
        if (instance == null) {
            instance = new MqttKafkaMapper();
        }
        return instance;
    }

    public void map(ChannelHandlerContext ctx, MqttPublishMessage mqttMessage) throws InterruptedException {
        // Mapping logic implementation
        MappingRunnable runnable = new MappingRunnable(ctx, mqttMessage);
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
