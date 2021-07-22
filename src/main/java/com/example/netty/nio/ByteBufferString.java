package com.example.netty.nio;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static com.example.netty.utils.ByteBufferUtil.debugAll;


/**
 * 字符串与 ByteBuffer 互转
 */
public class ByteBufferString {

    public static void main(String[] args) {

        //1:字符串转为ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put("hello".getBytes());
        debugAll(buffer);

        //2: Charset
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("hello");
        debugAll(buffer1);

        // 3.wrap
        ByteBuffer buffer2 = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer2);

        //4.转为字符串
        String str1 = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println(str1);

        buffer.flip();
        String str2 = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(str2);

    }

}
