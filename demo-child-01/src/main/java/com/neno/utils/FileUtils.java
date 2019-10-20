package com.neno.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: root
 * @Date: 2019/10/19 23:26
 */
public class FileUtils {

    private static final Logger log = LogManager.getLogger(FileUtils.class);

    /**
     * 存储文件列表
     */
    private static List<String> fileList = new ArrayList<>();

    /**
     * 遍历文件/文件夹 - 函数
     *
     * @param path 文件路径
     * @return 这是java 7以前的版本
     */
    public static List<String> recursiveFiles(String path) {
        // 创建 File对象
        File file = new File(path);
        // 取 文件/文件夹
        File files[] = file.listFiles();
        // 对象为空 直接返回
        if (files == null) {
            return null;
        }
        // 目录下文件
        if (files.length == 0) {
            log.info("{},该文件夹下没有文件", path);
        }
        // 存在文件 遍历 判断
        for (File f : files) {
            // 判断是否为 文件夹
            if (f.isDirectory()) {
                log.info("文件夹: {}", f.getAbsolutePath());
                // 为 文件夹继续遍历
                recursiveFiles(f.getAbsolutePath());
                // 判断是否为 文件
            } else if (f.isFile()) {
                String file2 = f.getAbsolutePath();
                log.info("文件: {}", f.getAbsolutePath());
                fileList.add(file2);
            } else {
                log.info("未知错误文件");
            }
        }
        return fileList;
    }

    /**
     * 该方法只能获取一级目录名称、文件、一级目录下文件
     * java 8
     *
     * @param path
     * @return
     */
    public static List<String> fun(String path) {
        List<String> fileList2 = new ArrayList<>();
        Path path1 = Paths.get(path);
        try {
            Files.list(path1).forEach(h -> fileList2.add(String.valueOf(h)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileList2;
    }

    /**
     * 该方法能获取当前目录下的所有文件
     * java 8
     *
     * @param path
     * @return
     */
    public static List<String> fun2(String path) {
        List<String> fileList2 = new ArrayList<>();
        Path path1 = Paths.get(path);
        try {
            Files.walkFileTree(path1, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                        throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {
                    fileList2.add(String.valueOf(file));
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileList2;
    }
}
