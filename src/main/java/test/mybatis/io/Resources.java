package test.mybatis.io;

import java.io.InputStream;

public class Resources {
    /**
     * 根据传入的参数获取一个字节输入流
     * @param filePath
     * @return
     */
    public static InputStream getResourceAsStream(String filePath){
        return  Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
