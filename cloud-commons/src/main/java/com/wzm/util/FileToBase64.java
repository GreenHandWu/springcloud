package com.wzm.util;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class FileToBase64 {
    public static void main(String[] args) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("D:\\小微客户数据问题.xlsx"));
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] encode = encoder.encode(bytes);
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\小微客户数据问题.txt");
            fileOutputStream.write(encode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
