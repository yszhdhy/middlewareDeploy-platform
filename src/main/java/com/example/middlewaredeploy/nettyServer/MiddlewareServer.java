package com.example.middlewaredeploy.nettyServer;

import com.example.middlewaredeploy.handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @project middlewareDeploy
 * @description netty服务端
 * @author capture or new
 * @date 2023/7/18 09:31:31
 * @version 1.0
 */
public class MiddlewareServer {

    public static final Map<String, Channel> channels = new ConcurrentHashMap<>(1024);

    public static void start() throws Exception {

//        UserHandler.execute();

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        // 绑定端口
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline =  socketChannel.pipeline();
                        // 添加http编码解码器
                        pipeline.addLast(new HttpServerCodec())
                                // 支持大数据流
                                .addLast(new ChunkedWriteHandler())
                                // 对http消息做聚合操作，FullHttpRequest、FullHttpResponse
                                .addLast(new HttpObjectAggregator(1024 * 64))
                                // websocket
                                .addLast(new WebSocketServerProtocolHandler("/"))
                                // 自定义的handler
                                .addLast(new WebSocketHandler());

                    }
                });

        bootstrap.bind(9998).sync();
    }
}
