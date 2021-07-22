package com.example.netty.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import static com.example.netty.utils.ByteBufferUtil.debugAll;


/**
 * Scattering Reads
 */
public class ByteBufferScatteringReads {

    public static void main(String[] args) {

        try {
            RandomAccessFile file = new RandomAccessFile("3parts.txt", "rw");
            FileChannel channel = file.getChannel();

            ByteBuffer a = ByteBuffer.allocate(3);
            ByteBuffer b = ByteBuffer.allocate(3);
            ByteBuffer c = ByteBuffer.allocate(5);

            channel.read(new ByteBuffer[]{a, b, c});
            a.flip();
            b.flip();
            c.flip();
            debugAll(a);
            debugAll(b);
            debugAll(c);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
