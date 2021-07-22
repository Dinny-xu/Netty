package com.example.netty.nio;


import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer 可让文件直接在内存(堆外内存)修改，操作系统不需要再次拷贝
 */
public class MappedByteBuffer {

    public static void main(String[] args) throws Exception {

        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
        //获取对应通道
        FileChannel channel = randomAccessFile.getChannel();

        /*
         * params:FileChannel.MapMode.READ_WRITE 使用的读写模式
         * params: 0 可以直接修改的起始位置
         * params: 5 是映射到内存的大小(不是索引位置) 将1.txt的多少个字节映射到内存
         * 可以直接修改的范围是0-5
         * 实际类型 DirectByteBuffer
         */
        java.nio.MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'Y');
//        mappedByteBuffer.put(3, (byte) '3');

        randomAccessFile.close();
        System.out.println("修改成功");
    }
}
