package com.mashibing.juc.c_000_Thread;

public class T04_ThreadState {

    static class MyThread extends Thread {
        @Override
        public void run() {
            //runnable
            System.out.println(this.getState());
            for(int i=0; i<10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();
        //new
        System.out.println(t.getState());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //teminated
        System.out.println(t.getState());

    }
}
