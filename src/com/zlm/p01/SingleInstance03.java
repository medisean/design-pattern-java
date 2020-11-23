package com.zlm.p01;

// 第三种实现，加 synchronized，多线程安全，这里的锁力度太粗
public class SingleInstance03 {

    private static SingleInstance03 INSTANCE = null;

    private SingleInstance03() {}

    public static synchronized SingleInstance03 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            INSTANCE = new SingleInstance03();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(SingleInstance03.getInstance().hashCode());
            }).start();
        }
    }
}