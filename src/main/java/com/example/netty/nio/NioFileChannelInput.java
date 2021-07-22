package com.example.netty.nio;


import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * Nio 文件通道输入
 */
public class NioFileChannelInput {

    public static void main(String[] args) throws Exception {

        File file = new File("/Users/dinny-xu/Desktop/01.txt");
        FileInputStream inputStream = new FileInputStream(file);

        FileChannel channel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        inputStream.close();
    }
}
