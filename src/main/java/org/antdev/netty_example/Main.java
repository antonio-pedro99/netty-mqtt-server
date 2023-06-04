package org.antdev.netty_example;

import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;

public class Main {

    //would be good to read all the server proprieties from a .yaml file or system named environment.
    static final int PORT = Integer.parseInt(System.getProperty("port", "1883"));

    public static void main(String[] args) {
       try {
           NioEventLoopGroup masterGroup = new NioEventLoopGroup();
           NioEventLoopGroup workerGroup = new NioEventLoopGroup();
           MqttServer server = new MqttServer(PORT, masterGroup, workerGroup, ChannelOption.SO_KEEPALIVE);
           server.start();
       } catch (Exception e){
           System.out.printf(e.getMessage());
       }
    }
}