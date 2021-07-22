package com.example.netty.nio;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Nio 文件通道读写 文件拷贝
 */
public class NioFileChannelReadAndWrite {

    public static void main(String[] args) throws Exception{

        FileInputStream inputStream = new FileInputStream("1.txt");
        FileChannel inputStreamChannel = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream("2.txt");
        FileChannel outputStreamChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {

            byteBuffer.clear();
            int read = inputStreamChannel.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            outputStreamChannel.write(byteBuffer);
        }
        inputStream.close();
        outputStream.close();
    }

}
