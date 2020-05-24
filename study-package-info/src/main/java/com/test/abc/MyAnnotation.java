package com.test.abc;

/**
 * @author raoshihong
 * @date 2020-05-24 11:15
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 定义只能标注在package上的注解
@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

}
