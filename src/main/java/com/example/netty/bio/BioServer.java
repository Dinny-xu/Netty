package com.example.netty.bio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * bio 应用实例
 */
@Slf4j
@Component
public class BioServer {

    public static void main(String[] args) throws Exception {

        /*
         * 1.创建一个线程池
         * 2.如果有客户端连接，就创建一个新线程，进行通讯
         */
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        //创建socket
        final ServerSocket serverSocket = new ServerSocket(6666);
        log.info("服务器已启动....");

        while (true) {
            final Socket socket = serverSocket.accept();
            log.info("连接到一个客户端");

            //创建线程--开始通讯
            newCachedThreadPool.execute(new Runnable() {
                public void run() {
                    handle(socket);
                }

                private void handle(Socket socket) {
                    //通过socket获取输入流
                    try {
                        log.info("线程信息ID:{}, 名称:{}",Thread.currentThread().getId(),Thread.currentThread().getName());
                        byte[] bytes = new byte[1024];
                        InputStream inputStream = socket.getInputStream();

                        //循环读取客户端发送的数据
                        while (true) {
                            log.info("线程信息ID:{}, 名称:{}",Thread.currentThread().getId(),Thread.currentThread().getName());
                            int read = inputStream.read(bytes);
                            if (read != -1) {
                                log.info(new String(bytes, 0, read));
                            }else {
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        log.info("关闭和cline的连接");
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
