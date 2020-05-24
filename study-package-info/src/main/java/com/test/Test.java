package com.test;

import com.test.abc.Client;
import com.test.abc.MyAnnotation;

/**
 * @author raoshihong
 * @date 2020-05-24 11:17
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(Package.getPackage("com.test").isAnnotationPresent(MyAnnotation.class));

        Client client = new Client();
        client.aa();


    }
}
