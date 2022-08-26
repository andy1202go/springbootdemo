package com.example.demo;

public class Singleton{
    private static Singleton instance = null;

    private Singleton(){
    }

    public static Singleton getInstance() {
        /**
         * 效率角度考虑，外层空判断
         */
        if (instance == null) {
            /**
             * 判空和实例化加锁，保证线程安全，只有一个实例
             */
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
