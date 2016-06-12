package com.tofun.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016-6-2 0002.
 */
public class InvokeImpl implements InvocationHandler {
    private Object target;

    public InvokeImpl(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = method.invoke(target,args);

        return obj+"proxy";
    }
}
