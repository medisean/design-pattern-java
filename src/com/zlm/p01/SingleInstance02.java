package com.zlm.p01;

// 单例第二种写法，延迟加载，这里会对 INSTANCE 进行判断，然后初始化，这里有一个问题是多线程可能不安全
public class SingleInstance02 {

    private static SingleInstance02 INSTANCE = null;

    private SingleInstance02() {}

    public static SingleInstance02 getInstance() {
        if (INSTANCE == null) {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            INSTANCE = new SingleInstance02();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
               System.out.println(SingleInstance02.getInstance().hashCode());
            }).start();
        }
    }
}
