package com.example.netty.nio;


import java.nio.ByteBuffer;

/**
 * 类型化get-put
 */
public class NioBufferPutGet {


    public static void main(String[] args) {

        ByteBuffer byteBuffer =  ByteBuffer.allocate(64);

        //类型化方式放入数据
        byteBuffer.putInt(100);
        byteBuffer.putLong(9L);
        byteBuffer.putChar('好');
        byteBuffer.putShort((short) 4);

        byteBuffer.flip();

        //取出的顺序必须与放入的顺序一致，否则类型转换失败
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
    }
}
