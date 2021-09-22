package test.netty.demo.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class MyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        // 解码器: 基于换行
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 基于指定符号【换行符，这样功能等同于LineBasedFrameDecoder】
//        channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, false, Delimiters.lineDelimiter()));
        //基于最大长度
//        channel.pipeline().addLast(new FixedLengthFrameDecoder(4));
        // 解码转string 注意编码格式
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        //在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyServerHandler());
    }
}
