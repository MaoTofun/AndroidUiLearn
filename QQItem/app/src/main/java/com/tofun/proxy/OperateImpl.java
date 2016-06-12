package com.tofun.proxy;

/**
 * Created by Administrator on 2016-6-2 0002.
 */
public class OperateImpl implements Operate {


    @Override
    public String method1(String msg) {
        return msg+"------From Method1";
    }

    @Override
    public String method2(String msg) {
        return msg+"------From Method1";
    }
}
