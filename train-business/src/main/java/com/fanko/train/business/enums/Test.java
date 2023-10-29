package com.fanko.train.business.enums;

public class Test {

    int num;
    public static void main(String[] args) {
        final Test demo1 = new Test();
        final Test demo2 = new Test();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                demo1.method("a");
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                demo2.method("b");
            }
        });
        t2.start();

    }
    public synchronized void method(String arg) {
        try {
            if("a".equals(arg)){
                num = 2;
                System.out.println("start running a");
                Thread.sleep(1000);
            }else{
                num = 3;
                System.out.println("start running b");
            }

            System.out.println("demo = "+ arg + ";num ="+ num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

