package com.example.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;


/**
 * 文件通道传输
 */
public class FileChannelTransferTo {

    public static void main(String[] args) {

        try {
            FileChannel form = new FileInputStream("data.txt").getChannel();
            FileChannel to = new FileOutputStream("to.txt").getChannel();
            //效率极高 底层会利用操作系统的零拷贝进行优化  PS:最多传输2G数据
            // form.transferTo(0, form.size(), to);

            //优化: 超过2G数据传输
            long size = form.size();

            // left 变量代表还剩余多少字节
            for (long left = size; left > 0;) {
                left -= form.transferTo(0, size, to);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
