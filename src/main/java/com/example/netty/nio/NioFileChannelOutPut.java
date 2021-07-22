package com.example.netty.nio;


import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * Nio 文件通道输出
 */
public class NioFileChannelOutPut {

    public static void main(String[] args) throws Exception{

        String str = "你好~";

        FileOutputStream outputStream = new FileOutputStream("/Users/dinny-xu/Desktop/01.txt");
        FileChannel channel = outputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();

        //将byteBuffer数据写入channel
        channel.write(byteBuffer);
        outputStream.close();

    }
}
