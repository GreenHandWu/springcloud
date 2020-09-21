package com.wzm.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class Base64ToFile {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("D:\\1.txt");
            byte[] buf = new byte[1024];
            int length = 0;
            StringBuilder buffer = new StringBuilder();
            //循环读取文件内容，输入流中将最多buf.length个字节的数据读入一个buf数组中,返回类型是读取到的字节数。
            //当文件读取到结尾时返回 -1,循环结束。
            while ((length = fileInputStream.read(buf)) != -1) {
                buffer.append(new String(buf, 0, length));
            }
            String stringResult = buffer.toString();
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\1.xlsx");
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] bytes = decoder.decode(stringResult);
            fileOutputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();//强制关闭输入流
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
