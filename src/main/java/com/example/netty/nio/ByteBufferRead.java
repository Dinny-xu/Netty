package com.example.netty.nio;

import java.nio.ByteBuffer;

import static com.example.netty.utils.ByteBufferUtil.debugAll;

public class ByteBufferRead {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();

/*      +--------+-------------------- all ------------------------+----------------+
                position: [4], limit: [4]
        +-------------------------------------------------+
                |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        +--------+-------------------------------------------------+----------------+
       |00000000| 61 62 63 64 00 00 00 00 00 00                   |abcd......      |
        +--------+-------------------------------------------------+----------------+*/

        //rewind 从头开始读

       /* buffer.get(new byte[4]);
        debugAll(buffer);
        buffer.rewind();
        System.out.println((char) buffer.get()); // = a*/

        // ps: rewind 和 flip 都会清除 mark 位置

        //mark & rest
        // mark 做一个标记，记录position位置 rest 将position 重置到mark 的位置
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        buffer.mark();
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        buffer.reset();
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());

        // get(i) 不会改变读索引的位置
        System.out.println((char) buffer.get(3));
        debugAll(buffer);

    }
}
