package com.example.netty.nio;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * NIO Buffer 数组完成读写操作 Scattering(分散) -- Gathering(聚集)
 */
public class ScatteringAndGathering {

    public static void main(String[] args) throws Exception{

        //使用ServerSocketChannel 和 SocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7777);

        //绑定端口到socket
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建 buffer 数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();
        //假设从客户端接收8个字节
        int messageLength = 8;

        while (true) {
            int byteRead = 0;

            while (byteRead < messageLength) {
                socketChannel.read(byteBuffers);
                byteRead += 1;
                System.out.println("byteRead="+ byteRead);
                //查看当前buffer position 和 limit
                Arrays.stream(byteBuffers).map(buffer -> "position=" + buffer.position() + "limit=" + buffer.limit()).forEach(System.out::println);
            }
            Arrays.asList(byteBuffers).forEach(Buffer::flip);

            //将数据读取显示到客户端
            long byteWrite = 0;
            while (byteWrite < messageLength) {
                long write = socketChannel.write(byteBuffers);
                byteWrite += write;
            }

            Arrays.asList(byteBuffers).forEach(Buffer::clear);
            System.out.println("byteRead=" + byteRead + "byteWrite=" + byteWrite + "messageLength" + messageLength);
        }
    }
}
