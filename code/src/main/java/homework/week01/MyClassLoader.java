package homework.week01;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/*
第一周作业#2
自定义一个 Classloader
加载一个 Hello.xlass 文件 执行 hello 方法
此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件
<p/>
 */
public class MyClassLoader extends ClassLoader {
    // 读取反码类字节
    byte[] getHelloXclass() throws IOException {
        // Hello.xlass 文件所有字节（x=255-x）处理后的文件
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Hello.xlass");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int len = -1;
        byte[] buff = new byte[1024];
        while ((len = inputStream.read(buff)) != -1) {
            outputStream.write(buff, 0, len);
        }
        outputStream.close();
        inputStream.close();
        return outputStream.toByteArray();
    }
    // 获取原始类字节
    private byte[] getHelloClassBytes(byte[] xclass) {
        byte[] xclass2 = new byte[xclass.length];
        int i = 0, len = xclass.length;
        for (; i < len; i++) {
            // b = 255 - b0
            byte b = xclass[i];
            // ~ 按位取反
            byte b0 = (byte) ~b;
            xclass2[i] = b0;
        }
        return xclass2;
    }
    // 类直接保存到文件
    // 通过 javap 手动查看类文件
    static void writeByte2File(String path, byte[] xclass2) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        fileOutputStream.write(xclass2);
        fileOutputStream.close();
    }
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader();
        byte[] xclass = myClassLoader.getHelloXclass();
        byte[] xclass2 = myClassLoader.getHelloClassBytes(xclass);
        Class helloClass = myClassLoader.defineClass("Hello", xclass2, 0, xclass2.length);
        Object hello = helloClass.newInstance();
        Method helloMethod = helloClass.getMethod("hello");
        // Hello, classLoader!
        helloMethod.invoke(hello);
    }
}
