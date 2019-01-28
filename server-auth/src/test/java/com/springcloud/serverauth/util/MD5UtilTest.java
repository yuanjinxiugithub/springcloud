package com.springcloud.serverauth.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author: YuanJinXiu
 * @Description: ${description}
 * @Date: 2019/1/22 15:17
 * @Version: 1.0
 */
public class MD5UtilTest {


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void encrypt() {
        String str = MD5Util.encrypt("123");
        System.out.println(str);
    }

    @Test
    public void decrypt() {
        String str = null;
        try {
            str = MD5Util.decrypt("mIn2hWCinJrueECVIioxoz12LHIm8gpu");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }
}