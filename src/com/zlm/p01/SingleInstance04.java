package com.zlm.p01;

// 单例第4种写法，这个方法不行，在判断的时候没有加锁，所以还是有异常
public class SingleInstance04 {

    private static SingleInstance04 INSTANCE = null;

    private SingleInstance04() {}

    public static SingleInstance04 getInstance() {
        if (INSTANCE == null) {

            synchronized (SingleInstance04.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new SingleInstance04();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(SingleInstance04.getInstance().hashCode());
            }).start();
        }
    }
}