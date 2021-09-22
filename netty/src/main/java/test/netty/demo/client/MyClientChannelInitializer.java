package test.netty.demo.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class MyClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
       // 基于换行符号
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        channel.pipeline().addLast(new MyClientInMsgHandler());
        channel.pipeline().addLast(new MyClientOutMsgHandler());//消息出站处理器，在Client发送消息时候会触发此处理器
    }
}
