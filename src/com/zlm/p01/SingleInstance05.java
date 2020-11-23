package com.zlm.p01;

// 单例第5种写法，DCL（double check lock）
public class SingleInstance05 {
    // 这里为什么要加 volatile，禁止指令重排序，初始化操作的时候
    // 第二步和第三步重排序（1.关联 2.初始化），然后就会出错
    // java happens_before 规定了 8 种情况不能重排序，其它都可以重排 上面那种情况不包括
    private static volatile SingleInstance05 INSTANCE = null;

    private SingleInstance05() {}

    public static SingleInstance05 getInstance() {
        // 业务逻辑
        if (INSTANCE == null) {
            // 双重检查，标准写法（这里为什么不把 synchronized 写在 if 外面，因为多线程的时候抢锁消耗的资源比较大，所以要双重检查
            synchronized (SingleInstance05.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new SingleInstance05();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(SingleInstance05.getInstance().hashCode());
            }).start();
        }
    }
}
