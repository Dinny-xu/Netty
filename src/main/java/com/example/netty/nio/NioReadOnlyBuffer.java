package com.example.netty.nio;


import java.nio.ByteBuffer;

/**
 * 将普通buffer 转换为只读
 */
public class NioReadOnlyBuffer {

    public static void main(String[] args) {

        //分配一个新的字节缓冲区。
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        for (int i = 0; i < 64; i++) {
            byteBuffer.put((byte) i);
        }

        byteBuffer.flip();
        //得到一个只读的buffer
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }
        //readOnlyBuffer.put((byte) 100);  buffer 已被设置为只读，再次put 将会报错
    }
}
