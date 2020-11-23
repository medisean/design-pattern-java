package com.zlm.p01;

// 单例的第一种写法，先 new
public class SingleInstance {

    private static final SingleInstance INSTANCE = new SingleInstance();

    private SingleInstance() {}

    public static SingleInstance getInstance() { return INSTANCE; }

    public static void main(String[] args) {
        SingleInstance m1 = SingleInstance.getInstance();
        SingleInstance m2 = SingleInstance.getInstance();

        System.out.println(m1 == m2);
    }
}
