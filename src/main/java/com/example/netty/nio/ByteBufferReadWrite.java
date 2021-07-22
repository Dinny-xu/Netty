package com.example.netty.nio;


import java.nio.ByteBuffer;

import static com.example.netty.utils.ByteBufferUtil.debugAll;


public class ByteBufferReadWrite {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);
        debugAll(buffer);
        buffer.put(new byte[]{0x62, 0x63, 0x64});
        debugAll(buffer);
/*
        * +--------+-------------------- all ------------------------+----------------+
    position: [1], limit: [10]
         +-------------------------------------------------+
         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
+--------+-------------------------------------------------+----------------+
|00000000| 61 00 00 00 00 00 00 00 00 00                   |a.........      |
+--------+-------------------------------------------------+----------------+
+--------+-------------------- all ------------------------+----------------+
    position: [4], limit: [10]
         +-------------------------------------------------+
         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
+--------+-------------------------------------------------+----------------+
|00000000| 61 62 63 64 00 00 00 00 00 00                   |abcd......      |
+--------+-------------------------------------------------+----------------+
*/
        //System.out.println("buffer.get() = " + buffer.get()); // = 0
        buffer.flip(); // 切换至读模式
        System.out.println("buffer.get() = " + buffer.get()); // = 97 将十六进制61 转换为十进制
        debugAll(buffer); // 切换至写模式
        buffer.compact();
        debugAll(buffer);
        buffer.put(new byte[]{0x65, 0x66});
        debugAll(buffer);
    }
}
