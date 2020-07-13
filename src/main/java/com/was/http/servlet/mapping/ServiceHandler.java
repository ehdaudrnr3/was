package com.was.http.servlet.mapping;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceHandler implements InvocationHandler {
	
	private String theString;

    public ServiceHandler (String theString) {
        this.theString = theString;
    }
    
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before method call : " + method.getName());
        Object result = method.invoke(theString, args);
        System.out.println("after method call : " + method.getName());
        return result;
	}
	
//	public static void main (String[] args) {
//
//		ServiceHandler handler = new ServiceHandler("the example string");
//
//        CharSequence o = (CharSequence) Proxy.newProxyInstance(
//        		ServiceHandler.class.getClassLoader(),
//                            new Class[]{CharSequence.class}, handler);
//        System.out.println(o.length());
//        System.out.println(o.subSequence(4, 8));
//    }

}
