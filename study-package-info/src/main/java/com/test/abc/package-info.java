/**
 * @author raoshihong
 * @date 2020-05-24 11:12
 */

//@MyAnnotation //这个注解放到这,则表示这个包下的所有类都带有这个注解
@Deprecated
package com.test.abc;

//定义当前包下都公共类和公共常量,这样只有在这个包下可以直接使用
class PkgClass{
    public void test(){
    }
}
//包常量，只运行包内访问，适用于分“包”开发
class PkgConst{
    static final String PACAKGE_CONST="ABC";
}