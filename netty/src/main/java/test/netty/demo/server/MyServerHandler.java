package test.netty.demo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        //当有客户端链接后，添加到channelGroup通信组
        ChannelHandler.channelGroup.add(ctx.channel());
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本服务端");
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接报告Port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
        String str = "通知客户端链接建立成功" + " " + new Date() + " " + channel.localAddress().getHostString() + "\r\n";
        ByteBuf buf = Unpooled.buffer(str.getBytes("GBK").length);
        buf.writeByte(str.getBytes("GBK").length);
        ctx.writeAndFlush(buf);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //当有客户端链接后，添加到channelGroup通信组
        ChannelHandler.channelGroup.remove(ctx.channel());
        System.out.println("客户端断开链接" + ctx.channel().localAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] msgByte = new byte[buf.readableBytes()];
//        buf.readBytes(msgByte);
//        System.out.print(new Date() + "接收到消息：");
//        System.out.println(new String(msgByte, Charset.forName("GBK")));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
        //通知客户端链消息发送成功｛不需要通过ByteBuf，可以直接发送字符串｝
        String str = "服务端收到：" + new Date() + " " + msg + "\r\n";
        ChannelHandler.channelGroup.writeAndFlush(str);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }
}
