package org.example.test;

import org.junit.Test;

/**
 * 测试我们的编码, 主要是为了测试托盘区的乱码问题
 *
 * @author Lutong99
 */
public class TestEncoding {
    @Test
    public void testEncoding() throws Exception {

        String property = System.getProperty("file.encoding");
        System.out.println(property);

        System.out.println("你好");

    }
}
