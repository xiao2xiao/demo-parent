package com.neno.controller;

import com.neno.utils.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Author: root
 * @Date: 2019/10/19 23:21
 */
@RestController
public class HelloController {

    private static final Logger log = LogManager.getLogger(HelloController.class);


    @RequestMapping("/index")
    public List<String> index() {

        String path = "";
        try {
            /**
             * 获取编译后resources的路径
             */
            path = ClassLoader.getSystemResource("").toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        log.info("=============start==================");
        List<String> fileList = FileUtils.recursiveFiles(path);
        log.info("=============end==================");
        return fileList;
    }

    @RequestMapping("/hello")
    public List<String> hello() {

        String path = "";
        try {
            path = ClassLoader.getSystemResource("").toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        log.info("=============start==================");
        List<String> fileList = FileUtils.fun(path.substring(1));
        log.info("=============end==================");
        return fileList;
    }

    @RequestMapping("/how")
    public List<String> how() {

        String path = null;
        try {
            /**
             * Paths.get(".");该方法可以获取父项目下的所有文件
             */
            path = ClassLoader.getSystemResource("").toURI().getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("=============start==================");
        List<String> fileList = FileUtils.fun2(path);
        log.info("=============end==================");
        return fileList;
    }
}
