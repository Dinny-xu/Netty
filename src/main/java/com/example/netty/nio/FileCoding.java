package com.example.netty.nio;

import lombok.SneakyThrows;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileCoding {

    @SneakyThrows
    public static void main(String[] args) {

        //判断文件是否存在
        Path path = Paths.get("data.txt");
        System.out.println("Files.exists(path) = " + Files.exists(path));


        //创建一级目录
        Path directory = Paths.get("d1");

        Path directories = Files.createDirectories(directory);
        System.out.println(directories);

        //创建多级目录
        Path multiLevelDirectory = Paths.get("d1/d2");
        Files.createDirectories(multiLevelDirectory);

        //拷贝文件
        Path source = Paths.get("data.txt");
        Path target = Paths.get("target.txt");
        //Files.copy(source, target); // 如果文件已存在 报异常:  FileAlreadyExistsException
        //开启强制拷贝-> 覆盖
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);


        //移动文件 -> 在移动文件前判断目标文件是否存在,然后再移动
        Path moveSource = Paths.get("move.txt");
        if (Files.exists(moveSource)) {
            Path moveTarget = Paths.get("/Users/dinny-xu/IdeaProjects/Netty/d1/move.txt");
            Files.move(moveSource, moveTarget, StandardCopyOption.ATOMIC_MOVE);
        }


        //删除文件
        Path delTarget = Paths.get("22.txt");
        if (Files.exists(delTarget)) {
            Files.delete(delTarget);
        }

        //删除目录
        Path delDri = Paths.get("21");
        if (Files.exists(delDri)) {
            Files.delete(delDri);
        }

    }

}
