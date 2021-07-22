package com.example.netty.nio;


import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.example.netty.utils.ByteBufferUtil.debugAll;

/**
 * Gathering Writes
 */
public class ByteBufferGatheringWrites {

    public static void main(String[] args) {

        try {
            RandomAccessFile file = new RandomAccessFile("3parts.txt", "rw");
            FileChannel channel = file.getChannel();

            ByteBuffer d = ByteBuffer.allocate(4);
            ByteBuffer e = ByteBuffer.allocate(4);
            channel.position(11);

            d.put(new byte[]{'f', 'o', 'u', 'r'});
            e.put(new byte[]{'f', 'i', 'v', 'e'});
            d.flip();
            e.flip();

            debugAll(d);
            debugAll(e);
            channel.write(new ByteBuffer[]{d, e});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
