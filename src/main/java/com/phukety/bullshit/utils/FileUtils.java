package com.phukety.bullshit.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

@Slf4j
public class FileUtils {
    /**
     * 读取json文件
     *
     * @param fileName
     * @return
     */
    public static String readJsonFile(String fileName) {
        String jsonStr;
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 按行读文本，返回字符串数组
     *
     * @param fileName
     * @return
     */
    public static List<String> readFile(String fileName, boolean notCreate) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(fileName);
            boolean created = true;
            if (!file.exists() && !notCreate) {
                created = file.createNewFile();
            }
            if (created) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
                String line;
                while (!StringUtils.isEmpty(line = reader.readLine())) {
                    lines.add(line);
                }
                reader.close();
            }
            return lines;
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 保存文件
     *
     * @param fileName
     * @param content
     */
    public static void saveFile(String fileName, String content) {
        try {
            File file = new File(fileName);
            if (!file.exists() && !file.createNewFile()) {
                return;
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 读取resource目录下文件内容
     *
     * @param resourcePath
     * @return
     */
    public static List<String> readResourceFile(String resourcePath) {
        List<String> lines = new ArrayList<>();
        try {
            ClassPathResource cpr = new ClassPathResource(resourcePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(cpr.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while (!StringUtils.isEmpty(line = reader.readLine())) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return lines;
    }

}
