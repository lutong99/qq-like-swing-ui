package org.example.test;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 测试我们的File存储, 演示而已
 *
 * @author Lutong99
 */
@SuppressWarnings("resource")
public class TestFile {
    @Test
    public void testName() throws Exception {
        String sourcePath = new String();

        String targetPath = new String();

        FileInputStream fileInputStream = new FileInputStream(new File(sourcePath));

        FileOutputStream fileOutputStream = new FileOutputStream(new File(targetPath));

        byte[] bytes = new byte[1024];
        int length = 0;
        while ((length = fileInputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, length);
        }

    }
}
