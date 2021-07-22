package com.example.netty.nio;


import java.nio.IntBuffer;

/**
 * nio Buffer 缓冲区
 */
public class BasicBuffer {


    public static void main(String[] args) {

        //buffer的使用
        //创建一个Buffer 大小为5
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        //如何从buffer 读取数据
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
