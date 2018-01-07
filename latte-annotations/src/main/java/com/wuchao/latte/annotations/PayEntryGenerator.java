package com.wuchao.latte.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: wuchao
 * @date: 2017/12/26 22:56
 * @desciption:
 */

@Target(ElementType.TYPE)//告诉编译器注解是用在类上面的，不是方法和属性
@Retention(RetentionPolicy.SOURCE)//编译器处理注解时是在源码阶段处理的，
// 即打包成apk或者运行时不再使用了，对性能没有影响
public @interface PayEntryGenerator {

    String packageName();

    Class<?> payEntryTemplate();
}
