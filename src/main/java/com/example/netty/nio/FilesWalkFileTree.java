package com.example.netty.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 遍历目录
 */
public class FilesWalkFileTree {

    public static void main(String[] args) throws IOException {
        directoryTraversal();//目录遍历
        filterTraversal();//文件筛选
        deleteFolderFile();//遍历删除目录和目录下所有文件
    }

    private static void directoryTraversal() throws IOException {
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();

        Files.walkFileTree(Paths.get("/Users/dinny-xu/study-blog-img"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("====>" + dir);
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });
        System.out.println("dirCount = " + dirCount);//统计文件夹数量
        System.out.println("fileCount = " + fileCount);//统计文件夹内文件数量
    }

    private static void filterTraversal() throws IOException {

        AtomicInteger jarCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();

        Files.walkFileTree(Paths.get("/Users/dinny-xu/study-blog-img"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("====>" + dir);
                jarCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".jpg")) {
                    fileCount.incrementAndGet();
                }
                return super.visitFile(file, attrs);
            }
        });
        System.out.println("jarCount = " + jarCount);//统计文件夹数量
        System.out.println("fileCount = " + fileCount);//统计文件夹内文件数量
    }

    private static void deleteFolderFile() throws IOException {


        Files.walkFileTree(Paths.get("/Users/dinny-xu/Desktop/1"), new SimpleFileVisitor<Path>() {
            //删除文件不走回收站--危险操作
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
    }
}
