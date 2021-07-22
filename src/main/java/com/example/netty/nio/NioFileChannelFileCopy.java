package com.example.netty.nio;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * Nio 文件通道文件复制-> 图片
 */
public class NioFileChannelFileCopy {

    public static void main(String[] args) throws Exception{

        //创建输入流通道
        FileInputStream inputStream = new FileInputStream("/Users/dinny-xu/Desktop/1.jpeg");
        FileChannel sourceChannel = inputStream.getChannel();

        //创建输出流通道
        FileOutputStream outputStream = new FileOutputStream("/Users/dinny-xu/Desktop/2.jpeg");
        FileChannel destChannel = outputStream.getChannel();

        destChannel.transferFrom(sourceChannel, 0,sourceChannel.size());

        sourceChannel.close();
        destChannel.close();
        inputStream.close();
        outputStream.close();
    }
}
