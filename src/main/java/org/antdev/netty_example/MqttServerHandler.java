package org.antdev.netty_example;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.mqtt.*;

import java.nio.charset.Charset;

public class MqttServerHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.printf("Client connected: " + ctx.channel().remoteAddress() + "\n");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MqttMessageType messageType = ((MqttMessage) msg).fixedHeader().messageType();

        if (messageType == MqttMessageType.CONNECT) {
            handleConnectMessage(ctx, (MqttConnectMessage) msg);
        } else if (messageType == MqttMessageType.PUBLISH) {
            MqttPublishMessage message = (MqttPublishMessage) msg;
            System.out.printf("Topic: " + message.variableHeader().topicName() +" \n");
            System.out.printf("Message: " + message.payload().toString(Charset.defaultCharset()));
        } else {
            // Handle other MQTT message types as needed
            System.out.printf(messageType.name()+"\n");
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    private void handleConnectMessage(ChannelHandlerContext ctx, MqttConnectMessage connectMessage) {

        MqttConnAckMessage connAckMessage = MqttMessageBuilders.connAck()
                .sessionPresent(false)
                .returnCode(MqttConnectReturnCode.CONNECTION_ACCEPTED)
                .build();

        System.out.printf("Connected\n");
        ctx.writeAndFlush(connAckMessage);
    }

}